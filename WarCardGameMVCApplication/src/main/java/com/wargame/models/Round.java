package com.wargame.models;

public class Round {
    private int roundID;
    private int gameID;
    private int roundNumber;
    private String player1Card;
    private String player2Card;
    private int roundWinner;    //playerID OR 0 (tie)
    private boolean isWarRound;

    public Round() {}

    // Constructor for inserting into DB
    public Round(int gameID, int roundNumber, String player1Card, String player2Card,
                 int roundWinner, boolean isWarRound) {
        this.gameID = gameID;
        this.roundNumber = roundNumber;
        this.player1Card = player1Card;
        this.player2Card = player2Card;
        this.roundWinner = roundWinner;
        this.isWarRound = isWarRound;
    }

    public Round(int roundID, int gameID, int roundNumber, String player1Card, String player2Card,
                 int roundWinner, boolean isWarRound) {
        this.roundID = roundID;
        this.gameID = gameID;
        this.roundNumber = roundNumber;
        this.player1Card = player1Card;
        this.player2Card = player2Card;
        this.roundWinner = roundWinner;
        this.isWarRound = isWarRound;
    }

    public int getRoundID() { return roundID; }
    public void setRoundID(int roundID) { this.roundID = roundID; }

    public int getGameID() { return gameID; }
    public void setGameID(int gameID) { this.gameID = gameID; }

    public int getRoundNumber() { return roundNumber; }
    public void setRoundNumber(int roundNumber) { this.roundNumber = roundNumber; }

    public String getPlayer1Card() { return player1Card; }
    public void setPlayer1Card(String player1Card) { this.player1Card = player1Card; }

    public String getPlayer2Card() { return player2Card; }
    public void setPlayer2Card(String player2Card) { this.player2Card = player2Card; }

    public int getRoundWinner() { return roundWinner; }
    public void setRoundWinner(int roundWinner) { this.roundWinner = roundWinner; }

    public boolean isWarRound() { return isWarRound; }
    public void setWarRound(boolean warRound) { isWarRound = warRound; }
}
