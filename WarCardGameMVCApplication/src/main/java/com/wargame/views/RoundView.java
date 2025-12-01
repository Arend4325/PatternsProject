package com.wargame.views;

import com.wargame.controllers.RoundController;
import com.wargame.models.Round;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

public class RoundView {

    private final RoundController roundController = new RoundController();
    private final TableView<Round> table = new TableView<>();

    public void showRoundsForGame(Stage stage, int gameID) {

        stage.setTitle("Rounds for Game #" + gameID);

        TableColumn<Round, Integer> colRoundNum = new TableColumn<>("Round #");
        colRoundNum.setCellValueFactory(new PropertyValueFactory<>("roundNumber"));
        colRoundNum.setPrefWidth(80);

        TableColumn<Round, String> colP1Card = new TableColumn<>("Player 1 Card");
        colP1Card.setCellValueFactory(new PropertyValueFactory<>("player1Card"));
        colP1Card.setPrefWidth(150);

        TableColumn<Round, String> colP2Card = new TableColumn<>("Player 2 Card");
        colP2Card.setCellValueFactory(new PropertyValueFactory<>("player2Card"));
        colP2Card.setPrefWidth(150);

        TableColumn<Round, Integer> colWinner = new TableColumn<>("Winner ID");
        colWinner.setCellValueFactory(new PropertyValueFactory<>("roundWinner"));
        colWinner.setPrefWidth(100);

        TableColumn<Round, Boolean> colWar = new TableColumn<>("War?");
        colWar.setCellValueFactory(new PropertyValueFactory<>("warRound"));
        colWar.setPrefWidth(80);

        table.getColumns().addAll(colRoundNum, colP1Card, colP2Card, colWinner, colWar);

        loadRounds(gameID);

        BorderPane root = new BorderPane();
        root.setCenter(table);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 650, 450);
        stage.setScene(scene);
        stage.show();
    }

    private void loadRounds(int gameID) {
        List<Round> rounds = roundController.getRoundsForGame(gameID);

        ObservableList<Round> data = FXCollections.observableArrayList(rounds);
        table.setItems(data);
    }
}
