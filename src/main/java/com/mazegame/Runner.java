package com.mazegame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The type Runner.
 */
public class Runner extends Rectangle {
    /**
     * The Obstacle size.
     */
    int obstacleSize;

    /**
     * Instantiates a new Runner.
     *
     * @param startingX    the starting x
     * @param startingY    the starting y
     * @param obstacleSize the obstacle size
     */
    public Runner(int startingX, int startingY, int obstacleSize) {
        super(startingX, startingY, Color.DARKMAGENTA);
        this.obstacleSize = obstacleSize;
        setWidth((double) obstacleSize / 3);
        setHeight((double) obstacleSize / 3);
        setTranslateX(startingX);
        setTranslateY(startingY);
    }

    /**
     * Move up.
     */
    public void moveUp() {
        setTranslateY(getTranslateY() - (obstacleSize - 1));
    }

    /**
     * Move down.
     */
    public void moveDown() {
        setTranslateY(getTranslateY() + (obstacleSize - 1));
    }

    /**
     * Move right.
     */
    public void moveRight() {
        setTranslateX(getTranslateX() + (obstacleSize - 1));
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        setTranslateX(getTranslateX() - (obstacleSize - 1));
    }


}