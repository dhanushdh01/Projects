package org.example.Models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> board;
    public Board(int n) {
        //Initialize a board of n
        this.size = n;
        this.board = new ArrayList<>(); // New Row Size of n
        for (int row = 0; row < n; row++) {
            board.add(new ArrayList<>()); // New Cols Size of n
            for (int col = 0; col < n; col++) {
                board.get(row).add(new Cell(row, col));
            }
        }
    }
    public void displayBoard() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Cell cell = board.get(row).get(col);
                if (cell.getCellStatus().equals(CellStatus.EMPTY)) {
                    System.out.print("   ");
                } else {
                    System.out.print(" " + cell.getPlayer().getSymbol().getSymbol() + " ");
                }
                if (col < size - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row < size - 1) {
                for (int col = 0; col < size; col++) {
                    System.out.print("---");
                    if (col < size - 1) {
                        System.out.print("+");
                    }
                }
                System.out.println();
            }
        }
    }
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    }


