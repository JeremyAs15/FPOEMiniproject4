package com.example.fpoeminiproject4.views;

import com.example.fpoeminiproject4.controllers.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameView extends Stage {
    private final GameController gameController;
    public GameController getGameController() {
        return gameController;
    }

    public GameView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fpoeminiproject4/GameView.fxml"));
        Parent root = loader.load();
        this.setTitle("Battle Ship Game");
        this.gameController = loader.getController();

        gameController.generateUserFleetGrid();
        gameController.generateMachineFleetGrid();

        Scene scene = new Scene(root);
        this.setScene(scene);
        this.setResizable(false);
        this.show();
    }

    private static class GameViewHolder {
        private static GameView INSTANCE;
    }

    public static GameView getInstance() throws IOException {
        if (GameViewHolder.INSTANCE == null) {
            return GameViewHolder.INSTANCE = new GameView();
        } else {
            return GameViewHolder.INSTANCE;
        }
    }
}