package org.example.Models;

public class Player {
    private String name;
    private Long id;
    private Symbol symbol;
    private PlayerType playerType;
    public  Player(String name,Symbol symbol,PlayerType playerType,Long id){
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
