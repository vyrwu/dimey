package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.Input.Keys.ESCAPE;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * The main class for the play areas of the game. Its main task is to render the
 * whole screen and every element that is shown in the game, supply the game with 
 * core features such as Camera, Collision and Portal Detectors and processes the 
 * unique for the play area input. Each zone that the player enters is a new instance 
 * of PlayScreen.
 * This class uses disposable elements, and does not run dispose() automatically. 
 * Please remember to dispose used assets when they are no longer needed to avoid 
 * memory leaks.
 */
public class PlayScreen extends AbstractScreen {

    private final int[] background = {0},
                        walls      = {1},
                        shadows    = {2};
    private final OrthogonalTiledMapRenderer renderer;
    private final Camera camera;
    private final Player player;

    /**
     * Constructor that creates the PlayScreen instance. Supplies this instance with
     * new instances of Player, Camera and Renderer, updates Collision and Portal 
     * detectors with desired TiledMap for the level and sets up an Input Adapter
     * which observes for the specific input keys:
     * - ESCAPE for exiting the game
     * - D for changing the dimension in the game
     * @param mapName the desired TiledMap to be rendered
     * @param camWidth the desired width of Camera window
     * @param camHeight the desired width of Camera window
     * @param posX the X-coordinate of spawn for the player
     * @param posY the Y-coordinate of spawn for the player
     * 
     * Our contribution: 90%
     */
    public PlayScreen(TiledMap mapName, float camWidth, float camHeight, float posX, float posY) {
        renderer = new OrthogonalTiledMapRenderer(mapName);
        camera   = new Camera(camWidth, camHeight);
        player   = new Player(posX, posY);
        CollisionDetector.getInstance().setCollisionMap(mapName);
        PortalDetector.getInstance().setPortalMap(mapName);
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == ESCAPE) {
                    game.dispose(); 
                    dispose();
                    System.exit(0);
                }
                if (keycode == D) {
                    ThreeDSystem.switchDimension();
                    player.refreshTexture();
                }
                return true;
            }
        });
    }
    
    /**
     * Gathers rendering calls from objects in the game and provides them with 
     * the an instance of GameCore. The method runs periodically, 
     * therefore it is also responsible for updating the Camera, and processing 
     * the movement of the Player.
     * 
     * @param delta the value denoting the time in milliseconds between rendering 
     * of the following frames
     */
    @Override
    public void render(float delta) {
        camera.updatePosition(renderer, player.getX(), player.getY());
        game.batch.setProjectionMatrix(camera.combined);

        renderer.render(background);
        game.batch.begin();
        player.render();
        game.batch.end();
        renderer.render(shadows);
        
        if (ThreeDSystem.isOn()) {
            renderer.render(walls);
        }

        player.processMovement();
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
    @Override
    public void dispose() {
        renderer.dispose();
    }
}
