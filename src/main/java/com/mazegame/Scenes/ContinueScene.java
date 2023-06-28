package com.mazegame.Scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ContinueScene {
    private Scene scene;
    public Scene secondScene(){

        HBox root = new HBox();
        scene = new Scene(root,200,200);

        Button buttonYes = new Button("yes");
        Button buttonNo = new Button("no");

        buttonYes.setOnAction(event ->  yesButton());
        buttonNo.setOnAction(event ->  noButton());


        root.getChildren().addAll(buttonYes,buttonNo);
        root.setAlignment(Pos.CENTER);

        return scene;
    }
    public void yesButton(){

    }
    public void noButton(){
        System.exit(0);
    }

}
