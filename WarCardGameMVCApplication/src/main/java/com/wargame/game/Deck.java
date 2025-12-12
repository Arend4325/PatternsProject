package com.wargame.game;

public class Deck {

    private final Card[] cards;
    private int numCards;

    public Deck() {
        cards = new Card[54];
        int index = 0;

        for (int suit = 1; suit <= 4; suit++) {
            for (int val = 1; val <= 13; val++) {
                cards[index++] = new Card(suit, val);
            }
        }

        cards[index++] = new Joker(14); // jokers always win so value is highest
        cards[index] = new Joker(14);

        numCards = 54;
    }

    public Deck(int capacity) {
        cards = new Card[capacity];
        numCards = 0;
    }

    public void shuffle() {
        for (int i = numCards - 1; i > 0; i--) {
            int rand = (int) (Math.random() * (i + 1));
            Card temp = cards[i];
            cards[i] = cards[rand];
            cards[rand] = temp;
        }
    }

    public void addToDeck(Card card) {
        if (numCards >= cards.length) {
            System.err.println("Deck overflow! Discarding card: " + card);
            return;
        }
        cards[numCards++] = card;
    }

    //this method makes dealing work like a queue to imitate dealing from the top of the deck (front of the queue) and adding it to the bottom of the deck (end of the queue)
    //it fixes our issue with the same cards always showing every round
    public Card dealCard() {
        if (numCards == 0) return null;

        Card top = cards[0];


        for (int i = 1; i < numCards; i++) {
            cards[i - 1] = cards[i];
        }

        cards[--numCards] = null;
        return top;
    }


    public Card dealCard(int index) {
        return cards[index];
    }

    public int getNumCards() {
        return numCards;
    }

    public void transferCardsFrom(Deck other) {

        while (other.numCards > 0) {
            this.addToDeck(other.dealCard());
        }
    }


    public void printDeck() {
        if (numCards == 0) {
            System.out.println("Empty deck");
            return;
        }

        for (int i = 0; i < numCards; i++) {
            System.out.println(cards[i]);
        }
    }
}
