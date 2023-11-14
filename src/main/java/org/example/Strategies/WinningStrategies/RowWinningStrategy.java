package org.example.Strategies.WinningStrategies;

import org.example.Models.Board;
import org.example.Models.Move;
import org.example.Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements PlayerWinningStrategy{
    private Map<Integer, Map<Symbol,Integer>> rowMaps = new HashMap<>();
    @Override
    public boolean checkWinner(Move move, Board board){
        Symbol symbol = move.getPlayer().getSymbol();
        int row = move.getCell().getRow();
        if(!rowMaps.containsKey((row))){
            rowMaps.put(row,new HashMap<>());
        }
        Map<Symbol,Integer> curr_Row = rowMaps.get(row);
        if(curr_Row.containsKey(symbol)){
            curr_Row.put(symbol,curr_Row.get(symbol)+1);
        }else{
            curr_Row.put(symbol,1);
        }
        return curr_Row.get(symbol) == board.getSize();
    }
}
