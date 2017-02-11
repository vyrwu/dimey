package com.mygdx.game;

/**
 * Enumeration for all Screens in the game. It provides ScreenManager class with an instance
 * of AbstractScreen to be processed.
 * 
 * Can be interpreted as a Factory class for AbstractScreen objects.
 * 
 * Our contribution: 50%
 */
public enum ScreenEnum {
    MAIN_MENU {
        @Override
        public AbstractScreen getScreen() {
            return new SplashScreen();
        }
    },
    AREA_ONE {
        @Override
        public AbstractScreen getScreen() {
            return new PlayScreen(Resources.mapOne, 1200, 960, 768, 640);
        }
    },
    AREA_ONE_BACK {
        @Override
        public AbstractScreen getScreen() {
            return new PlayScreen(Resources.mapOne, 1200, 960, 1456, 384);
        }
    },
    AREA_TWO {
        @Override
        public AbstractScreen getScreen() {
            return new PlayScreen(Resources.mapTwo, 800, 640, 144, 640);
        }
    },
    AREA_TWO_BACK {
        @Override
        public AbstractScreen getScreen() {
            return new PlayScreen(Resources.mapTwo, 800, 640, 1456, 640);
        }
    },
    AREA_THREE {
        @Override
        public AbstractScreen getScreen() {
            return new PlayScreen(Resources.mapThree, 1600, 1280, 144, 639);
        }
    };

    /**
     * Returns an instance of AbstractScreen. 
     * To be overridden in enum types.
     * 
     * @return an instance of AbstractScreen
     */
    public abstract AbstractScreen getScreen();
}
