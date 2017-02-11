package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.BatchTiledMapRenderer;

/**
 * Controls the very simple camera of the game using OrthographicCamera. It is a type of camera that
 * provides only orthographic projection of the screen, meaning that it cannot present depth.
 * Orthographic projection is meant to be used by simple 2D games, and is represented by two
 * perpendicular lines which intersect at the middle of the viewport (an area of the screen that is being
 * considered as a display for the game). The intersect point is denoted as a center of the Camera.
 * 
 * Our contribution: 100%
 */
public class Camera extends OrthographicCamera {
    
    /**
     * Creates an instance of Camera, and sets its projection lines.
     * @param orthoX the desired length of the projection line parallel to the X-axis 
     * @param orthoY the desired length of the projection line parallel to the Y-axis 
     */
    public Camera(float orthoX, float orthoY) {
        setToOrtho(false, orthoX, orthoY);
    }
   
    /**
     * Updates the position of a camera, setting its center.
     * @param renderer an instance of renderer class to be used while rendering the scene
     * @param posX the desired X-position of the Camera center
     * @param posY the desired Y-position of the Camera center
     */
    public void updatePosition(BatchTiledMapRenderer renderer, float posX, float posY) {
        position.set(posX, posY, 0);
        update();
        renderer.setView(this);
    }
}
