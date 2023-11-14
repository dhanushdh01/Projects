package org.example.Strategies.WinningStrategies;

import org.example.Models.Board;
import org.example.Models.Move;
import org.example.Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements PlayerWinningStrategy{
    Map<Symbol,Integer> right_left_DiagonalMap = new HashMap<>();
    Map<Symbol,Integer> left_right_DiagonalMap = new HashMap<>();
    @Override
    public boolean checkWinner(Move move, Board board){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        // left to right diagonal
        if(row == col){
            if(left_right_DiagonalMap.containsKey(symbol)){
                left_right_DiagonalMap.put(symbol,left_right_DiagonalMap.get(symbol)+1);
            }else{
                left_right_DiagonalMap.put(symbol,1);
            }
        }
        // checking winner for left to right
        if (row == col && left_right_DiagonalMap.get(symbol) == board.getSize()){
            return true;
        }

        // right to left diagonal  row + col == board.size() -1
        if(row + col == board.getSize()-1){
            if(right_left_DiagonalMap.containsKey(symbol)){
                right_left_DiagonalMap.put(symbol,right_left_DiagonalMap.get(symbol)+1);
            }else{
                right_left_DiagonalMap.put(symbol,1);
            }
        }
        if( row + col == board.getSize() - 1 && right_left_DiagonalMap.get(symbol) == board.getSize()){
            return true;
        }
        return false;
    }

}
