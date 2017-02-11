package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

/**
 * Singleton class for detecting players collision with map objects. Implements lazy 
 * initialization design pattern. Uses nodes defined in Player class to determine 
 * whether the cell that player is about to enter is blocked. The class must be 
 * provided with a Player and TiledMap instances through its utility methods first. 
 * The collision layer of the TiledMap must be called "BlockedAreas".
 * 
 * Our contribution: 100%
 */
public class CollisionDetector {

    private static CollisionDetector instance;
    private TiledMapTileLayer collisionLayer;
    private Player player;
    
    /**
     * Private constructor that creates an instance of CollisionDetector.
     */
    private CollisionDetector() {}
    
    /**
     * Retrieves an instance of CollisionDetector. The method will always return 
     * the same instance of CollisionDetector.
     * 
     * Postconditions:
     * - CollisionDetector will remain instantiated, and accessible through this method.
     * 
     * @return a single instance of CollisionDetector
     */
    public static CollisionDetector getInstance() {
        if (instance == null) {
            instance = new CollisionDetector();
        }
        return instance;
    }
    
    /**
     * Utility method for providing the CollisionDetector with a TiledMap map from 
     * which to extract the collision layer. The collision layer of the TiledMap 
     * must be called "BlockedAreas" to be properly read by CollisionDetector.
     * Remember to re-setup the detector for each map being rendered at the moment.
     * 
     * Postconditions:
     * - the previous value of map layer field is lost
     * 
     * Side-effects:
     * - collision layer might not match currently rendered map
     * 
     * @param map the desired TiledMap 
     */
    public void setCollisionMap(TiledMap map) {
        this.collisionLayer = (TiledMapTileLayer)map.getLayers().get("BlockedAreas");
    }
    
    /**
     * Utility method for setting up the player from which to extract the nodes.
     * @param player the desired Player instance
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Checks for the collision of the player with the map. The method extracts 
     * coordinates of the Player nodes and makes a check for each, using 
     * isCellBlocked(float x, float y) method.
     * 
     * Preconditions:
     * - CollisionDetector has to be provided with a Player and TiledMap using utility methods.
     * - nodes in Player class have to be updated.
     * 
     * @return true if at least one node is inside a blocked tile
     */
    public boolean checkMapCollision() {
        for (int i = 0; i < player.nodes.length; i++) {
            float vertX = player.nodes[i][0];
            float vertY = player.nodes[i][1];

            if (isCellBlocked(vertX, vertY)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validates if the tile being pointed by given coordinates has a property "Blocked".
     * The method will also return true, if there was no tile/cell detected under 
     * given coordinates.
     * 
     * Precondition:
     * - CollisionDetector has to be provided with a TiledMap using utility methods.
     * 
     * @param posX the desired X-position to be validated
     * @param posY the desired Y-position to be validated
     * @return true if the tile has a property "Blocked" and if there was no 
     * tile/cell detected under given coordinates
     */
    private boolean isCellBlocked(float posX, float posY) {
        Cell cell = collisionLayer.getCell(
                (int) (posX / collisionLayer.getTileWidth()),
                (int) (posY / collisionLayer.getTileHeight()));

        return cell != null
                && cell.getTile() != null
                && cell.getTile().getProperties().containsKey("Blocked");
    }
}
