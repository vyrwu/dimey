package com.mygdx.game;

import com.badlogic.gdx.Screen;

/**
 * Singleton class responsible for Screen instances management.
 * In conjuction with ScreenEnum manipulate AbstractScreen instances to be currently shown, 
 * while disposing the previous ones.
 * Have to be initialized with GameCore instance before it can be used.
 * 
 * Our contribution: 20%
 */
public class ScreenManager {

    private static ScreenManager instance;
    private GameCore game;
    
    /**
     * Private constructor creating an instance of ScreenManager.
     */
    private ScreenManager() {}
    
    /**
     * Retrieves an instance of ScreenManager. The method will always
     * return the same instance of ScreenManager.
     * 
     * Postconditions:
     * - ScreenManager will remain instantiated, and accessible through this method.
     * 
     * @return a single instance of ScreenManager
     */
    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    /**
     * Initializes ScreenManager with GameCore instance.
     * 
     * @param gameCore the desired GameCore instance to be used
     */
    public void init(GameCore gameCore) {
        this.game = gameCore;
    }

    /**
     * Shows the specified Screen. The Screens are defined in ScreenEnum.
     * This method disposes current Screen to be replaced with a new one.
     * 
     * Pre-conditions:
     * - ScreenManager have to be initialized with GameCore instance.
     * 
     * Postconditions:
     * - The current Screen is disposed, and therefore no longer accessible.
     * 
     * Side-effects:
     * - NullPointerException
     * 
     * @param screenEnum the desired ScreenEnum.
     */
    public void showScreen(ScreenEnum screenEnum) {
        Screen currentScreen = game.getScreen();

        AbstractScreen newScreen = screenEnum.getScreen();
        game.setScreen(newScreen);

        if (currentScreen != null) {
            currentScreen.dispose();
        }
    }
}
