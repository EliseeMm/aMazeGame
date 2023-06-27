package com.mazegame;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ScoreBoard extends Text {
    public Text scoreBoard(int score){
        Text text = new Text();
        String scoreString = Integer.toString(score);
        text.setX(410);
        text.setY(60);
        text.setFont(Font.font("Verdana", FontWeight.BOLD,40));
        text.setText("Score: "+scoreString);
        text.setStroke(Color.GOLD);
        return text;
    }
}
