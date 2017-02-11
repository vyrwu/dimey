package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * A class responsible for resources management. It can load, store and dispose resource elements.
 * This class uses disposable elements, and does not run dispose() automatically. Please remember
 * to dispose used assets after they are no longer needed to avoid memory leaks.
 * 
 * Our contribution: 100%
 */
public class Resources {

    private static Texture playerTexture2D, playerTexture3D;
    public static Texture splash;
    public static TiledMap mapOne, mapTwo, mapThree;

    /**
     * Loads all the resources to be used by the game. Any loaded assets should be also 
     * disposed when no longer needed. All resources are contained in Supergiera/core/assets directory.
     * 
     * Postconditions:
     * - the previously loaded resources are lost
     * 
     */
    public static void load() {
        playerTexture3D = new Texture(Gdx.files.internal("dimey64-3d.png"));
        playerTexture2D = new Texture(Gdx.files.internal("dimey64-2d.png"));
        mapOne          = new TmxMapLoader().load("map.tmx");
        mapTwo          = new TmxMapLoader().load("map2.tmx");
        mapThree        = new TmxMapLoader().load("map3.tmx");
        splash          = new Texture(Gdx.files.internal("splash.png"));
    }
    
    /**
     * Returns specific player texture. Utilizes ThreeDSystem to determine
     * which texture is to be returned by this method.
     * 
     * @return a Texture for the Player according to the state of ThreeDSystem
     */
    public static Texture getPlayerTexture() {
        if (ThreeDSystem.isOn()) {
            return playerTexture3D;
        }
        return playerTexture2D;
    }
    
    /**
     * Utility method for disposing assets. This method should be invoked when the assets 
     * are no longer needed to avoid memory leaks. Does not run automatically!
     * 
     * Postconditions:
     * - disposed assets can no longer be used
     * 
     * Side-effect:
     * - accessing disposed assets causes thread crash
     */
    public static void dispose() {
        playerTexture2D.dispose();
        playerTexture3D.dispose();
        mapOne.dispose();
        mapTwo.dispose();
        mapThree.dispose();
        splash.dispose();
    }
}
