package com.wargame.controllers;

import com.wargame.ConnectionManager;
import com.wargame.models.Round;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoundController {

    public void saveRound(Round round) {
        String sql = "INSERT INTO rounds (gameID, roundNumber, player1Card, player2Card, roundWinner, isWarRound) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, round.getGameID());
            stmt.setInt(2, round.getRoundNumber());
            stmt.setString(3, round.getPlayer1Card());
            stmt.setString(4, round.getPlayer2Card());
            stmt.setInt(5, round.getRoundWinner());
            stmt.setBoolean(6, round.isWarRound());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error saving round: " + e.getMessage());
        }
    }


    public List<Round> getRoundsForGame(int gameID) {
        List<Round> rounds = new ArrayList<>();
        String sql = "SELECT * FROM rounds WHERE gameID = ? ORDER BY roundNumber ASC";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, gameID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                rounds.add(new Round(
                        rs.getInt("roundID"),
                        rs.getInt("gameID"),
                        rs.getInt("roundNumber"),
                        rs.getString("player1Card"),
                        rs.getString("player2Card"),
                        rs.getInt("roundWinner"),      // returns 0 for ties
                        rs.getBoolean("isWarRound")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error loading rounds: " + e.getMessage());
        }

        return rounds;
    }
}
