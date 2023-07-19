package com.mazegame.Scenes;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * The type Algorithm name.
 */
public class AlgorithmName extends Text {

    /**
     * Algo text text.
     *
     * @param algorithmName the algorithm name
     * @param color         the color
     * @return the text
     */
    public static Text algoText(String algorithmName, Color color) {

        Text text = new Text();
        text.setX(80);
        text.setY(600);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        text.setText(algorithmName);
        text.setStroke(color);
        return text;

    }
}
