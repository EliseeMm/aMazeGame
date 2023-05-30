package com.mazegame;

import com.mazegame.Algorithms.DepthFirstSearchMaze;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        Grid grid = new Grid(4);


        ArrayList<ArrayList<Cell>> grids = grid.makeGrid();
        DepthFirstSearchMaze dfs = new DepthFirstSearchMaze(grid);
        ArrayList<ArrayList<Cell>> maze = dfs.depthFirstSearch();
        launch();

    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }
}
