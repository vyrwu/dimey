package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;

/**
 * Simple splash screen class asking for the users input.
 * 
 * Our contribution: 100%
 */
public class SplashScreen extends AbstractScreen {
    
    /**
     * Constructor that creates the SplashScreen instance. Sets up an Input Adapter
     * which observes for any users input.
     */
    public SplashScreen() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                ScreenManager.getInstance().showScreen(ScreenEnum.AREA_ONE);
                return true;
            }
        });
    }
    
    /**
     * Gathers rendering calls from objects in the game and provides them with 
     * the an instance of GameCore. The method runs periodically.
     * 
     * @param delta the value denoting the time in milliseconds between rendering
     * of the following frames
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(Resources.splash, 0, 0);
        game.font.draw(game.batch, "Press any key to continue...", 600, 64);
        game.batch.end();
    }
}
