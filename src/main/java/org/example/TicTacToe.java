package org.example;

import org.example.Controllers.GameController;
import org.example.Models.*;
import org.example.Strategies.PlayingStrategies.EasyBotPlayingStrategies;
import org.example.Strategies.WinningStrategies.ColWinningStrategy;
import org.example.Strategies.WinningStrategies.DiagonalWinningStrategy;
import org.example.Strategies.WinningStrategies.RowWinningStrategy;
import org.example.Strategies.WinningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) throws Exception {
        GameController gameController = new GameController();
        Scanner sc = new Scanner(System.in);

        // Enter the number of Board Size
        System.out.println("Enter the number of Board Size: ");
        int boardSize = sc.nextInt();

        // Enter the number of Players
        int numberOfPlayers = 2;
        System.out.println("The Default number of Players are 2. Do you want to change it? (Y/N)");
        String changePlayers = sc.next();
        if (changePlayers.equals("Y")) {
            System.out.println("Enter the number of Players: ");
            numberOfPlayers = sc.nextInt();
        }

        List<Player> players = new ArrayList<>();
        List<Character> playerSymbols = new ArrayList<>();

        for(int i = 1; i <= numberOfPlayers; i++) {
            System.out.println(("Is Player " + i + " a bot? (Y/N)"));
            String isBot = sc.next();

            System.out.println("Enter the symbol for player : " + i + " : ");
            char symbol = sc.next().charAt(0);
            while (playerSymbols.contains(symbol)) {
                System.out.println("Symbol already taken. Please enter a different symbol: " + i + " : ");
                symbol = sc.next().charAt(0);
            }
            playerSymbols.add(symbol);

            if (isBot.equals("Y")) {
                System.out.println("Enter the difficulty level for bot (EASY/MEDIUM/HARD) : ");
                String difficulty = sc.next();
                BotDiffLevels botDiffLevel = BotDiffLevels.valueOf(difficulty);

                //Creating bot player
                players.add(new Bot("Bot" + i,new Symbol(symbol), PlayerType.Bot,i, botDiffLevel,new EasyBotPlayingStrategies()));
            }else {
                // Creating Human Player
                System.out.println("Enter the name for player : " + i + " : ");
                String name = sc.next();
                players.add(new Player(name,new Symbol(symbol),PlayerType.Human,i));
            }
        }

        //Define winning criteria
        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());

        // Start the Game
        System.out.println("Starting the game ...");
        Game game = gameController.startGame(boardSize,players,winningStrategies);

        // Start playing the game
        System.out.println("Game started ...");
        while(gameController.getGameStatus(game).equals( GameStatus.IN_Progress)){
            //Display the board
            System.out.println("Current Board : ************");
            gameController.diaplayBoard(game);

            //Undo the move
            System.out.println("Want to undo the move? (Y/N)");
            String undo = sc.next();
            if (undo.equals("Y")) {
                gameController.undo(game);
            }

            //Make a move
            gameController.makeMove(game);

            //Check if game is over
            if (gameController.getGameStatus(game).equals(GameStatus.Ended)) {
                //Display the board
                gameController.diaplayBoard(game);
                //Display the winner
                System.out.println("Game Over. Winner is .... ");
                if (game.getWinner() != null) {
                    System.out.println(gameController.getWinner(game).getName());
                    System.out.println("Want to play again? (Y/N)");
                    String playAgain = sc.next();
                    if (playAgain.equals("Y")) {
                        game = gameController.startGame(boardSize,players,winningStrategies);
                    } else {
                        System.out.println("Thanks for playing. Exiting the game...");
                        break;
                    }
                }else {
                    System.out.println("Game is a draw");
                    System.out.println("Want to play again? (Y/N)");
                    String playAgain = sc.next();
                    if (playAgain.equals("Y")) {
                        game = gameController.startGame(boardSize,players,winningStrategies);
                    } else {
                        break;
                    }
                }
            }
        }
        sc.close();
    }
}