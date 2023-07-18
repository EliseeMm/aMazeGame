package com.mazegame.Scenes;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * The type Score board.
 */
public class ScoreBoard extends Text {
    /**
     * Score board text.
     *
     * @param score the score
     * @return the text
     */
    public Text scoreBoard(int score) {
        Text text = new Text();
        String scoreString = Integer.toString(score);
        text.setX(80);
        text.setY(40);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        text.setText("Score: " + scoreString);
        text.setStroke(Color.GOLD);
        return text;
    }
}
