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
            System.err.println("Error adding player: " + e.getMessage());
        }

        return false;
    }


    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM players ORDER BY playerID ASC";

        try (Connection conn = ConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                players.add(new Player(
                        rs.getInt("playerID"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getInt("totalWins")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving players: " + e.getMessage());
        }

        return players;
    }


    public Player getPlayerById(int playerID) {
        String sql = "SELECT * FROM players WHERE playerID = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, playerID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Player(
                        rs.getInt("playerID"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getInt("totalWins")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving player: " + e.getMessage());
        }

        return null;
    }

    public boolean updatePlayer(Player p) {
        String sql = "UPDATE players SET firstName = ?, lastName = ?, email = ? WHERE playerID = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getFirstName());
            stmt.setString(2, p.getLastName());
            stmt.setString(3, p.getEmail());
            stmt.setInt(4, p.getPlayerID());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating player: " + e.getMessage());
            return false;
        }
    }


    public void increaseWins(int playerID) {
        String sql = "UPDATE players SET totalWins = totalWins + 1 WHERE playerID = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, playerID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error increasing wins: " + e.getMessage());
        }
    }

    public boolean deletePlayer(int playerID) {
        String sql = "DELETE FROM players WHERE playerID = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, playerID);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting player: " + e.getMessage());
            return false;
        }
    }
}
