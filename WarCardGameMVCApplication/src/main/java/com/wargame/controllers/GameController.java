package com.wargame.controllers;

import com.wargame.database.ConnectionManager;
import com.wargame.models.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameController {

    // ==============================
    // SAVE GAME
    // ==============================
    public int saveGame(Game game) {
        String sql = """
                INSERT INTO games
                (player1ID, player2ID, winnerID, datePlayed,
                 p1RoundWins, p2RoundWins, totalWars)
                VALUES (?, ?, ?, NOW(), ?, ?, ?)
                """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, game.getPlayer1ID());
            stmt.setInt(2, game.getPlayer2ID());
            stmt.setInt(3, game.getWinnerID());
            stmt.setInt(4, game.getP1RoundWins());
            stmt.setInt(5, game.getP2RoundWins());
            stmt.setInt(6, game.getTotalWars());

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    game.setGameID(id);
                    return id;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }

        return -1;
    }


    // ==============================
    // GET ALL GAMES
    // ==============================
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM games ORDER BY datePlayed DESC";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                games.add(mapGame(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving all games: " + e.getMessage());
        }

        return games;
    }


    // ==============================
    // GET GAME BY ID
    // ==============================
    public Game getGameById(int gameID) {
        String sql = "SELECT * FROM games WHERE gameID = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, gameID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapGame(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving game by ID: " + e.getMessage());
        }

        return null;
    }


    // ==============================
    // GET GAMES FOR A PLAYER
    // ==============================
    public List<Game> getGamesForPlayer(int playerID) {
        List<Game> games = new ArrayList<>();

        String sql = """
                SELECT * FROM games
                WHERE player1ID = ? OR player2ID = ?
                ORDER BY datePlayed DESC
                """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, playerID);
            stmt.setInt(2, playerID);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                games.add(mapGame(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving games for player: " + e.getMessage());
        }

        return games;
    }


    // ==============================
    // DELETE GAME
    // ==============================
    public boolean deleteGame(int gameID) {
        String sql = "DELETE FROM games WHERE gameID = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, gameID);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting game: " + e.getMessage());
            return false;
        }
    }


    // ==============================
    // MAP RESULTSET → GAME OBJECT
    // ==============================
    private Game mapGame(ResultSet rs) throws SQLException {
        return new Game(
                rs.getInt("gameID"),
                rs.getInt("player1ID"),
                rs.getInt("player2ID"),
                rs.getInt("winnerID"),
                rs.getTimestamp("datePlayed").toLocalDateTime(),
                rs.getInt("p1RoundWins"),
                rs.getInt("p2RoundWins"),
                rs.getInt("totalWars")
        );
    }
}
