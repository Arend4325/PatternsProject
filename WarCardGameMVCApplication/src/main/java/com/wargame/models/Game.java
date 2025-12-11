package com.wargame.models;

import java.time.LocalDateTime;

public class Game {

    private int gameID;
    private int player1ID;
    private int player2ID;
    private int winnerID;
    private LocalDateTime datePlayed;

    // arend: per-player round wins
    private int p1RoundWins;
    private int p2RoundWins;

    private int totalWars;

    public Game() {}

    // Constructor used BEFORE saving to DB
    public Game(int player1ID,
                int player2ID,
                int winnerID,
                int p1RoundWins,
                int p2RoundWins,
                int totalWars) {

        this.player1ID = player1ID;
        this.player2ID = player2ID;
        this.winnerID = winnerID;
        this.p1RoundWins = p1RoundWins;
        this.p2RoundWins = p2RoundWins;
        this.totalWars = totalWars;
        this.datePlayed = LocalDateTime.now();
    }

    // Constructor used AFTER loading from DB
    public Game(int gameID,
                int player1ID,
                int player2ID,
                int winnerID,
                LocalDateTime datePlayed,
                int p1RoundWins,
                int p2RoundWins,
                int totalWars) {

        this.gameID = gameID;
        this.player1ID = player1ID;
        this.player2ID = player2ID;
        this.winnerID = winnerID;
        this.datePlayed = datePlayed;
        this.p1RoundWins = p1RoundWins;
        this.p2RoundWins = p2RoundWins;
        this.totalWars = totalWars;
    }

    // ---- getters / setters ----

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

    public int getP1RoundWins() {
        return p1RoundWins;
    }

    public void setP1RoundWins(int p1RoundWins) {
        this.p1RoundWins = p1RoundWins;
    }

    public int getP2RoundWins() {
        return p2RoundWins;
    }

    public void setP2RoundWins(int p2RoundWins) {
        this.p2RoundWins = p2RoundWins;
    }

    public int getTotalWars() {
        return totalWars;
    }

    public void setTotalWars(int totalWars) {
        this.totalWars = totalWars;
    }
}
