package org.example.Strategies.PlayingStrategies;
import org.example.Models.Move;
import org.example.Models.Board;

public interface BotPlayingStrategies {
    Move makeMove(Board board);
}
