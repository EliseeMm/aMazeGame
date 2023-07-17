package com.mazegame.Scenes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.temporal.ChronoUnit;

public class Timer extends Label {

    private static Stage stage;

    public Timeline start(Stage stage) {
        this.stage = stage;

        // Create label to display the time left:
        Label countDownLabel = new Label();

        // Create duration property for time remaining:
        ObjectProperty<java.time.Duration> remainingDuration
                = new SimpleObjectProperty<>(java.time.Duration.ofSeconds(45)); // Here: 5 sec count down

        // Binding with media time format (hh:mm:ss):
        countDownLabel.textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("%02d:%02d:%02d",
                                remainingDuration.get().toHours(),
                                remainingDuration.get().toMinutesPart(),
                                remainingDuration.get().toSecondsPart()),
                remainingDuration));

        // Create time line to lower remaining duration every second:
        Timeline countDownTimeLine = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) ->
                remainingDuration.setValue(remainingDuration.get().minus(1, ChronoUnit.SECONDS))));

        // Set number of cycles (remaining duration in seconds):
        countDownTimeLine.setCycleCount((int) remainingDuration.get().getSeconds());

        // Show alert when time is up:
        countDownTimeLine.setOnFinished(event -> new Alert(Alert.AlertType.INFORMATION).show());


        // Prepare and show stage:
        this.stage.setScene(new Scene(new Pane(countDownLabel), 200, 100));
        this.stage.show();

        return countDownTimeLine;
    }

    public static void closeTimer(){
        stage.close();
    }
}