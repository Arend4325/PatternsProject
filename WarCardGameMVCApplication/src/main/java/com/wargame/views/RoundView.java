package com.wargame.views;

import com.wargame.controllers.RoundController;
import com.wargame.models.Round;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class RoundView {

    private final RoundController roundController = new RoundController();
    private final TableView<Round> table = new TableView<>();

    public void showRoundsForGame(Stage stage, int gameID) {

        stage.setTitle("Rounds for Game #" + gameID);

        Label title = new Label("ROUND HISTORY – GAME #" + gameID);
        title.getStyleClass().add("title-label");

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

        table.getColumns().addAll(
                colRoundNum,
                colP1Card,
                colP2Card,
                colWinner,
                colWar
        );

        loadRounds(gameID);

        Button btnClose = new Button("Close");
        btnClose.setOnAction(e -> stage.close());

        HBox buttonRow = new HBox(btnClose);
        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.setPadding(new Insets(10));

        VBox center = new VBox(15, title, table, buttonRow);
        center.setPadding(new Insets(20));
        center.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane(center);
        applyBackground(root);

        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(
                getClass().getResource("/wartheme.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void loadRounds(int gameID) {
        List<Round> rounds = roundController.getRoundsForGame(gameID);
        ObservableList<Round> data = FXCollections.observableArrayList(rounds);
        table.setItems(data);
    }

    private void applyBackground(Pane root) {
        BackgroundImage bg = new BackgroundImage(
                new Image(getClass()
                        .getResource("/images/table_bg.png")
                        .toExternalForm()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
        );
        root.setBackground(new Background(bg));
    }
}
