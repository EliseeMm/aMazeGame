module aMazeGame {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.base;
    requires org.junit.jupiter.api;
    requires org.json;
    requires java.sql;

    opens com.mazegame;
    opens com.mazegame.Scenes;
    opens com.mazegame.CellsAndWalls;
    opens com.mazegame.DatabaseHandler;
}