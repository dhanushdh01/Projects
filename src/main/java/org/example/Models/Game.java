package org.example.Models;
import org.example.exceptions.GameInvalidationException;
import org.example.Strategies.WinningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;


public class Game {
    private Board board;
    private List<Player> player;
    private GameStatus gameStatus;
    private Player winner;
    private int nextMovePlayerIndex;
    private List<Move> moves;
    private final List<WinningStrategy> winningStrategy;
    public static Builder getBuilder(){
        return new Builder();
    }
    public static class Builder{
        private int size;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
//        private Builder(){
//            this.players = new ArrayList<>();
//            this.winningStrategies = new ArrayList<>();
//            this.size = 0;
//        }
        public Builder setSize(int size){
            this.size = size;
            return this;
        }
        public Builder setPlayers(List<Player> players){
            this.players = players;
            return this;
        }
        public Builder setPlayerWinningStrategy(List<WinningStrategy> winningStrategies){
            this.winningStrategies = winningStrategies;
            return this;
        }
        public boolean Validate(){
//            for(int i = 0;i<size;i++){
//                if(players.get(i).getSymbol() == players.get(i+1).getSymbol()){
//                    return false;
//                }
//            }
            return true;
        }
        public Game build() throws Exception{
            // Validation
            if(!Validate()){
                throw new GameInvalidationException("Invalid Game");
            }
            return new Game(size,players, winningStrategies);
        }
    }
    private Game (int size,List<Player> players,List<WinningStrategy> winningStrategies){
        this.board = new Board(size);
        this.gameStatus = GameStatus.IN_Progress;
        this.nextMovePlayerIndex = 0;
        this.moves = new ArrayList<>();
        this.winningStrategy = winningStrategies;
        this.player = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayer() {
        return player;
    }

    public void setPlayer(List<Player> player) {
        this.player = player;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }

    public void setNextMovePlayerIndex(int nextMovePlayerIndex) {
        this.nextMovePlayerIndex = nextMovePlayerIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
    public void printBoard(){
        board.displayBoard();
    }
    public void undo(){
        if(moves.size() == 0){
            System.out.println("No moves to undo");
            return;
        }

        Move lastMove = moves.get(moves.size()-1);

        Cell cell = lastMove.getCell();
        cell.setCellStatus(CellStatus.EMPTY);
        cell.setPlayer(null);

        moves.remove(moves.size()-1);

        nextMovePlayerIndex -= 1;
        nextMovePlayerIndex = (nextMovePlayerIndex + player.size()) % player.size();
        nextMovePlayerIndex = (nextMovePlayerIndex + player.size()) % player.size();
        gameStatus = GameStatus.IN_Progress;
        winner = null;

    }
    public boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        return row >= 0 && row < board.getSize() && col >= 0 && col < board.getSize() &&
                board.getBoard().get(row).get(col).getCellStatus().equals(CellStatus.EMPTY);

    }
    public boolean checkWinner(Board board,Move move){
        for(WinningStrategy winningStrategy1 : winningStrategy){
            if(winningStrategy1.checkWinner(move, board)){
                return true;
            }
        }
        return false;
    }
    public void makeMove(){
        // Current player to make a move.
        Player curr_Player = player.get(nextMovePlayerIndex);
        System.out.println("It's "+curr_Player.getName()+"'s move!");
        Move move = curr_Player.makeMove(this.board);
        System.out.println(curr_Player.getName()+" has made a move at this Row : "+move.getCell().getRow()+" and the col : "+move.getCell().getCol() +".");
        //Check the Validation to move on the Board.
        if(!validateMove(move)) {
            System.out.println("It's a Invalid move by a Player : " + curr_Player.getName() + " please select any other Valid Move");
            return;
        }
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell final_Cell_Move = board.getBoard().get(row).get(col);
        final_Cell_Move.setCellStatus(CellStatus.FILLED);
        final_Cell_Move.setPlayer(curr_Player);
        Move final_Move = new Move(final_Cell_Move,curr_Player);
        moves.add(final_Move);
        nextMovePlayerIndex += 1;
        nextMovePlayerIndex %=player.size();
        //Once a player has made a move , Check the winner.
        if(checkWinner(board,final_Move)){
            gameStatus = GameStatus.Ended;
            winner = curr_Player;
        }else if (moves.size() == board.getSize()*board.getSize()){
            // Game has Draw
            gameStatus = GameStatus.Draw;
        }

    }

}
