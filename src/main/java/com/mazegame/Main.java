package com.mazegame;

import com.mazegame.Algorithms.DepthFirstSearchMaze;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
        Group root = new Group();
        Scene scene = new Scene(root, 320, 240);
        Grid grid = new Grid(12);


        ArrayList<ArrayList<Cell>> grids = grid.makeGrid();
        DepthFirstSearchMaze dfs = new DepthFirstSearchMaze(grid);
        ArrayList<ArrayList<Cell>> maze = dfs.depthFirstSearch();
        for(ArrayList<Cell> row : maze){
            for(Cell cell: row){
                System.out.println();
                Rectangle rectangle = new Rectangle();
                rectangle.setX(cell.getX());
                rectangle.setY(cell.getY());
                rectangle.setHeight(100);
                rectangle.setWidth(100);
                root.getChildren().add(rectangle);
                for(Wall wall: cell.getWalls()){
                    Rectangle walls = new Rectangle();
                    walls.setX(wall.x());
                    walls.setY(wall.y());
                    walls.setHeight(50);
                    walls.setWidth(50);
                    walls.setFill(Color.SKYBLUE);
                    root.getChildren().add(walls);
                }
            }
        }
        System.out.println(root.getChildren());
        stage.setTitle("Maze Escape");
        stage.setScene(scene);
        stage.show();

    }
}
