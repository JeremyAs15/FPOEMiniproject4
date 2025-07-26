package com.example.fpoeminiproject4.interfaces;

import com.example.fpoeminiproject4.models.Ship;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public interface PositionBoardInterface {
    int[][] getBoard();
    int[][] getShotGrid();
    ArrayList<Ship> getShips();
    void setShipPosition(int shipIndex, int row, int col, int orientation);
    boolean checkPosition(int shipIndex, int row, int col, int orientation);
    boolean checkAmount(int shipIndex);
    boolean isBoardFull();
    List<int[]> getShipCoordinatesList();
}
