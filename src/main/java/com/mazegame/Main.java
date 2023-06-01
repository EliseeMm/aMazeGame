package com.mazegame;

import com.mazegame.Algorithms.DepthFirstSearchMaze;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class Main extends Application {
    public static void main(String[] args) {
        launch();

    }

    @Override
    public void start(Stage stage) throws IOException {
        try {
            int offset = 0;
            int offsetY = 0;
            Grid grid = new Grid(10);
            ArrayList<ArrayList<Cell>> grids = grid.makeGrid();
            DepthFirstSearchMaze dfs = new DepthFirstSearchMaze(grid);
            ArrayList<ArrayList<Cell>> maze = dfs.depthFirstSearch();
            Group root = new Group();
            for (ArrayList<Cell> row : maze) {
                for (Cell cell : row) {
                    Rectangle rectangle = new Rectangle();
                    rectangle.setX(cell.getX() + offset);
                    rectangle.setY(cell.getY() + offsetY);
                    rectangle.setHeight(100);
                    rectangle.setWidth(100);
                    root.getChildren().add(rectangle);
                    for (Wall wall : cell.getWalls()) {

                        Line line = new Line();
                        switch (wall.getWallPosition()) {
                            case TOP -> {
                                line.setStartX(wall.x()+offset);
                                line.setStartY(wall.y() - 0.5 + offsetY);
                                line.setEndX(wall.x()+100+offset);
                                line.setEndY(wall.y() - 0.5 + offsetY);
                            }
                            case BOTTOM -> {
                                line.setStartX(wall.x()+offset);
                                line.setStartY(wall.y() + 0.5+100+ offsetY);
                                line.setEndX(wall.x()+100+offset);
                                line.setEndY(wall.y() + 0.5+100+ offsetY);
                            }
                            case LEFT -> {
                                line.setStartX(wall.x() + 0.5 +offset);
                                line.setStartY(wall.y()+ offsetY);
                                line.setEndX(wall.x() + 0.5+offset);
                                line.setEndY(wall.y() +100+ offsetY);
                            }
                            case RIGHT -> {
                                line.setStartX(wall.x() - 0.5+100+offset);
                                line.setStartY(wall.y()+ offsetY);
                                line.setEndX(wall.x() - 0.5+100+offset);
                                line.setEndY(wall.y() +100+ offsetY);
                            }
                        }
                        line.setStyle("-fx-stroke: red;");
                        root.getChildren().add(line);
                    }
                    offset += 99;
                }
                offsetY +=99;
                offset = 0;
            }
            Cell cell = getRunnerStartingCell(grids);
            int[] centre = findCellCentre(cell,100);
            Runner runner = new Runner(centre[0],centre[1],Color.SEAGREEN,100);
            root.getChildren().add(runner);
            Scene scene = new Scene(root, 1000, 1000);
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    switch (keyEvent.getCode()){
                        case A -> runner.moveLeft();
                        case D -> runner.moveRight();
                        case S -> runner.moveDown();
                        case W -> runner.moveUp();
                    }
                }
            });
            stage.setScene(scene);
            stage.setTitle("MAZE GAME");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public class Runner extends Rectangle {
        private int startingX;
        private int startingY;

        private Runner(int startingX,int startingY,Color color,int obstacleSize){
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

    public Cell getRunnerStartingCell(ArrayList<ArrayList<Cell>> grid){
        Random random = new Random();

        int rowIndex = random.nextInt(grid.size());
        System.out.println(grid.size());
        System.out.println(rowIndex);

        ArrayList<Cell> row = grid.get(rowIndex);

        int cellIndex = random.nextInt(row.size());




        return row.get(cellIndex);
    }


    public int[] findCellCentre(Cell cell, int size){



        int xCentre = (int) ((cell.getX() + size)/3);
        int yCentre = (int) ((cell.getY() + size)/3);
        return new int[]{xCentre,yCentre};

    }
}
