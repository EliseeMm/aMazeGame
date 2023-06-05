package com.mazegame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Runner extends Rectangle {
    protected Runner(int startingX, int startingY, Color color, int obstacleSize){
        super(startingX,startingY,color);
        setWidth((double) obstacleSize /3);
        setHeight((double) obstacleSize /3);
        setTranslateX(startingX);
        setTranslateY(startingY);
    }

    void moveUp(){
        setTranslateY(getTranslateY() - 100);
    }
    void moveDown(){
        setTranslateY(getTranslateY() + 100);
    }
    void moveRight(){
        setTranslateX(getTranslateX() + 100);
    }
    void moveLeft(){
        setTranslateX(getTranslateX() - 100);
    }


}