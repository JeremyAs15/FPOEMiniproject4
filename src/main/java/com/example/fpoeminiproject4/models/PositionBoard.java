package com.example.fpoeminiproject4.models;

import com.example.fpoeminiproject4.interfaces.PositionBoardInterface;

import java.util.ArrayList
        ;
import java.util.List;

public class PositionBoard implements PositionBoardInterface {
    private final int[][] positionTable = new int[10][10];
    private final int[][] shotGrid = new int[10][10];
    public List<int[]> getShipCoordinatesList() {
        return shipCoordinatesList;
    }
    private final List<int[]> shipCoordinatesList = new ArrayList<>();
    ArrayList<Ship> ships = new ArrayList<Ship>();

    public PositionBoard() {
        ships.add(null);
        ships.add(new Ship(1,1, 4));
        ships.add(new Ship(2,2, 3));
        ships.add(new Ship(3,3, 2));
        ships.add(new Ship(4,4, 1));
    }

    @Override
    public int[][] getBoard() {
        return positionTable;
    }

    @Override
    public int[][] getShotGrid() {
        return shotGrid;
    }

    @Override
    public ArrayList<Ship> getShips() {
        return ships;
    }

    @Override
    public void setShipPosition(int shipIndex,int row, int col, int orientation) {
        Ship ship = ships.get(shipIndex);
        int shipSize = ship.getShipSize();
        int shipType = ship.getShipType();

        if (orientation == 0){
            for (int i = row; i < shipSize + row; i++){
                positionTable[i][col] = shipType;
            }
            shipCoordinatesList.add(new int[]{row, col, row + shipSize - 1, col, orientation, shipType });
        }
        else if (orientation == 1){
            for (int j = col; j < shipSize + col; j++){
                positionTable[row][j] = shipType;
            }
            shipCoordinatesList.add(new int[]{row, col, row, col + shipSize - 1, orientation, shipType });
        }
        ship.setShipAmount(ship.getShipAmount() - 1);
    }

    @Override
    public boolean checkPosition(int shipIndex, int row, int col, int orientation) {
        Ship ship = ships.get(shipIndex);
        int shipSize = ship.getShipSize();
        int checkCounter = 0;

        if (orientation == 0){
            for (int i = row; i < shipSize + row; i++){
                if (positionTable[i][col] == 0)
                    checkCounter++;
            }
        }
        else if (orientation == 1){
            for (int j = col; j < shipSize + col; j++){
                if (positionTable[row][j] == 0)
                    checkCounter++;
            }
        }

        if (checkCounter == shipSize)
            return true;
        return false;
    }

    @Override
    public boolean checkAmount(int shipIndex){
        Ship ship = ships.get(shipIndex);
        int shipAmount = ship.getShipAmount();

        if (shipAmount == 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean isBoardFull() {
        for (Ship ship : ships) {
            if (ship == null) continue;
            if (ship.getShipAmount() > 0) {
                return false;
            }
        }
        return true;
    }

}
