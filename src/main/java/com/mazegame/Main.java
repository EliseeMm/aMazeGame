package com.mazegame;


import com.mazegame.Algorithms.Kruskal;
import com.mazegame.CellsAndWalls.Cell;
import com.mazegame.Scenes.AlgorithmSelectorScene;
import com.mazegame.Scenes.MazeScene;
import com.mazegame.Scenes.UserNameScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {
    public static void main(String[] args) {
        launch();

    }

    @Override
    public void start(Stage stage){
        Grid grid = new Grid(12);

        Kruskal kruskal = new Kruskal(grid);

        MazeScene mazeSceneCreator = new MazeScene(stage,grid);

        AlgorithmSelectorScene algorithmSelectorScene = new AlgorithmSelectorScene(stage,mazeSceneCreator);

        UserNameScene userNameSceneCreator = new UserNameScene(stage, algorithmSelectorScene.getScene());
        Scene userNameScene = userNameSceneCreator.getScene();
        String playerName = userNameSceneCreator.getPlayerName();
        System.out.println(playerName);

        stage.setScene(userNameScene);
        stage.setTitle("MAZE GAME");
        stage.show();
    }
}
