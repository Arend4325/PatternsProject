package com.wargame.models;

import java.time.LocalDateTime;

public class Game {

    private int gameID;
    private int player1ID;
    private int player2ID;
    private int winnerID;
    private LocalDateTime datePlayed;
    private int totalRounds;
    private int totalWars;

    public Game() {}

    // Constructor used BEFORE saving to DB
    public Game(int player1ID, int player2ID, int winnerID,
                int totalRounds, int totalWars) {
        this.player1ID = player1ID;
        this.player2ID = player2ID;
        this.winnerID = winnerID;
        this.totalRounds = totalRounds;
        this.totalWars = totalWars;
        this.datePlayed = LocalDateTime.now(); // default
    }

    // Constructor used AFTER loading from DB
    public Game(int gameID, int player1ID, int player2ID, int winnerID,
                LocalDateTime datePlayed, int totalRounds, int totalWars) {
        this.gameID = gameID;
        this.player1ID = player1ID;
        this.player2ID = player2ID;
        this.winnerID = winnerID;
        this.datePlayed = datePlayed;
        this.totalRounds = totalRounds;
        this.totalWars = totalWars;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getPlayer1ID() {
        return player1ID;
    }

    public int getPlayer2ID() {
        return player2ID;
    }

    public int getWinnerID() {
        return winnerID;
    }

    public LocalDateTime getDatePlayed() {
        return datePlayed;
    }

    public void setDatePlayed(LocalDateTime datePlayed) {
        this.datePlayed = datePlayed;
    }

    public int getTotalRounds() {
        return totalRounds;
    }

    public int getTotalWars() {
        return totalWars;
    }

}
