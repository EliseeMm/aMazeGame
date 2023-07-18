package com.mazegame;



import com.mazegame.Scenes.AlgorithmSelectorScene;
import com.mazegame.Scenes.MazeScene;
import com.mazegame.Scenes.UserNameScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    private static Stage  stage;
    public static void main(String[] args) {
        launch();

    }

    @Override
    public void start(Stage stage) {
        Main.stage = stage;
        Grid grid = new Grid(12);
        MazeScene mazeSceneCreator = new MazeScene(stage, grid);

        AlgorithmSelectorScene algorithmSelectorScene = new AlgorithmSelectorScene(stage, mazeSceneCreator);

        UserNameScene userNameSceneCreator = new UserNameScene(stage, algorithmSelectorScene.getScene());
        Scene userNameScene = userNameSceneCreator.getScene();
//        String playerName = userNameSceneCreator.getPlayerName();
        Main.stage.setScene(userNameScene);
        Main.stage.setTitle("MAZE GAME");
        Main.stage.show();
    }
    public static void restart(){
        stage.close();
    }
}
