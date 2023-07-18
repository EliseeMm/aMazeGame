package com.mazegame.Scenes;

import com.mazegame.Main;
import com.mazegame.ScoresAndNames.ScoreNameAlgorithm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LeaderBoard {
    private final TableView<ScoreNameAlgorithm> tableView = new TableView<>();
    private final TableColumn<ScoreNameAlgorithm, String> name = new TableColumn<>("Player Name");
    private final TableColumn<ScoreNameAlgorithm, String> algorithm = new TableColumn<>("Algorithm");
    private final TableColumn<ScoreNameAlgorithm, Integer> score = new TableColumn<>("Score");


    public Scene makeTable(ArrayList<ScoreNameAlgorithm> scoreNameAlgorithms) {
        VBox vBox = new VBox();
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        Button quit = new Button("quit");
        Button playAgain = new Button("play again");

        playAgain.setOnAction(restart -> playAgain());
        quit.setOnAction(exit -> System.exit(0));
        algorithm.setCellValueFactory(new PropertyValueFactory<>("algorithmType"));
        score.setCellValueFactory(new PropertyValueFactory<>("Score"));
        tableView.getColumns().addAll(name, algorithm, score);

        ObservableList<ScoreNameAlgorithm> observableList = FXCollections.observableArrayList(scoreNameAlgorithms);

        tableView.setItems(observableList);
        vBox.getChildren().addAll(tableView, quit,playAgain);
        return new Scene(vBox);
    }

    public void playAgain(){
        Main.restart();
        Timer.closeTimer();
        new Main().start(new Stage());
    }
}
