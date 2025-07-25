package com.example.fpoeminiproject4.views;

import com.example.fpoeminiproject4.controllers.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameView extends Stage {
    private static GameView instance;
    private GameController gameController;

    private GameView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fpoeminiproject4/GameView.fxml"));
        Parent root = loader.load();
        this.gameController = loader.getController();
        this.setScene(new Scene(root));
        this.setTitle("Battle Ship Game");
        this.setResizable(false);
    }

    public static GameView getInstance() throws IOException {
        if (instance == null) {
            instance = new GameView();
        }
        return instance;
    }

    public GameController getGameController() {
        return gameController;
    }
}

