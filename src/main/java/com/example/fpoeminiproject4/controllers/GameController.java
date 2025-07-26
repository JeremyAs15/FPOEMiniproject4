package com.example.fpoeminiproject4.controllers;

import com.example.fpoeminiproject4.models.CreateShip;
import com.example.fpoeminiproject4.models.GameModel;
import com.example.fpoeminiproject4.models.Ship;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameController {

    @FXML
    private Label aircraftCounter, descriptionLabel, destroyerCounter, frigateCounter, labelPlayerName, messageLabel, submarineCounter;
    @FXML
    private Button horizontalBtn, revealShips, startGame, verticalBtn, revealFleet;
    @FXML
    private VBox parentContainer;
    @FXML
    private HBox revealShipsContainer;
    @FXML
    private GridPane selectionGrid, userFleet, machinesFleet;
    private int shipType;
    private int shipOrientation = 1;
    private String username;
    private final CreateShip drawer;
    private GameModel gameModel;
    private boolean successfulShot = false;
    private final Set<String> registeredSunkenShips = new HashSet<>();
    private boolean finalStatsSaved = false;
    private boolean winnerAlertShown = false;
    private boolean playerTurn = true;

    public GameController() {
        gameModel = new GameModel();
        drawer = new CreateShip();
    }

    @FXML
    private void setHorizontalOrientation(ActionEvent event) {
        shipOrientation = 1;
        updateOrientationButtons();
    }
    @FXML
    private void setVerticalOrientation(ActionEvent event) {
        shipOrientation = 0;
        updateOrientationButtons();
    }

    @FXML
    public void initializeNewGame(String username) {

            this.username = username.trim();
            labelPlayerName.setText(this.username);

            gameModel.newMatch(this.username);

            generateUserFleetGrid();
            generateMachineFleetGrid();

            setCellsEvents();
            setUpShipEvents();

            updateLabels();

            startGame.setDisable(!gameModel.getPositionTable().isBoardFull());
            machinesFleet.setDisable(true);
            revealFleet.setDisable(false);
            playerTurn = false;
            successfulShot = false;
            registeredSunkenShips.clear();
            finalStatsSaved = false;
            winnerAlertShown = false;

            messageLabel.setVisible(false);
            descriptionLabel.setText("Pick a ship and place it on your board");

            updateOrientationButtons();
    }

    public void updateLabels() {
        ArrayList<Ship> ships = gameModel.getPositionTable().getShips();
        for (Ship ship : ships) {
            if (ship == null) continue;
            if (ship.getShipType() == 1) {
                frigateCounter.setText("x" + ship.getShipAmount());
            } else if (ship.getShipType() == 2) {
                destroyerCounter.setText("x" + ship.getShipAmount());
            } else if (ship.getShipType() == 3) {
                submarineCounter.setText("x" + ship.getShipAmount());
            } else if (ship.getShipType() == 4) {
                aircraftCounter.setText("x" + ship.getShipAmount());
            }
        }
    }

    private void setCellsEvents() {
        for (Node node : userFleet.getChildren()) {
            node.setOnDragOver(event -> {
                if (event.getGestureSource() != node &&
                        event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            });

            node.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                boolean success = false;

                if (db.hasString()) {
                    Integer row = GridPane.getRowIndex(node);
                    Integer col = GridPane.getColumnIndex(node);

                    if (row == null) row = 0;
                    if (col == null) col = 0;

                    placeShip(row, col);
                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();
            });
        }

        for (Node node : machinesFleet.getChildren()) {
            node.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            });
        }
    }

    private void setUpShipEvents() {
        setupDraggableShip(1, drawer.drawFrigate(true), "Frigate occupies 1 cells.");
        setupDraggableShip(2, drawer.drawDestroyer(true, false), "Destroyer occupies 2 cells.");
        setupDraggableShip(3, drawer.drawSubmarine(true, false), "Submarine occupies 3 cells.");
        setupDraggableShip(4, drawer.drawAircraftCarrier(true, false), "Carrier occupies 4 cells");
    }

    private void setupDraggableShip(int type, Group ship, String description) {
        ship.getStyleClass().add("selection-ship");
        ship.setOnDragDetected(event -> {
            if (gameModel.getPositionTable().checkAmount(type)) {
                this.shipType = type;
                descriptionLabel.setText(description);

                Dragboard db = ship.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(String.valueOf(type));
                db.setContent(content);
                db.setDragView(ship.snapshot(null, null));

                event.consume();
            } else {
                showMessage("No more vessels of this class are in stock");
            }
        });

        selectionGrid.add(ship, 0, type-1);
    }

    private void placeShip(int row, int col) {

            gameModel.getPositionTable().setShipPosition(shipType, row, col, shipOrientation);

            Group ship = switch (shipType) {
                case 1 -> drawer.drawFrigate(shipOrientation == 0);
                case 2 -> drawer.drawDestroyer(shipOrientation == 0, true);
                case 3 -> drawer.drawSubmarine(shipOrientation == 0, true);
                case 4 -> drawer.drawAircraftCarrier(shipOrientation == 0, true);
                default -> null;
            };

            if (ship != null) {
                userFleet.add(ship, col, row);
                updateCounter(shipType);
            }

            if (gameModel.getPositionTable().isBoardFull()) {
                startGame.setDisable(false);
            }
    }

    private void updateCounter(int shipType) {
        int currentAmount = 0;
        switch (shipType) {
            case 1:
                currentAmount = Character.getNumericValue(frigateCounter.getText().charAt(1));
                currentAmount--;
                frigateCounter.setText("x" + currentAmount);
                break;
            case 2:
                currentAmount = Character.getNumericValue(destroyerCounter.getText().charAt(1));
                currentAmount--;
                destroyerCounter.setText("x" + currentAmount);
                break;
            case 3:
                currentAmount = Character.getNumericValue(submarineCounter.getText().charAt(1));
                currentAmount--;
                submarineCounter.setText("x" + currentAmount);
                break;
            case 4:
                currentAmount = Character.getNumericValue(aircraftCounter.getText().charAt(1));
                currentAmount--;
                aircraftCounter.setText("x" + currentAmount);
                break;
        }
    }

    private void updateOrientationButtons() {
        if (horizontalBtn == null || verticalBtn == null) return;

        horizontalBtn.getStyleClass().removeAll("orientation-button-selected", "orientation-button-unselected");
        verticalBtn.getStyleClass().removeAll("orientation-button-selected", "orientation-button-unselected");

        if (!horizontalBtn.getStyleClass().contains("orientation-button")) {
            horizontalBtn.getStyleClass().add("orientation-button");
        }
        if (!verticalBtn.getStyleClass().contains("orientation-button")) {
            verticalBtn.getStyleClass().add("orientation-button");
        }

        if (shipOrientation == 1) {
            horizontalBtn.getStyleClass().add("orientation-button-selected");
            verticalBtn.getStyleClass().add("orientation-button-unselected");
        } else {
            verticalBtn.getStyleClass().add("orientation-button-selected");
            horizontalBtn.getStyleClass().add("orientation-button-unselected");
        }
    }

    public void generateUserFleetGrid() {
        userFleet.getChildren().clear();
        userFleet.getColumnConstraints().clear();
        userFleet.getRowConstraints().clear();
        for (int i = 0; i < 10; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(Priority.SOMETIMES);
            column.setMinWidth(10.0);
            column.setPrefWidth(30.0);
            userFleet.getColumnConstraints().add(column);
        }
        for (int i = 0; i < 10; i++) {
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.SOMETIMES);
            row.setMinHeight(10.0);
            row.setPrefHeight(30.0);
            userFleet.getRowConstraints().add(row);
        }
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Pane cell = new Pane();
                cell.getStyleClass().add("userCell");
                cell.setPrefSize(30, 30);
                userFleet.add(cell, col, row);
            }
        }
    }

    public void generateMachineFleetGrid() {
        machinesFleet.getChildren().clear();
        machinesFleet.getColumnConstraints().clear();
        machinesFleet.getRowConstraints().clear();

        for (int i = 0; i < 10; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(Priority.SOMETIMES);
            column.setMinWidth(10.0);
            column.setPrefWidth(30.0);
            machinesFleet.getColumnConstraints().add(column);
        }
        for (int i = 0; i < 10; i++) {
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.SOMETIMES);
            row.setMinHeight(10.0);
            row.setPrefHeight(30.0);
            machinesFleet.getRowConstraints().add(row);
        }
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Pane cell = new Pane();
                cell.getStyleClass().add("machineCell");
                cell.setPrefSize(30, 30);
                machinesFleet.add(cell, col, row);
            }
        }
    }

    private void showMessage(String msg) {
        messageLabel.setText(msg);
        messageLabel.setVisible(true);
        FadeTransition transition = new FadeTransition(Duration.seconds(2), messageLabel);
        transition.setFromValue(1);
        transition.setToValue(0);
        transition.play();
    }


}