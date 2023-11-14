package org.example.Models;
import org.example.exceptions.GameInvalidationException;
import java.util.ArrayList;
import java.util.List;
import org.example.Strategies.WinningStrategies.PlayerWinningStrategy;

public class Game {
    private Board board;
    private List<Player> player;
    private GameStatus gameStatus;
    private Player winner;
    private int nextMovePlayerIndex;
    private List<Move> moves;
    private List<PlayerWinningStrategy> playerWinningStrategy;
    public static Builder getBuilder(){
        return new Builder();
    }
    public static class Builder{
        private int size;
        private List<Player> players;
        private List<PlayerWinningStrategy> playerWinningStrategies;
        public Builder setSize(int size){
            this.size = size;
            return this;
        }
        public Builder setPlayers(List<Player> players){
            this.players = players;
            return this;
        }
        public Builder setPlayerWinningStrategy(List<PlayerWinningStrategy> playerWinningStrategies){
            this.playerWinningStrategies = playerWinningStrategies;
            return this;
        }
        public boolean Validate(){
            for(int i = 0;i<size;i++){
                if(players.get(i).getSymbol() == players.get(i+1).getSymbol()){
                    return false;
                }
            }
            return true;
        }
        public Game build() throws Exception{
            // Validation
            if(Validate()){
                return new Game(size,players,playerWinningStrategies);
            }
            throw new GameInvalidationException("Invalid Game");
        }
    }
    private Game (int size,List<Player> players,List<PlayerWinningStrategy> playerWinningStrategies){
        this.board = new Board(size);
        this.gameStatus = GameStatus.IN_Progress;
        this.nextMovePlayerIndex = 0;
        this.moves = new ArrayList<>();
        this.playerWinningStrategy = playerWinningStrategies;
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

    }
    public boolean validMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        if (row >= 0 && row < board.getSize() && col >=0  && col < board.getSize() &&
                board.getBoard().get(row).get(col).getCellStatus().equals(CellStatus.EMPTY)){
            return true;
        }
        return false;

    }
    public boolean checkWinner(Move move,Board board){
        for(PlayerWinningStrategy playerWinningStrategy1 : playerWinningStrategy){
            if(playerWinningStrategy1.checkWinner(move,board)){
                return true;
            }
        }
        return false;
    }
    public void makeMove(){
        // Current player to make a move.

    }

}
