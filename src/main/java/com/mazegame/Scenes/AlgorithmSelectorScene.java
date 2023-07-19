package com.mazegame.Scenes;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The type Algorithm selector scene.
 */
public class AlgorithmSelectorScene {
    private final Scene scene;
    private final MazeScene nextScene;
    private String requestedAlgo;
    private final Stage stage;


    /**
     * Instantiates a new Algorithm selector scene.
     *
     * @param stage     the stage
     * @param nextScene the next scene
     */
    public AlgorithmSelectorScene(Stage stage, MazeScene nextScene) {
        VBox root = new VBox();


        Button kruskalButton = new Button("kruskal");
        Button dfsButton = new Button("DFS");
        Button primButton = new Button("Prim");
        Button ANBButton = new Button("AldousAndBroder");
        Button randomButton = new Button("Randomised");


        kruskalButton.setOnAction(actionEvent -> setRequestedAlgo("kruskal"));
        dfsButton.setOnAction(actionEvent -> setRequestedAlgo("dfs"));
        primButton.setOnAction(actionEvent -> setRequestedAlgo("prim"));
        ANBButton.setOnAction(actionEvent -> setRequestedAlgo("anb"));
        randomButton.setOnAction(actionEvent -> setRequestedAlgo("random"));

        root.getChildren().addAll(kruskalButton, dfsButton, primButton, ANBButton, randomButton);
        root.setAlignment(Pos.CENTER);
        this.nextScene = nextScene;
        this.stage = stage;
        scene = new Scene(root, 200, 200);

    }

    private void setRequestedAlgo(String algo) {
        requestedAlgo = algo;
        switchScene(nextScene);
    }

    /**
     * Gets scene.
     *
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Gets requested algo.
     *
     * @return the requested algo
     */
    public String getRequestedAlgo() {
        return requestedAlgo;
    }

    /**
     * Switch scene.
     *
     * @param scene the scene
     */
    public void switchScene(MazeScene scene) {
        scene.setAlgo(getRequestedAlgo());
        this.stage.setScene(scene.createMazeScene());
    }
}
