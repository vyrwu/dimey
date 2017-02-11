package com.mygdx.game;

import com.badlogic.gdx.Screen;

/**
 * Method handler class for the Screen interface. Implements all the methods from the Screen interface
 * to be overridden by subclasses of AbstractScreen. This solution makes code in Screen classes 
 * more readable, and allows to propagate the same GameCore instance across all Screens.
 * 
 * Our contribution: 90%
 */
abstract class AbstractScreen implements Screen {

    /**
     * Instance of GameCore.
     */
    protected GameCore game;
    
    /**
    * Constructor that is being run through extending AbstractClass. Handles the 
    * GameCore instance to be used in subclasses of this class.
    */
    protected AbstractScreen() {
        game = GameCore.getInstance();
    }

    // Methods of Screen interface below
    @Override
    public void show() {}

    @Override
    public void render(float delta) {}

    @Override
    public void resize(int x, int y) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
