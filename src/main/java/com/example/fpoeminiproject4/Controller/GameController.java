package com.example.fpoeminiproject4.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class GameController {

    @FXML
    private Label aircraftCounter, descriptionLabel, destroyerCounter, frigateCounter, labelPlayerName, messageLabel, submarineCounter;
    @FXML
    private Button horizontalBtn, revealShips, startGame, verticalBtn;
    @FXML
    private VBox parentContainer;
    @FXML
    private HBox revealShipsContainer;
    @FXML
    private GridPane selectionGrid;

    public void initialize(String username) {
        labelPlayerName.setText(username);
    }
}
