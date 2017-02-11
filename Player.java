package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import static com.badlogic.gdx.Input.Keys.DOWN;
import static com.badlogic.gdx.Input.Keys.LEFT;
import static com.badlogic.gdx.Input.Keys.RIGHT;
import static com.badlogic.gdx.Input.Keys.UP;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A class that represents a Player. It is responsible for storing information
 * about the player such as its current position and texture. It also handles 
 * processing players movement across the map and detecting special events such 
 * as collision and portal traverse. Note that Player position is stored as a 
 * position of bottom left-hand corner of its texture.
 * 
 * Our contribution: 100%
 */
public class Player extends Sprite {
    
    private Texture texture; //Currently instantiated texture.
    private float width, height;// Current texture attributes.
    private float posX, posY; // Position of the bottom left-hand corner of the texture.
    /**
     * The set of coordinates of 8 points, denoted as nodes, used to detect 
     * events happened during the game. The scheme shows position of nodes around
     * the player texture.
     * 
     *           8 - 1 - 2
     *           |       |
     *           7 Hero  3
     *           |       |
     *           6 - 5 - 4
     */
    public float[][] nodes;

    /**
     * Constructor that creates a new instance of Player. It sets up primary
     * coordinates of Player and updates detector classes with this instance.
     * Defines a set of 8 points around the player, denoted as nodes, which are 
     * used in detector classes as detection points.
     * @param posX the X-coordinate of spawn for the player
     * @param posY the Y-coordinate of spawn for the player
     */
    public Player(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
        texture = Resources.getPlayerTexture();
        width = texture.getWidth();
        height = texture.getHeight();
        nodes = new float[8][2];
        CollisionDetector.getInstance().setPlayer(this);
        PortalDetector.getInstance().setPlayer(this);
        updatePlayerNodes();
    }
    
    /**
     * Refreshes players texture.
     * 
     * Postconditions:
     * - players nodes are updated to match the new texture
     */
    public final void refreshTexture() {
        Texture newTexture = Resources.getPlayerTexture();
        this.texture = newTexture;
        this.width = newTexture.getWidth();
        this.height = newTexture.getHeight();
        updatePlayerNodes();
    }
    
    /**
     * Sends request to the game batch to render the player at the position of 
     * the middle of its currently initialized texture.
     */
    public void render() {
        SpriteBatch gameBatch = GameCore.getInstance().batch;
        gameBatch.draw(texture,
                posX - width / 2,
                posY - height / 2);
    }
    
    /**
     * Processes movement of the player. Checks whether one of the arrow 
     * keys was pressed, and if so, it changes the position coordinates of this
     * Player accordingly. The method sends a messages to Detector classes, to
     * look for the special effects. If the collision with map was found, the 
     * position of the Player remain unchanged.
     * 
     * Postconditions:
     * - if portal was found, the current instance of Player will be disposed
     */
    public void processMovement() {
        float speed = 5;
        float oldX = posX, oldY = posY;
        if (Gdx.input.isKeyPressed(UP)) {
            posY += speed;
        }
        if (Gdx.input.isKeyPressed(DOWN)) {
            posY -= speed;
        }
        if (Gdx.input.isKeyPressed(RIGHT)) {
            posX += speed;
        }
        if (Gdx.input.isKeyPressed(LEFT)) {
            posX -= speed;
        }
        updatePlayerNodes();

        if (CollisionDetector.getInstance().checkMapCollision()) {
            posX = oldX;
            posY = oldY;
            updatePlayerNodes();
        }
        if (PortalDetector.getInstance().checkForPortal()) {
            PortalDetector.getInstance().changeArea();
        }
    }
    
    /**
     * Updates position of a set of points (nodes) the Player. 
     * Look below for the scheme of nodes:
     * 
     *           8 - 1 - 2
     *           |       |
     *           7 Hero  3
     *           |       |
     *           6 - 5 - 4
     * 
     * Postconditions:
     * - the previous coordinates of nodes will be lost
     */
    public final void updatePlayerNodes() {
        float[] node1 = {posX            , posY + height / 2};
        float[] node2 = {posX + width / 2, posY + height / 2};
        float[] node3 = {posX + width / 2, posY};
        float[] node4 = {posX + width / 2, posY - height / 2};
        float[] node5 = {posX            , posY - height / 2};
        float[] node6 = {posX - width / 2, posY - height / 2};
        float[] node7 = {posX - width / 2, posY};
        float[] node8 = {posX - width / 2, posY + height / 2};

        nodes[0] = node1;
        nodes[1] = node2;
        nodes[2] = node3;
        nodes[3] = node4;
        nodes[4] = node5;
        nodes[5] = node6;
        nodes[6] = node7;
        nodes[7] = node8;
    }
    
    /**
     * Returns players X-position. Note that this is the position of
     * bottom left-hand corner of the Players texture.
     * @return the current X-position of the player.
     */
    @Override
    public float getX() {
        return posX;
    }
    
    /**
     * Returns players Y-position. Note that this is the position of
     * bottom left-hand corner of the Players texture.
     * @return the current Y-position of the player.
     */
    @Override
    public float getY() {
        return posY;
    }

    /**
     * Sets players X-position. Note that this is the position of
     * bottom left-hand corner of the Players texture.
     * @param posX the desired X-position
     */
    @Override
    public void setX(float posX) {
        this.posX = posX;
    }
    
    /**
     * Sets players Y-position. Note that this is the position of
     * bottom left-hand corner of the Players texture.
     * @param posY the desired Y-position
     */
    @Override
    public void setY(float posY) {
        this.posY = posY;
    }

}
