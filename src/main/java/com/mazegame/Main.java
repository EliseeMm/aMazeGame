package com.mazegame;

import com.mazegame.Algorithms.DepthFirstSearchMaze;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class Main extends Application {
    private Rectangle endPoint;
    public static void main(String[] args) {
        launch();

    }

    @Override
    public void start(Stage stage){
        try {
            int offset = 0;
            int offsetY = 0;
            int obstacleSize = 60;
//            Rectangle endPoint;
            Grid grid = new Grid(12);
            DepthFirstSearchMaze dfs = new DepthFirstSearchMaze(grid);
            ArrayList<Line> lines =  new ArrayList<>();
            ArrayList<Rectangle> cells = new ArrayList<>();
            ArrayList<ArrayList<Cell>> maze = dfs.depthFirstSearch();
            Group root = new Group();
            for (ArrayList<Cell> row : maze) {
                for (Cell cell : row) {
                    Rectangle rectangle = new Rectangle();
                    rectangle.setX(cell.getX() + offset);
                    rectangle.setY(cell.getY() + offsetY);
                    rectangle.setHeight(obstacleSize);
                    rectangle.setWidth(obstacleSize);
                    if(cell.getCellType().equals(CellType.END)){
                        rectangle.setFill(Color.DARKCYAN);
                        endPoint = rectangle;
                    }
                    cells.add(rectangle);
                    root.getChildren().add(rectangle);
                    for (Wall wall : cell.getWalls()) {

                        Line line = new Line();
                        switch (wall.getWallPosition()) {
                            case TOP -> {
                                line.setStartX(wall.x()+offset);
                                line.setStartY(wall.y() - 0.5 + offsetY);
                                line.setEndX(wall.x()+obstacleSize+offset);
                                line.setEndY(wall.y() - 0.5 + offsetY);
                            }
                            case BOTTOM -> {
                                line.setStartX(wall.x()+offset);
                                line.setStartY(wall.y() + 0.5+obstacleSize+ offsetY);
                                line.setEndX(wall.x()+obstacleSize+offset);
                                line.setEndY(wall.y() + 0.5+obstacleSize+ offsetY);
                            }
                            case LEFT -> {
                                line.setStartX(wall.x() + 0.5 +offset);
                                line.setStartY(wall.y()+ offsetY);
                                line.setEndX(wall.x() + 0.5+offset);
                                line.setEndY(wall.y() +obstacleSize+ offsetY);
                            }
                            case RIGHT -> {
                                line.setStartX(wall.x() - 0.5+obstacleSize+offset);
                                line.setStartY(wall.y()+ offsetY);
                                line.setEndX(wall.x() - 0.5+obstacleSize+offset);
                                line.setEndY(wall.y() +obstacleSize+ offsetY);
                            }
                        }
                        lines.add(line);
                        line.setStyle("-fx-stroke: red;");
                        root.getChildren().add(line);
                    }
                    offset += (obstacleSize-1);
                }
                offsetY += (obstacleSize-1);
                offset = 0;
            }
            Rectangle selectedRectangle = getRunnerStartingCell(cells);
            Cell rectangleCentre = findCellCentre(selectedRectangle,obstacleSize);
            Runner runner = new Runner((int) rectangleCentre.getX(), (int) rectangleCentre.getY(),Color.SEAGREEN,obstacleSize);
            root.getChildren().add(runner);
            Scene scene = new Scene(root, 1000, 1000);


            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {

                    switch (keyEvent.getCode()){
                        case A -> {
                            if (linesDoNotIntersect(runner, lines, -obstacleSize, 0)){
                                runner.moveLeft();
                            }
                        }
                        case D -> {
                            if (linesDoNotIntersect(runner, lines, obstacleSize, 0)){
                                runner.moveRight();
                            }
                        }
                        case S -> {
                            if (linesDoNotIntersect(runner, lines, 0, +obstacleSize)){
                                runner.moveDown();
                            }
                        }
                        case W -> {
                            if (linesDoNotIntersect(runner, lines, 0, -obstacleSize)){
                                runner.moveUp();
                            }
                        }
                    }
                    System.out.println(robotInEndPoint(runner,endPoint));
                }
            });
            stage.setScene(scene);
            stage.setTitle("MAZE GAME");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Rectangle getRunnerStartingCell(ArrayList<Rectangle> cells){
        Random random = new Random();
        int rectangleIndex = random.nextInt(cells.size());
        return cells.get(rectangleIndex);
    }

    public Cell findCellCentre(Rectangle rectangle,int obstacleSize){
        float x = (float) rectangle.getX();
        float y = (float) rectangle.getY();
        return new Cell(x+20,y+20);
    }


    public boolean linesDoNotIntersect(Runner runner, ArrayList<Line> lines, int translateX, int translateY){
        Line runnerLine = new Line();
        runnerLine.setStartX(runner.getTranslateX());
        runnerLine.setStartY(runner.getTranslateY());
        runnerLine.setEndX(runner.getTranslateX() + translateX);
        runnerLine.setEndY(runner.getTranslateY() + translateY);
        for (Line line : lines){
            if(runnerLine.getBoundsInParent().intersects(line.getBoundsInParent())){
                return false;
            }
        }
        return true;
    }
    public boolean robotInEndPoint(Runner runner, Rectangle rectangle){
        Cell runnerCell = new Cell((float) runner.getTranslateX(), (float) runner.getTranslateY());
        Cell rectangleTopLeft = new Cell((float) rectangle.getX(),(float) rectangle.getY());
        Cell rectangleBottomRight = new Cell((float) ((float) rectangle.getX() + rectangle.getHeight()), (float) ((float) rectangle.getY() + rectangle.getHeight()));
        return  runnerCell.withinTwoPoints(rectangleTopLeft,rectangleBottomRight);
    }


}
