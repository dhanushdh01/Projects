package org.example.Models;

import org.example.Strategies.PlayingStrategies.BotPlayingStrategies;

public class Bot extends Player{
    private BotDiffLevels botDiffLevels;
    private BotPlayingStrategies botPlayingStrategies;

    public Bot(String name, Symbol symbol, PlayerType playerType, int id,BotDiffLevels botDiffLevels,BotPlayingStrategies botPlayingStrategies) {

        super(name, symbol, playerType, id);
        this.botDiffLevels = botDiffLevels;
        this.botPlayingStrategies = botPlayingStrategies;

    }
    @Override
    public Move makeMove(Board board){
        // How Bot will make a move.
        //Bot will make a move based on it's level(Easy,Medium,Hard).
        Move move = botPlayingStrategies.makeMove(board);
        move.setPlayer(this);
        return move;
    }
    // --------------- Getters & Setters ---------------------

    public BotDiffLevels getBotDiffLevels() {
        return botDiffLevels;
    }

    public void setBotDiffLevels(BotDiffLevels botDiffLevels) {
        this.botDiffLevels = botDiffLevels;
    }

    public BotPlayingStrategies getBotPlayingStrategies() {
        return botPlayingStrategies;
    }

    public void setBotPlayingStrategies(BotPlayingStrategies botPlayingStrategies) {
        this.botPlayingStrategies = botPlayingStrategies;
    }

}
