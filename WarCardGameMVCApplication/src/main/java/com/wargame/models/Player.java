package com.wargame.models;

public class Player {
    private int playerID;
    private String firstName;
    private String lastName;
    private String email;
    private int totalWins;

    public Player() {}

    public Player(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.totalWins = 0;
    }

    public Player(int playerID, String firstName, String lastName,
                  String email, int totalWins) {
        this.playerID = playerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.totalWins = totalWins;
    }

    public int getPlayerID() { return playerID; }
    public void setPlayerID(int playerID) { this.playerID = playerID; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getTotalWins() { return totalWins; }
    public void setTotalWins(int totalWins) { this.totalWins = totalWins; }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + email + "), wins: " + totalWins;
    }
}
