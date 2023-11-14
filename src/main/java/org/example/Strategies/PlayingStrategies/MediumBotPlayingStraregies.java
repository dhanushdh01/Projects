package org.example.Strategies.PlayingStrategies;

import org.example.Models.Board;
import org.example.Models.Cell;
import org.example.Models.CellStatus;
import org.example.Models.Move;

import java.util.List;

public class MediumBotPlayingStraregies implements BotPlayingStrategies{
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
        // Iterate over columns within each row
        for (int col = 0; col < board.getBoard().get(0).size(); col++) {
            for (List<Cell> row : board.getBoard()) {
                Cell cell = row.get(col);
                if (cell.getCellStatus().equals(CellStatus.EMPTY)) {
                    // Bot can make a move at this cell.
                    return new Move(cell, null);
                }
            }
        }
        // No empty cells found
        return null;
    }
}
