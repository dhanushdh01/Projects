package org.example.Models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> board;
    public Board(int size){
        //Initialize a board of size
        this.board = new ArrayList<>(); // New Row Size of size
        this.size = size;
        for(int i = 0; i < size; i++) {
            board.add(new ArrayList<>()); // New Cols Size of size
            for(int j = 0; j < size; j++) {
                board.get(i).add(new Cell(i,j));
            }
        }
        public void displayBoard(){
            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    Cell cell = board.get(i).get(j);
                    if(cell.getCellStatus().equals(CellStatus.EMPTY)){
                        System.out.print("|  |");
                    }else{
                        System.out.print("|" + cell.getPlayer().getSymbol() + "|");
                    }
                }
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
