package com.mazegame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Runner extends Rectangle {
    int obstacleSize;

    public Runner(int startingX, int startingY, int obstacleSize) {
        super(startingX, startingY, Color.DARKMAGENTA);
        this.obstacleSize = obstacleSize;
        setWidth((double) obstacleSize / 3);
        setHeight((double) obstacleSize / 3);
        setTranslateX(startingX);
        setTranslateY(startingY);
    }

    public void moveUp() {
        setTranslateY(getTranslateY() - (obstacleSize - 1));
    }

    public void moveDown() {
        setTranslateY(getTranslateY() + (obstacleSize - 1));
    }

    public void moveRight() {
        setTranslateX(getTranslateX() + (obstacleSize - 1));
    }

    public void moveLeft() {
        setTranslateX(getTranslateX() - (obstacleSize - 1));
    }


}