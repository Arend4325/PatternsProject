package com.wargame.game;

public class Joker extends Card {

    public Joker(int value) {
        super(0, value);
    }

    @Override
    public String getSuitAsString() {
        return "Joker";
    }

    @Override
    public String getValueAsString() {
        return "Joker";
    }

    @Override
    public String toString() {
        return "Joker";
    }
}
