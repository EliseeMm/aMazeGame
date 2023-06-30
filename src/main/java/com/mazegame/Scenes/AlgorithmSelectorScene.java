package com.mazegame.Scenes;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlgorithmSelectorScene {
    private final Scene scene;
    private final MazeScene nextScene;
    private String requestedAlgo;
    private final Stage stage;


    public AlgorithmSelectorScene(Stage stage,MazeScene nextScene){
        VBox root = new VBox();


        Button kruskalButton = new Button("kruskal");
        Button dfsButton = new Button("DFS");
        Button randomButton = new Button("Randomised");


        kruskalButton.setOnAction(actionEvent -> setRequestedAlgo("kruskal"));
        dfsButton.setOnAction(actionEvent -> setRequestedAlgo("dfs"));
        randomButton.setOnAction(actionEvent -> setRequestedAlgo("random"));

        root.getChildren().addAll(kruskalButton,dfsButton,randomButton);
        root.setAlignment(Pos.CENTER);
        this.nextScene = nextScene;
        this.stage = stage;
        scene = new Scene(root,200,200);

    }

    private void setRequestedAlgo(String algo) {
        requestedAlgo = algo;
        switchScene(nextScene);
    }

    public Scene getScene(){
        return scene;
    }

    public String getRequestedAlgo(){
        return requestedAlgo;
    }
    public void switchScene(MazeScene scene){
        scene.setAlgo(getRequestedAlgo());
        this.stage.setScene(scene.createMazeScene());
    }
}
