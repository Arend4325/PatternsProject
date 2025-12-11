package com.wargame.views;

import com.wargame.controllers.GameController;
import com.wargame.models.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GameView {

    private final GameController gameController = new GameController();
    private final TableView<Game> table = new TableView<>();

    public void showGameView(Stage stage) {

        stage.setTitle("Game History");

        // ============================
        //  TABLE COLUMNS
        // ============================

        TableColumn<Game, Integer> colID = new TableColumn<>("Game ID");
        colID.setCellValueFactory(new PropertyValueFactory<>("gameID"));
        colID.setPrefWidth(80);

        TableColumn<Game, Integer> colP1 = new TableColumn<>("Player 1 ID");
        colP1.setCellValueFactory(new PropertyValueFactory<>("player1ID"));

        TableColumn<Game, Integer> colP2 = new TableColumn<>("Player 2 ID");
        colP2.setCellValueFactory(new PropertyValueFactory<>("player2ID"));

        TableColumn<Game, Integer> colWinner = new TableColumn<>("Winner ID");
        colWinner.setCellValueFactory(new PropertyValueFactory<>("winnerID"));

        TableColumn<Game, String> colDate = new TableColumn<>("Date Played");
        colDate.setCellValueFactory(new PropertyValueFactory<>("datePlayed"));
        colDate.setPrefWidth(150);

        TableColumn<Game, Integer> colP1Wins = new TableColumn<>("P1 Wins");
        colP1Wins.setCellValueFactory(new PropertyValueFactory<>("p1RoundWins"));

        TableColumn<Game, Integer> colP2Wins = new TableColumn<>("P2 Wins");
        colP2Wins.setCellValueFactory(new PropertyValueFactory<>("p2RoundWins"));

        TableColumn<Game, Integer> colWars = new TableColumn<>("Wars");
        colWars.setCellValueFactory(new PropertyValueFactory<>("totalWars"));

        table.getColumns().addAll(
                colID, colP1, colP2, colWinner,
                colDate, colP1Wins, colP2Wins, colWars
        );

        // Load initial data
        refreshTable();

        // ============================
        //  BUTTONS
        // ============================
        Button btnViewRounds = new Button("View Rounds");
        Button btnRefresh = new Button("Refresh");

        btnViewRounds.setOnAction(e -> openRoundViewForSelectedGame());
        btnRefresh.setOnAction(e -> refreshTable());

        HBox buttonRow = new HBox(10, btnViewRounds, btnRefresh);
        buttonRow.setPadding(new Insets(10));

        // ============================
        //  LAYOUT
        // ============================
        BorderPane root = new BorderPane();
        root.setCenter(table);
        root.setBottom(buttonRow);

        Scene scene = new Scene(root, 850, 450);
        stage.setScene(scene);
        stage.show();
    }

    private void refreshTable() {
        ObservableList<Game> list =
                FXCollections.observableArrayList(gameController.getAllGames());
        table.setItems(list);
    }

    private void openRoundViewForSelectedGame() {

        Game selected = table.getSelectionModel().getSelectedItem();

        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Select a game first.").show();
            return;
        }

        RoundView rv = new RoundView();
        Stage roundStage = new Stage();
        rv.showRoundsForGame(roundStage, selected.getGameID());
    }
}
