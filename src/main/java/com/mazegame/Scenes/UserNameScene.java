package com.mazegame.Scenes;

import com.mazegame.PlayerInfo;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The type User name scene.
 */
public class UserNameScene {
    private Scene scene;
    private final Stage stage;
    private final Scene nextScene;

    /**
     * Instantiates a new User name scene.
     *
     * @param stage the stage
     * @param scene the scene
     */
    public UserNameScene(Stage stage, Scene scene) {
        this.stage = stage;
        this.nextScene = scene;
        this.scene = userName();
    }

    /**
     * User name scene.
     *
     * @return the scene
     */
    public Scene userName() {
        VBox root = new VBox();
        scene = new Scene(root, 200, 200);
        scene.setFill(Color.web("#81c483"));


        Text text = new Text();
        text.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
        text.setText("    UserName\n1 to 5 characters");
        text.setX(12);
        text.setY(12);


        text.setFill(Color.DARKCYAN);
        TextField nameField = new TextField();
        nameField.autosize();
        Text text2 = new Text();
        text2.setFill(Color.DARKCYAN);
        text2.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
        text2.setText("Enter to save.");
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(text, nameField, text2);

        nameField.setOnAction(actionEvent -> {
            if (!nameField.getText().isEmpty() && nameField.getText().length() <= 5) {
                PlayerInfo playerInfo = new PlayerInfo();
                playerInfo.setPlayerName(nameField.getText());
                switchScene(nextScene);
            }
            nameField.clear();
        });
        return scene;
    }

    /**
     * Switch scene.
     *
     * @param scene the scene
     */
    public void switchScene(Scene scene) {
        stage.setScene(scene);
    }

    /**
     * Gets scene.
     *
     * @return the scene
     */
    public Scene getScene() {
        return this.scene;
    }

}
