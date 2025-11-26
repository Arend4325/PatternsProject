package com.wargame.controllers;

import com.wargame.ConnectionManager;
import com.wargame.models.Round;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoundController {

    // Insert a single round into the rounds table
    public void saveRound(Round round) {
        String sql = "INSERT INTO rounds (gameID, roundNumber, player1Card, player2Card, roundWinner, isWarRound) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, round.getGameID());
            stmt.setInt(2, round.getRoundNumber());
            stmt.setString(3, round.getPlayer1Card());
            stmt.setString(4, round.getPlayer2Card());
            stmt.setInt(5, round.getRoundWinner());    // may be 0 for tie/no winner
            stmt.setBoolean(6, round.isWarRound());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all rounds for a full game (for history screen)
    public List<Round> getRoundsForGame(int gameID) {
        List<Round> rounds = new ArrayList<>();
        String sql = "SELECT * FROM rounds WHERE gameID = ? ORDER BY roundNumber ASC";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, gameID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Round r = new Round(
                        rs.getInt("roundID"),
                        rs.getInt("gameID"),
                        rs.getInt("roundNumber"),
                        rs.getString("player1Card"),
                        rs.getString("player2Card"),
                        rs.getInt("roundWinner"),
                        rs.getBoolean("isWarRound")
                );
                rounds.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rounds;
    }
}
