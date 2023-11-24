package org.example.Models;

import java.util.Scanner;

public class Player {
    private String name;
    private int id;
    private Symbol symbol;
    private PlayerType playerType;
    public  Player(String name,Symbol symbol,PlayerType playerType,int id){
        this.name = name;
        this.symbol = symbol;
        this.playerType =playerType;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
    //Player needs to make move so ask the player to get the row and col
    public Move makeMove(Board board){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the row index : ");
        int row_Num = sc.nextInt();
        System.out.println("Enter the col index : ");
        int col_Num = sc.nextInt();
        System.out.println("--------------------");
        return new Move(new Cell(row_Num,col_Num),this);
    }
}
