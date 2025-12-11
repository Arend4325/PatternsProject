package com.wargame.factory;

import com.wargame.game.Deck;

public class DeckFactory {

    public static Deck createShuffledDeck() {
        Deck d = new Deck();
        for (int i = 0; i < 4; i++) {
            d.shuffle();
        }
        return d;
    }

    public static Deck createEmptyDeck() {
        return new Deck(54);
    }
}
