package com.wargame.views;

import com.wargame.controllers.GameController;
import com.wargame.models.Game;
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

public class GameView {

    private final GameController gameController = new GameController();
    private final TableView<Game> table = new TableView<>();

    public void showGameView(Stage stage) {

        stage.setTitle("Game History");

        Label title = new Label("GAME HISTORY");
        title.getStyleClass().add("title-label");

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

        refreshTable();

        Button btnViewRounds = new Button("View Rounds");
        btnViewRounds.setOnAction(e -> openRoundViewForSelectedGame());

        HBox buttons = new HBox(btnViewRounds);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(10));

        VBox center = new VBox(15, title, table, buttons);
        center.setPadding(new Insets(20));
        center.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane(center);
        applyBackground(root);

        Scene scene = new Scene(root, 900, 500);
        scene.getStylesheets().add(
                getClass().getResource("/wartheme.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.setResizable(false);
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

        new RoundView().showRoundsForGame(
                new Stage(),
                selected.getGameID()
        );
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
