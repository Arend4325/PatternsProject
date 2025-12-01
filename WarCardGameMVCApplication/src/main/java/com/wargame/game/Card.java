package com.wargame.game;

public class Card {

    private final int suit;   // 1–4
    private final int value;  // 1–13

    public Card(int suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public int getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public String getSuitAsString() {
        return switch (suit) {
            case 1 -> "Spades";
            case 2 -> "Hearts";
            case 3 -> "Diamonds";
            case 4 -> "Clubs";
            default -> "Unknown";
        };
    }

    public String getValueAsString() {
        return switch (value) {
            case 1 -> "Ace";
            case 11 -> "Jack";
            case 12 -> "Queen";
            case 13 -> "King";
            default -> String.valueOf(value); // numeric cards
        };
    }

    @Override
    public String toString() {
        return getValueAsString() + " of " + getSuitAsString();
    }
}
