package com.example.fpoeminiproject4.controllers;

import com.example.fpoeminiproject4.views.GameView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    @FXML
    private TextField inputUsername;

    @FXML
    void OnInstructionsButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rules");
        alert.setHeaderText(null);
        alert.setContentText(""" 
                --------------------------------  RULES -------------------------------
                * The goal is to destroy all enemy ships before it.
                * You cannot shoot on your own Position Board.
                * Each player has two boards: one to place their own ships and one to attack the opponent.
                * The Attack Board is used to guess and hit the enemy's ships.
                * On your turn, click a square on the Attack Board to fire a shot.
                * If the shot hits a ship, it will be marked as a hit.
                * If the shot misses, it will be marked with a blue square and the turn passes to the opponent.
                * If you destroy an entire ship, it is marked as sunk with red squares.
                * After sinking a ship, you can continue shooting until you miss.
                * The game continues with alternating turns between you and the computer.
                * The game ends when one player sinks all of the opponent’s ships.
                """);
        alert.showAndWait();
    }

    @FXML
    void onPlayButton(ActionEvent event) throws IOException {
        if (inputUsername == null || inputUsername.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Type your username!");
            alert.showAndWait();
        } else {
            Node source = (Node) event.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();

            GameView gameView = GameView.getInstance();
            gameView.getGameController().setPlayerName(inputUsername.getText());
            gameView.show();
        }
    }
}