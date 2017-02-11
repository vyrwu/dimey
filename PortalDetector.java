package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

/**
 * Singleton class for detecting portals leading to different areas of the game. 
 * Implements lazy initialization design pattern. Uses nodes defined in Player 
 * class to determine whether the player has entered the zone denoted as the portal. 
 * The class must first be provided with a Player and TiledMap instances through its utility
 * methods. The portal layer of the TiledMap must be called "Portals".
 * 
 * Our contribution: 100%
 */
public class PortalDetector {

    private static PortalDetector instance;
    private TiledMapTileLayer portalLayer;
    private Player player;

    /**
     * Private constructor that creates an instance of PortalDetector.
     */
    private PortalDetector() {
        super();
    }

    /**
     * Retrieves an instance of PortalDetector. The method will always return the 
     * same instance of PortalDetector.
     * 
     * Postconditions:
     * - PortalDetector will remain instantiated, and accessible through this method.
     * 
     * @return a single instance of PortalDetector
     */
    public static PortalDetector getInstance() {
        if (instance == null) {
            instance = new PortalDetector();
        }
        return instance;
    }

    /**
     * Utility method for setting up the map from which to extract the portal layer.
     * The portal layer of the TiledMap must be called "Portals".
     * Remember to re-setup the detector for each map being rendered at the moment.
     * 
     * Postconditions:
     * - the previous value of map layer field is lost
     * 
     * Side-effects:
     * - portal layer might not match currently rendered map
     * 
     * @param map the desired TiledMap 
     */
    public void setPortalMap(TiledMap map) {
        this.portalLayer = (TiledMapTileLayer) map.getLayers().get("Portal");
    }

    /**
     * Utility method for setting up the player.
     * 
     * @param player the desired Player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Checks whether the player is inside the area marked as a portal. 
     * The method extracts the coordinates of the Player nodes and makes a 
     * check for each, using isCellAPortal(float x, float y) method.
     * 
     * Preconditions:
     * - PortalDetector has to be provided with a Player and TiledMap using utility methods.
     * - nodes in Player class have to be already updated
     * 
     * @return true if all the players nodes are inside a portal
     */
    public boolean checkForPortal() {
        for (int i = 0; i < player.nodes.length; i++) {
            float vertX = player.nodes[i][0];
            float vertY = player.nodes[i][1];

            if (!isCellAPortal(vertX, vertY)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Sends a message for changing the current Screen depending of the type of 
     * portal the player has entered. The types of portals can be specified as 
     * values of "Portal" property in tiles of desired TiledMap. The method has 
     * to be updated with each new type.
     */
    public void changeArea() {
        Cell cell = portalLayer.getCell(
                (int) (player.getX() / portalLayer.getTileWidth()),
                (int) (player.getY() / portalLayer.getTileHeight()));
        
        if (cell.getTile().getProperties().get("Portal").equals("1_2")) {
            ScreenManager.getInstance().showScreen(ScreenEnum.AREA_TWO);
        }
        if (cell.getTile().getProperties().get("Portal").equals("2_1")) {
            ScreenManager.getInstance().showScreen(ScreenEnum.AREA_ONE_BACK); 
        }
        if (cell.getTile().getProperties().get("Portal").equals("2_3")) {
            ScreenManager.getInstance().showScreen(ScreenEnum.AREA_THREE); 
        }
        if (cell.getTile().getProperties().get("Portal").equals("3_2")) {
            ScreenManager.getInstance().showScreen(ScreenEnum.AREA_TWO_BACK); 
        }
    }
    
    /**
     * Checks whether the tile being pointing given coordinates has a property "Portal".
     * 
     * Precondition:
     * - PortalDetector has to be provided with a TiledMap using utility methods.
     * 
     * @param posX is the x-coordinate of the node to be checked
     * @param posY is the y-coordinate of the node to be checked
     * @return true if the tile has a property "Portal" and if there was no tile/cell detected under given coordinates
     */
    private boolean isCellAPortal(float posX, float posY) {
        Cell cell = portalLayer.getCell(
                (int) (posX / portalLayer.getTileWidth()),
                (int) (posY / portalLayer.getTileHeight()));
        
        return cell != null
                && cell.getTile() != null
                && cell.getTile().getProperties().containsKey("Portal");

    }
}
