package org.example.Controllers;
import org.example.Models.GameStatus;
import org.example.Models.Player;
import org.example.Models.Game;
import org.example.Strategies.WinningStrategies.PlayerWinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(List<Player> players, int boardSize, List<PlayerWinningStrategy> playerWinningStrategy) throws Exception{
        return Game.getBuilder()
                .setSize(boardSize)
                .setPlayers(players)
                .setPlayerWinningStrategy(playerWinningStrategy)
                .build();
    }
    public void makeMove(Game game){
        game.makeMove();
    }
    public void diaplayBoard(Game game){
        game.printBoard();
    }
    public Player getWinner(Game game){
        return game.getWinner();
    }
    public void undo(Game game){
        game.undo();
    }
    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }


}
