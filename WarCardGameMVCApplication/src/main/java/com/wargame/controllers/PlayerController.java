package com.wargame.controllers;

import com.wargame.ConnectionManager;
import com.wargame.models.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerController {

    public boolean addPlayer(Player player) {
        String sql = "INSERT INTO players (firstName, lastName, email, totalWins) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, player.getFirstName());
            stmt.setString(2, player.getLastName());
            stmt.setString(3, player.getEmail());
            stmt.setInt(4, player.getTotalWins());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    player.setPlayerID(rs.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM players";

        try (Connection conn = ConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Player p = new Player(
                        rs.getInt("playerID"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getInt("totalWins")
                );
                players.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return players;
    }

    public void increaseWins(int playerID) {
        String sql = "UPDATE players SET totalWins = totalWins + 1 WHERE playerID = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, playerID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
