package com.example.fpoeminiproject4.interfaces;

import java.io.Serializable;
import java.util.List;

public interface MainBoardInterface extends Serializable {
    void startBoard();
    void setShips();
    int[][] getShotGrid();
    int[] shot();
    int[] smartShot();
    boolean canPlaced(int row, int column, int size, boolean horizontal);
}