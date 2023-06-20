package com.mazegame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Runner extends Rectangle {
    int obstacleSize;
    protected Runner(int startingX, int startingY, Color color, int obstacleSize){
        super(startingX,startingY,color);
        this.obstacleSize = obstacleSize;
        setWidth(20);
        setHeight(20);
        setTranslateX(startingX);
        setTranslateY(startingY);
    }

    void moveUp(){
        setTranslateY(getTranslateY() - (obstacleSize-1));
    }
    void moveDown(){
        setTranslateY(getTranslateY() + (obstacleSize-1));
    }
    void moveRight(){
        setTranslateX(getTranslateX() + (obstacleSize-1));
    }
    void moveLeft(){
        setTranslateX(getTranslateX() - (obstacleSize-1));
    }


}