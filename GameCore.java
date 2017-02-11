package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Singleton class that delegates work to a Screen instances. Implements eager 
 * initialization design pattern. This class uses disposable elements, and does 
 * not run dispose() automatically. Please remember to dispose used assets when 
 * they are no longer needed to avoid memory leaks.
 * 
 * Our contribution: 70%
 */
public class GameCore extends Game {

    private static final GameCore instance = new GameCore();
    /**
     * An object used to batch the 2D drawing commands and optimize them for 
     * processing by the GPU. To draw something using batch, one has to call
     * begin() method first, specify drawing commands, and then invoke end()
     * method which will perform drawing. SpriteBatch is a heavy object, therefore
     * it should be instantiated only once.
     */
    public SpriteBatch batch;
    /**
     * An object used to render bitmap fonts.
     */
    public BitmapFont font;
    
    /**
     * Private constructor that creates an instance of GameCore.
     */
    private GameCore() {}
    
    /**
     * Returns an existing instance of GameCore
     * @return an existing instance of GameCore
     */
    public static GameCore getInstance() {
        return instance;
    }

    /**
     * Creates the core rendering objects, loads Resources to the game engine 
     * and delegates the control to the ScreenManager. 
     * This method runs only once.
     */
    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        Resources.load();
        ScreenManager.getInstance().init(this);
        ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
    }
    
    /**
     * An obligatory method for rendering the screen in libGDX.
     */
    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    /**
     * Utility method for disposing assets. This method must be invoked at least
     * once to avoid memory leaks. Does not run automatically!
     * 
     * Postconditions:
     * - disposed assets can no longer be used
     * 
     * Side-effect:
     * - accessing disposed assets causes thread crash
     */
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        Resources.dispose();
    }
}
