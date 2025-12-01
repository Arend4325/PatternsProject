package com.wargame.models;

public class Round {

    private int roundID;
    private int gameID;
    private int roundNumber;
    private String player1Card;
    private String player2Card;
    private int roundWinner; // 0 = tie
    private boolean isWarRound;

    public Round() {}

    // Used when saving
    public Round(int gameID, int roundNumber, String player1Card, String player2Card,
                 int roundWinner, boolean isWarRound) {
        this.gameID = gameID;
        this.roundNumber = roundNumber;
        this.player1Card = player1Card;
        this.player2Card = player2Card;
        this.roundWinner = roundWinner;
        this.isWarRound = isWarRound;
    }

    // Used when loading from DB
    public Round(int roundID, int gameID, int roundNumber, String player1Card,
                 String player2Card, int roundWinner, boolean isWarRound) {
        this.roundID = roundID;
        this.gameID = gameID;
        this.roundNumber = roundNumber;
        this.player1Card = player1Card;
        this.player2Card = player2Card;
        this.roundWinner = roundWinner;
        this.isWarRound = isWarRound;
    }

    public int getRoundID() {
        return roundID;
    }

    public int getGameID() {
        return gameID;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public String getPlayer1Card() {
        return player1Card;
    }

    public String getPlayer2Card() {
        return player2Card;
    }

    public int getRoundWinner() {
        return roundWinner;
    }

    public boolean isWarRound() {
        return isWarRound;
    }

}
