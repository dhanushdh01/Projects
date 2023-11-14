package org.example.Models;

import org.example.Strategies.PlayingStrategies.BotPlayingStrategies;

public class Bot extends Player{
    private BotDiffLevels botDiffLevels;
    private BotPlayingStrategies botPlayingStrategies;

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

    public Bot(String name, Symbol symbol, PlayerType playerType, Long id,BotDiffLevels botDiffLevels,BotPlayingStrategies botPlayingStrategies) {

        super(name, symbol, playerType, id);
        this.botDiffLevels = botDiffLevels;
        this.botPlayingStrategies = botPlayingStrategies;

    }
}
