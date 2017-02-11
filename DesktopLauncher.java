package com.mygdx.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.GameCore;

/**
 * Desktop launcher class for the game. Contains a main class for the game, in 
 * which the configuration object is being instantiated to configure the game.
 * 
 * Our contribution: 0%
 */
public class DesktopLauncher {  
    
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 640;
        
        config.title = "Dimey";
        
        config.addIcon("icon16x16.png", FileType.Classpath);
        config.addIcon("icon32x32.png", FileType.Classpath);
        config.addIcon("icon128x128.png", FileType.Classpath);
        
 
        new LwjglApplication(GameCore.getInstance(), config);
    }
}
