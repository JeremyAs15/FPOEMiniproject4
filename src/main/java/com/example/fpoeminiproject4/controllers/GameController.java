package com.example.fpoeminiproject4.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

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
    private GridPane selectionGrid, userFleet, machinesFleet;

    @FXML
    public void initialize() {
        generateUserFleetGrid();
        generateMachineFleetGrid();
    }

    public void setPlayerName(String playerName) {
        if (labelPlayerName != null) {
            labelPlayerName.setText(playerName);
        }
    }

    public void generateUserFleetGrid() {
        if (userFleet != null) {
            userFleet.getChildren().clear();
            setupGrid(userFleet, "userCell");
        }
    }

    public void generateMachineFleetGrid() {
        if (machinesFleet != null) {
            machinesFleet.getChildren().clear();
            setupGrid(machinesFleet, "machineCell");
        }
    }

    private void setupGrid(GridPane grid, String styleClass) {
        if (grid == null) return;
        for (int i = 0; i < 10; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(30));
            grid.getRowConstraints().add(new RowConstraints(30));
        }
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Pane cell = new Pane();
                cell.getStyleClass().add(styleClass);
                cell.setPrefSize(30, 30);
                grid.add(cell, col, row);
            }
        }
    }


}