package com.mazegame.Scenes;

import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AlgorithmName extends Text {

    public static Text algoText(String algorithmName,Color color){

        Text text = new Text();
        text.setX(80);
        text.setY(600);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        text.setText("Algorithm: " + algorithmName);
        text.setStroke(color);
        return text;

    }
}
