package com.mygdx.game;

/**
 * Simple switcher class managing 2D and 3D modes of the game. The class operates with
 * thirdDimensionMode, which enables the game to render additional map layer containing 
 * 3D objects (i.e. walls), and determines players texture being rendered at the moment.
 * Classes that use ThreeDSystem: PlayScreen, Resources
 * 
 * Our contribution: 100%
 */
public class ThreeDSystem {
    
    private static boolean thirdDimensionMode = false;
    
    /**
     * Turns on/off the thirdDimensionMode.
     */
    public static void switchDimension() {
        if (thirdDimensionMode) {
            thirdDimensionMode = false;
        } else {
            thirdDimensionMode = true;
        }    
    }
    
    /**
     * Returns the ThreeDMode state.
     * @return true if the 3D mode is turned on
     */
    public static boolean isOn() {
        return thirdDimensionMode;
    }
}
