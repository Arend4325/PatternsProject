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

        TableColumn<Game, Integer> colID = new TableColumn<>("Game ID");
        colID.setCellValueFactory(new PropertyValueFactory<>("gameID"));
        colID.setPrefWidth(80);

        TableColumn<Game, Integer> colP1 = new TableColumn<>("Player 1");
        colP1.setCellValueFactory(new PropertyValueFactory<>("player1ID"));

        TableColumn<Game, Integer> colP2 = new TableColumn<>("Player 2");
        colP2.setCellValueFactory(new PropertyValueFactory<>("player2ID"));

        TableColumn<Game, Integer> colWinner = new TableColumn<>("Winner");
        colWinner.setCellValueFactory(new PropertyValueFactory<>("winnerID"));

        TableColumn<Game, String> colDate = new TableColumn<>("Date Played");
        colDate.setCellValueFactory(new PropertyValueFactory<>("datePlayed"));
        colDate.setPrefWidth(160);

        TableColumn<Game, Integer> colRounds = new TableColumn<>("Rounds");
        colRounds.setCellValueFactory(new PropertyValueFactory<>("totalRounds"));

        TableColumn<Game, Integer> colWars = new TableColumn<>("Wars");
        colWars.setCellValueFactory(new PropertyValueFactory<>("totalWars"));

        table.getColumns().addAll(colID, colP1, colP2, colWinner, colDate, colRounds, colWars);

        refreshTable();

        Button btnViewRounds = new Button("View Rounds");
        Button btnRefresh = new Button("Refresh");

        btnViewRounds.setOnAction(e -> openRoundViewForSelectedGame());
        btnRefresh.setOnAction(e -> refreshTable());

        HBox buttonRow = new HBox(10, btnViewRounds, btnRefresh);
        buttonRow.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setCenter(table);
        root.setBottom(buttonRow);

        Scene scene = new Scene(root, 800, 450);
        stage.setScene(scene);
        stage.show();
    }

    private void refreshTable() {
        ObservableList<Game> data =
                FXCollections.observableArrayList(gameController.getAllGames());
        table.setItems(data);
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
