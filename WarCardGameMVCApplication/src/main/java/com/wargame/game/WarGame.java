package com.wargame.game;

import com.wargame.factory.DeckFactory;
import java.util.ArrayList;
import java.util.List;

public class WarGame {

    private static final int MAX_ROUNDS = 25;

    private final WarPlayer p1;
    private final WarPlayer p2;

    private final Deck tableDeck = DeckFactory.createEmptyDeck();

    private int totalRounds = 0;
    private int totalWars = 0;

    private int p1RoundWins = 0;
    private int p2RoundWins = 0;


    private final List<RoundRecord> roundHistory = new ArrayList<>();

    public WarGame(WarPlayer p1, WarPlayer p2) {
        this.p1 = p1;
        this.p2 = p2;
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

    public int getP1RoundWins() {
        return p1RoundWins;
    }

    public int getP2RoundWins() {
        return p2RoundWins;
    }

    public static class RoundRecord {
        public int roundNumber;
        public Card p1Card;
        public Card p2Card;
        public int winner;  // 1 = p1, 2 = p2, 0 = tie
        public boolean war;

        public RoundRecord(int roundNumber, Card c1, Card c2, int winner, boolean war) {
            this.roundNumber = roundNumber;
            this.p1Card = c1;
            this.p2Card = c2;
            this.winner = winner;
            this.war = war;
        }
    }

    public void setupGame() {

        //factory
        Deck deck = DeckFactory.createShuffledDeck();

        // deal 27 cards each (54 total)
        for (int i = 0; i < 27; i++) {
            p1.getPlayerDeck().addToDeck(deck.dealCard());
            p2.getPlayerDeck().addToDeck(deck.dealCard());
        }
    }

    public String playGame() {

        while (p1.getPlayerDeck().getNumCards() > 0 &&
                p2.getPlayerDeck().getNumCards() > 0 &&
                totalRounds < MAX_ROUNDS) {

            totalRounds++;

            Card c1 = p1.getPlayerDeck().dealCard();
            Card c2 = p2.getPlayerDeck().dealCard();

            tableDeck.addToDeck(c1);
            tableDeck.addToDeck(c2);

            int winner;

            if (c1.getValue() > c2.getValue()) {
                winner = 1;
                p1RoundWins++;   // <-- count win
                p1.getPlayerDeck().transferCardsFrom(tableDeck);
            }
            else if (c2.getValue() > c1.getValue()) {
                winner = 2;
                p2RoundWins++;   // <-- count win
                p2.getPlayerDeck().transferCardsFrom(tableDeck);
            }
            else {
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
        }

        // Winner by card count
        int p1Cards = p1.getPlayerDeck().getNumCards();
        int p2Cards = p2.getPlayerDeck().getNumCards();

        if (p1Cards != p2Cards) {
            return p1Cards > p2Cards ? p1.getName() : p2.getName();
        }

        // Sudden death tiebreaker
        while (true) {
            if (p1.getPlayerDeck().getNumCards() == 0) return p2.getName();
            if (p2.getPlayerDeck().getNumCards() == 0) return p1.getName();

            Card c1 = p1.getPlayerDeck().dealCard();
            Card c2 = p2.getPlayerDeck().dealCard();

            tableDeck.addToDeck(c1);
            tableDeck.addToDeck(c2);


            totalRounds++;

            int winner;
            if (c1.getValue() > c2.getValue()) {
                winner = 1;
                p1RoundWins++;
                p1.getPlayerDeck().transferCardsFrom(tableDeck);
            }
            else if (c2.getValue() > c1.getValue()) {
                winner = 2;
                p2RoundWins++;
                p2.getPlayerDeck().transferCardsFrom(tableDeck);
            }

            else {
                winner = 0;
            }


            roundHistory.add(new RoundRecord(
                    totalRounds,
                    c1,
                    c2,
                    winner,
                    winner == 0
            ));


            if (winner == 1) return p1.getName();
            if (winner == 2) return p2.getName();
        }
    }



}
