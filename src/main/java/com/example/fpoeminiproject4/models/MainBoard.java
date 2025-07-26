package com.example.fpoeminiproject4.models;

import com.example.fpoeminiproject4.interfaces.MainBoardInterface;

import java.util.*;

public class MainBoard implements MainBoardInterface {
    private int[][] board = new int[10][10];
    private final int[][] shotGrid = new int[10][10];
    private final List<int[]> shipCoordinatesList = new ArrayList<>();
    private final List<int[]> shots = new ArrayList<>();
    private final Random random = new Random();
    ArrayList<Ship> ships = new ArrayList<>();

    public MainBoard() {
        ships.add(new Ship(4,4, 1));
        ships.add(new Ship(3,3, 2));
        ships.add(new Ship(2,2, 3));
        ships.add(new Ship(1,1, 4));
        startBoard();
    }

    @Override
    public void startBoard() {
        for (int[] nums : board) {
            Arrays.fill(nums, 0);
        }
        setShips();
    }

    @Override
    public int[][] getShotGrid() {
        return shotGrid;
    }

    @Override
    public void setShips() {
        for (Ship ship : ships) {
            int amount = ship.getShipAmount();
            for (int i = 0; i < amount; i++) {
                int size = ship.getShipSize();
                boolean placed = false;
                int aux = ship.getShipType();

                while (!placed) {
                    int row = random.nextInt(10);
                    int column = random.nextInt(10);
                    boolean horizontal = random.nextBoolean();

                    if (canPlaced(row, column, size, horizontal)) {
                        for (int j = 0; j < size; j++) {
                            if (horizontal) {
                                board[row][column + j] = aux;
                            } else {
                                board[row + j][column] = aux;
                            }
                        }
                        int endX = horizontal ? row : row + size - 1;
                        int endY = horizontal ? column + size - 1 : column;
                        int orientation = horizontal ? 1 : 0;

                        shipCoordinatesList.add(new int[]{row, column, endX, endY, orientation, aux, 0});

                        placed = true;
                    }
                }
            }
        }
    }

    @Override
    public boolean canPlaced(int row, int column, int size, boolean horizontal) {
        if (horizontal) {
            if (column + size > 10) return false;
            for (int i = 0; i < size; i++) {
                if (board[row][column + i] != 0) {
                    return false;
                }
            }
        } else {
            if (row + size > 10) return false;
            for (int i = 0; i < size; i++) {
                if (board[row + i][column] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int[] shot() {
        int x, y;
        boolean isUnique;

        do {
            x = random.nextInt(10);
            y = random.nextInt(10);
            isUnique = true;

            for (int[] shot : shots) {
                if (shot[0] == x && shot[1] == y) {
                    isUnique = false;
                    break;
                }
            }
        } while (!isUnique);

        shots.add(new int[]{x, y});
        return new int[]{x, y};
    }

    @Override
    public int[] smartShot() {
        if (shots.isEmpty()) {
            return shot();
        }

        int[] lastShot = shots.get(shots.size() - 1);
        List<int[]> directions = new ArrayList<>();
        directions.add(new int[]{0, 1});
        directions.add(new int[]{0, -1});
        directions.add(new int[]{1, 0});
        directions.add(new int[]{-1, 0});

        Collections.shuffle(directions);

        for (int[] direction : directions) {
            int newX = lastShot[0] + direction[0];
            int newY = lastShot[1] + direction[1];

            if (newX >= 0 && newX < 10 && newY >= 0 && newY < 10) {
                int[] newShot = new int[]{newX, newY};

                if (!isShot(newShot)) {
                    shots.add(newShot);
                    return newShot;
                }
            }
        }

        return shot();
    }

    private boolean isShot(int[] shot) {
        for (int[] existingShot : shots) {
            if (existingShot[0] == shot[0] && existingShot[1] == shot[1]) {
                return true;
            }
        }
        return false;
    }

}

