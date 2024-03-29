package com.mazegame.Scenes;

import com.mazegame.Algorithms.Algorithms;
import com.mazegame.CellsAndWalls.Cell;
import com.mazegame.CellsAndWalls.CellType;
import com.mazegame.CellsAndWalls.Wall;
import com.mazegame.DatabaseHandler.DbConnect;
import com.mazegame.Grid;
import com.mazegame.PlayerInfo;
import com.mazegame.Runner;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;


/**
 * The type Maze scene.
 */
public class MazeScene {
    private Rectangle endPoint;
    private final ScoreBoard scoreBoard = new ScoreBoard();
    private int score = 0;
    private final Grid grid;
    private final Stage mazeStage;
    private String algo;
    private final Timeline timeline;
    private final Boolean gameOn;
    private final Color endPointColor = Color.STEELBLUE;

    /**
     * Instantiates a new Maze scene.
     *
     * @param stage the stage
     * @param grid  the grid
     */
    public MazeScene(Stage stage, Grid grid) {
        Stage timeStage = new Stage();
        this.mazeStage = stage;
        this.grid = grid;
        Timer timer = new Timer();
        timeline = timer.start(timeStage);
        gameOn = true;

    }

    /**
     * Create maze scene scene.
     *
     * @return the scene
     */
    public Scene createMazeScene() {
        if (gameOn) {
            timeline.play();
        }

        Scene scene;
        try {
            int offset = 80;
            int offsetY = 50;
            int obstacleSize = 45;

            GenerateMaze generateMaze = new GenerateMaze(grid, algo);
            ArrayList<ArrayList<Cell>> maze = generateMaze.getMaze();
            String colorWall = generateMaze.getColorWall();
            Algorithms algorithms = generateMaze.getAlgorithms();

            ArrayList<Line> lines = new ArrayList<>();
            ArrayList<Rectangle> cells = new ArrayList<>();

            Group root = new Group();

            for (ArrayList<Cell> row : maze) {
                for (Cell cell : row) {
                    Rectangle rectangle = new Rectangle();
                    rectangle.setX(cell.getX() + offset);
                    rectangle.setY(cell.getY() + offsetY);
                    rectangle.setHeight(obstacleSize);
                    rectangle.setWidth(obstacleSize);
                    if (cell.getCellType().equals(CellType.END)) {
                        rectangle.setFill(endPointColor);
                        endPoint = rectangle;
                    }
                    cells.add(rectangle);
                    root.getChildren().add(rectangle);
                    for (Wall wall : cell.getWalls()) {

                        Line line = new Line();
                        switch (wall.getWallPosition()) {
                            case TOP -> {
                                line.setStartX(wall.x() + offset);
                                line.setStartY(wall.y() - 0.5 + offsetY);
                                line.setEndX(wall.x() + obstacleSize + offset);
                                line.setEndY(wall.y() - 0.5 + offsetY);
                            }
                            case BOTTOM -> {
                                line.setStartX(wall.x() + offset);
                                line.setStartY(wall.y() + 0.5 + obstacleSize + offsetY);
                                line.setEndX(wall.x() + obstacleSize + offset);
                                line.setEndY(wall.y() + 0.5 + obstacleSize + offsetY);
                            }
                            case LEFT -> {
                                line.setStartX(wall.x() + 0.5 + offset);
                                line.setStartY(wall.y() + offsetY);
                                line.setEndX(wall.x() + 0.5 + offset);
                                line.setEndY(wall.y() + obstacleSize + offsetY);
                            }
                            case RIGHT -> {
                                line.setStartX(wall.x() - 0.5 + obstacleSize + offset);
                                line.setStartY(wall.y() + offsetY);
                                line.setEndX(wall.x() - 0.5 + obstacleSize + offset);
                                line.setEndY(wall.y() + obstacleSize + offsetY);
                            }
                        }
                        lines.add(line);
                        line.setStyle("-fx-stroke: " + colorWall + ";");
                        root.getChildren().add(line);
                    }
                    offset += (obstacleSize - 1);
                }
                offsetY += (obstacleSize - 1);
                offset = 80;
            }
            Rectangle selectedRectangle = getRunnerStartingCell(cells);
            Cell rectangleCentre = findCellCentre(selectedRectangle, obstacleSize);
            Runner runner = new Runner((int) rectangleCentre.getX(), (int) rectangleCentre.getY(), obstacleSize);
            Text text = scoreBoard.scoreBoard(score);
            Text text1 = AlgorithmName.algoText(algorithms.getAlgorithmName(), algorithms.getAlorithmColor());
            root.getChildren().addAll(runner, text, text1);

            scene = new Scene(root, 700, 700);
            scene.setFill(Color.SKYBLUE);


            timeline.setOnFinished(actionEvent -> {
                try {
                    close();
                } catch (FileNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
            });


            scene.setOnKeyPressed(keyEvent -> {
                switch (keyEvent.getCode()) {
                    case LEFT -> {
                        if (linesDoNotIntersect(runner, lines, -obstacleSize, 0)) {
                            runner.moveLeft();
                        }
                    }
                    case RIGHT -> {
                        if (linesDoNotIntersect(runner, lines, obstacleSize, 0)) {
                            runner.moveRight();
                        }
                    }
                    case DOWN -> {
                        if (linesDoNotIntersect(runner, lines, 0, +obstacleSize)) {
                            runner.moveDown();
                        }
                    }
                    case UP -> {
                        if (linesDoNotIntersect(runner, lines, 0, -obstacleSize)) {
                            runner.moveUp();
                        }
                    }
                }
                if (robotInEndPoint(runner, endPoint)) {
                    score += 1;
                    switchScene(createMazeScene());
                    mazeStage.show();

                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return scene;
    }

    /**
     * Gets runner starting cell.
     *
     * @param cells the cells
     * @return the runner starting cell
     */
    public Rectangle getRunnerStartingCell(ArrayList<Rectangle> cells) {
        Random random = new Random();
        while (true) {
            int rectangleIndex = random.nextInt(cells.size());
            Color rectangleColor = (Color) cells.get(rectangleIndex).getFill();
            if (!rectangleColor.equals(endPointColor)) {
                return cells.get(rectangleIndex);
            }
        }
    }

    /**
     * Find cell centre cell.
     *
     * @param rectangle    the rectangle
     * @param obstacleSize the obstacle size
     * @return the cell
     */
    public Cell findCellCentre(Rectangle rectangle, int obstacleSize) {
        float x = (float) rectangle.getX();
        float y = (float) rectangle.getY();
        return new Cell(x + ((float) obstacleSize / 3), y + ((float) obstacleSize / 3));
    }

    /**
     * Lines do not intersect boolean.
     *
     * @param runner     the runner
     * @param lines      the lines
     * @param translateX the translate x
     * @param translateY the translate y
     * @return the boolean
     */
    public boolean linesDoNotIntersect(Runner runner, ArrayList<Line> lines, int translateX, int translateY) {
        Line runnerLine = new Line();
        runnerLine.setStartX(runner.getTranslateX());
        runnerLine.setStartY(runner.getTranslateY());
        runnerLine.setEndX(runner.getTranslateX() + translateX);
        runnerLine.setEndY(runner.getTranslateY() + translateY);
        for (Line line : lines) {
            if (runnerLine.getBoundsInParent().intersects(line.getBoundsInParent())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Robot in end point boolean.
     *
     * @param runner    the runner
     * @param rectangle the rectangle
     * @return the boolean
     */
    public boolean robotInEndPoint(Runner runner, Rectangle rectangle) {
        Cell runnerCell = new Cell((float) runner.getTranslateX(), (float) runner.getTranslateY());
        Cell rectangleTopLeft = new Cell((float) rectangle.getX(), (float) rectangle.getY());
        Cell rectangleBottomRight = new Cell((float) ((float) rectangle.getX() + rectangle.getHeight()), (float) ((float) rectangle.getY() + rectangle.getHeight()));
        return runnerCell.withinTwoPoints(rectangleTopLeft, rectangleBottomRight);
    }

    /**
     * Switch scene.
     *
     * @param scene the scene
     */
    public void switchScene(Scene scene) {
        mazeStage.setScene(scene);
    }

    /**
     * Sets algo.
     *
     * @param algo the algo
     */
    public void setAlgo(String algo) {
        this.algo = algo;
    }

    /**
     * Close.
     *
     * @throws FileNotFoundException the file not found exception
     * @throws SQLException          the sql exception
     */
    public void close() throws FileNotFoundException, SQLException {
        PlayerInfo.setPlayerScore(score);
        DbConnect dbConnect = new DbConnect();
        Connection connection = dbConnect.getConnection();
        dbConnect.addPlayerScore(connection, PlayerInfo.getPlayerName(), algo, PlayerInfo.getPlayerScore());
        dbConnect.readScoreTable(connection);
        LeaderBoard leaderBoard = new LeaderBoard();
        switchScene(leaderBoard.makeTable(dbConnect.readScoreTable(connection)));

    }
}
