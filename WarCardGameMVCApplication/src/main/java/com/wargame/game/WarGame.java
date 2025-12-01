package com.wargame.game;

import java.util.ArrayList;
import java.util.List;

public class WarGame {

    private static final int MAX_ROUNDS = 25;

    private final WarPlayer p1;
    private final WarPlayer p2;

    private final Deck tableDeck = new Deck(54);

    private int totalRounds = 0;
    private int totalWars = 0;

    private final List<RoundRecord> roundHistory = new ArrayList<>();

    public WarGame(WarPlayer p1, WarPlayer p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public static class RoundRecord {
        public final int roundNumber;
        public final Card p1Card;
        public final Card p2Card;
        public final int winner;
        public final boolean war;

        public RoundRecord(int roundNumber, Card c1, Card c2, int winner, boolean war) {
            this.roundNumber = roundNumber;
            this.p1Card = c1;
            this.p2Card = c2;
            this.winner = winner;
            this.war = war;
        }
    }

    public int getTotalRounds() {
        return totalRounds;
    }

    public int getTotalWars() {
        return totalWars;
    }

    public List<RoundRecord> getRoundHistory() {
        return roundHistory;
    }

    public String getPlayer1Name() {
        return p1.getName();
    }

    public String getPlayer2Name() {
        return p2.getName();
    }

    public void setupGame() {

        Deck deck = new Deck();

        for (int i = 0; i < 4; i++) {
            deck.shuffle();
        }

        for (int i = 0; i < 27; i++) {
            p1.getPlayerDeck().addToDeck(deck.dealCard(2 * i));
            p2.getPlayerDeck().addToDeck(deck.dealCard(2 * i + 1));
        }
    }

    private int playSingleRound(boolean countToMax) {

        if (countToMax) {
            totalRounds++;
        }

        Card c1 = p1.getPlayerDeck().dealCard();
        Card c2 = p2.getPlayerDeck().dealCard();

        tableDeck.addToDeck(c1);
        tableDeck.addToDeck(c2);

        int winner;

        if (c1.getValue() > c2.getValue()) {
            winner = 1;
            p1.getPlayerDeck().transferCardsFrom(tableDeck);
        } else if (c2.getValue() > c1.getValue()) {
            winner = 2;
            p2.getPlayerDeck().transferCardsFrom(tableDeck);
        } else {
            winner = 0;
            totalWars++;
        }

        roundHistory.add(new RoundRecord(
                totalRounds,
                c1,
                c2,
                winner,
                winner == 0
        ));

        return winner;
    }

    public String playGame() {

        while (totalRounds < MAX_ROUNDS &&
                p1.getPlayerDeck().getNumCards() > 0 &&
                p2.getPlayerDeck().getNumCards() > 0) {

            playSingleRound(true);
        }

        int p1Cards = p1.getPlayerDeck().getNumCards();
        int p2Cards = p2.getPlayerDeck().getNumCards();

        if (p1Cards > p2Cards) return p1.getName();
        if (p2Cards > p1Cards) return p2.getName();

        while (true) {

            if (p1.getPlayerDeck().getNumCards() == 0) return p2.getName();
            if (p2.getPlayerDeck().getNumCards() == 0) return p1.getName();

            int result = playSingleRound(false);

            if (result == 1) return p1.getName();
            if (result == 2) return p2.getName();
        }
    }

}
