package com.wargame.game;

public class WarPlayer {

    private final String name;
    private final Deck playerDeck;

    public WarPlayer(String name) {
        this.name = name;
        this.playerDeck = new Deck(54);
    }

    public String getName() {
        return name;
    }

    public Deck getPlayerDeck() {
        return playerDeck;
    }
}
