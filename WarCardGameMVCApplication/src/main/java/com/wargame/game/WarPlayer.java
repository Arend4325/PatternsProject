package com.wargame.game;

import com.wargame.factory.DeckFactory;

public class WarPlayer {

    private final String name;
    private final Deck playerDeck;

    public WarPlayer(String name) {
        this.name = name;
        this.playerDeck = DeckFactory.createEmptyDeck();
    }

    public String getName() {
        return name;
    }

    public Deck getPlayerDeck() {
        return playerDeck;
    }
}
