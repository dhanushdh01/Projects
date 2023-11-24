package org.example.Strategies.WinningStrategies;

import org.example.Models.Board;
import org.example.Models.Move;
import org.example.Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStrategy implements WinningStrategy {
    private Map<Integer, Map<Symbol,Integer>> colMaps = new HashMap<>();
    @Override
    public boolean checkWinner(Move move, Board board) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        if (!colMaps.containsKey(col)) {
            colMaps.put(col, new HashMap<>());
        }
        Map<Symbol,Integer> curr_Col = colMaps.get(col);
        if (curr_Col.containsKey(symbol)) {
            curr_Col.put(symbol, curr_Col.get(symbol) + 1);
        } else {
            curr_Col.put(symbol, 1);
        }
        return curr_Col.get(symbol) == board.getSize();
    }
}
