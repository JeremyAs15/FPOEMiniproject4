package com.example.fpoeminiproject4.models;

public class GameModel {

    private PositionBoard positionTable;

    public void newMatch(String nickname) {
        positionTable = new PositionBoard();

    }

    public PositionBoard getPositionTable() {
        return positionTable;
    }

}
