package org.example.Strategies.WinningStrategies;

import org.example.Models.Board;
import org.example.Models.Move;

public interface WinningStrategy {
    boolean checkWinner(Move move, Board board);
}
