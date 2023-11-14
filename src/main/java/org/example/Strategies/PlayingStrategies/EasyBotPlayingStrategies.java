package org.example.Strategies.PlayingStrategies;
import org.example.Models.Board;
import org.example.Models.Cell;
import org.example.Models.CellStatus;
import org.example.Models.Move;
import java.util.List;
public class EasyBotPlayingStrategies implements BotPlayingStrategies{
    @Override
    public Move makeMove(Board board){
        // in Easy Bot Playing Strategies bot will check cell Status to make a move
        // If cell is available then make a move
        for(List<Cell> row : board.getBoard()){
            for(Cell cell : row){
                if(cell.getCellStatus().equals(CellStatus.EMPTY)){
                    // Bot can make a move
                    return new Move(cell,null);
                }
            }
        }
        return null;
    }
}
