package com.wargame.controllers;

import com.wargame.ConnectionManager;
import com.wargame.models.Game;

import java.sql.*;

public class GameController {

    // Inserts a completed game into the DB
    public int saveGame(Game game) {
        String sql = "INSERT INTO games (player1ID, player2ID, winnerID, datePlayed, totalRounds, totalWars) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, game.getPlayer1ID());
            stmt.setInt(2, game.getPlayer2ID());
            stmt.setInt(3, game.getWinnerID());
            stmt.setTimestamp(4, Timestamp.valueOf(game.getDatePlayed()));
            stmt.setInt(5, game.getTotalRounds());
            stmt.setInt(6, game.getTotalWars());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int generatedID = rs.getInt(1);
                    game.setGameID(generatedID);
                    return generatedID;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
