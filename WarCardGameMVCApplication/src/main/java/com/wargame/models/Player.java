package com.wargame.models;

public class Player {

    private int playerID;
    private String firstName;
    private String lastName;
    private String email;
    private int totalWins;

    public Player() {}

    //before saving to DB
    public Player(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.totalWins = 0;
    }

    //loaded from DB
    public Player(int playerID, String firstName, String lastName,
                  String email, int totalWins) {
        this.playerID = playerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.totalWins = totalWins;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + email + "), wins: " + totalWins;
    }
}
