package com.mazegame;


import com.mazegame.Scenes.MazeScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {
    public static void main(String[] args) {
        launch();

    }

    @Override
    public void start(Stage stage){
        MazeScene mazeScene = new MazeScene(stage);
        Scene scene1 = mazeScene.createMazeScene();
        stage.setScene(scene1);
        stage.setTitle("MAZE GAME");
        stage.show();
    }

}
