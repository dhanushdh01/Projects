package org.example;

import org.example.Controllers.GameController;
import org.example.Models.*;
import org.example.Strategies.PlayingStrategies.BotPlayingStrategies;
import org.example.Strategies.PlayingStrategies.EasyBotPlayingStrategies;
import org.example.Strategies.WinningStrategies.ColWinningStrategy;
import org.example.Strategies.WinningStrategies.DiagonalWinningStrategy;
import org.example.Strategies.WinningStrategies.WinningStrategy;
import org.example.Strategies.WinningStrategies.RowWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TicTacToe {
    public static void main(String[] args) throws Exception {
        GameController gameController = new GameController();
//        System.out.println("Enter the Game Size : ");
//        Scanner sc = new Scanner(System.in);
        int Size = 3;
        int numberOfPlayers = Size -1;
        List<Player> players = new ArrayList<>();
        players.add(new Player("Dhanush",new Symbol('X'),PlayerType.Human,1));
        players.add(new Bot("Bot",new Symbol('O'),PlayerType.Bot,2,BotDiffLevels.Easy,new EasyBotPlayingStrategies()));
        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());

        // Start the Game
        Game game = gameController.startGame(Size, players,winningStrategies);
        //Start Playing the Game
        while(gameController.getGameStatus(game).equals(GameStatus.IN_Progress)){
            //Display Board
            System.out.println("This is the current state of Board ");
            gameController.diaplayBoard(game);
            //UNDO function
            gameController.makeMove(game);
            if(gameController.getGameStatus(game).equals(GameStatus.Ended)){
                //Player X is won the game
                gameController.diaplayBoard(game);
                System.out.println("Winner is "+ gameController.getWinner(game).getName());
            }else{
                System.out.println("Game has Draw");
            }
        }
    }
}