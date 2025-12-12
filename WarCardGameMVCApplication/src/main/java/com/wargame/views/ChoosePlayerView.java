package com.wargame.views;

import com.wargame.controllers.PlayerController;
import com.wargame.models.Player;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class ChoosePlayerView {

    private final PlayerController playerController = new PlayerController();

    public void show(Stage stage) {

        stage.setTitle("Start New Game");

        Label title = new Label("START NEW GAME");
        title.getStyleClass().add("title-label");

        ComboBox<Player> cmbPlayer1 = new ComboBox<>();
        ComboBox<Player> cmbPlayer2 = new ComboBox<>();

        cmbPlayer1.setPrefWidth(240);
        cmbPlayer2.setPrefWidth(240);

        List<Player> players = playerController.getAllPlayers();

        if (players.size() < 2) {
            new Alert(Alert.AlertType.WARNING,
                    "You need at least 2 players to start a game.").show();
            stage.close();
            return;
        }

        cmbPlayer1.setItems(FXCollections.observableArrayList(players));
        cmbPlayer2.setItems(FXCollections.observableArrayList(players));

        cmbPlayer1.setPromptText("Select Player 1");
        cmbPlayer2.setPromptText("Select Player 2");

        Button btnStart = new Button("Begin Game");
        Button btnBack = new Button("Back");

        btnStart.setPrefWidth(240);
        btnBack.setPrefWidth(240);

        btnStart.setOnAction(e -> {
            Player p1 = cmbPlayer1.getValue();
            Player p2 = cmbPlayer2.getValue();

            if (p1 == null || p2 == null) {
                new Alert(Alert.AlertType.WARNING,
                        "Please select both players.").show();
                return;
            }

            if (p1.getPlayerID() == p2.getPlayerID()) {
                new Alert(Alert.AlertType.WARNING,
                        "Players must be different!").show();
                return;
            }

            new GameplayView(p1, p2).show(new Stage());
        });

        btnBack.setOnAction(e -> stage.close());

        VBox layout = new VBox(
                15,
                title,
                cmbPlayer1,
                cmbPlayer2,
                btnStart,
                btnBack
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        applyBackground(layout);

        Scene scene = new Scene(layout, 360, 360);
        scene.getStylesheets().add(
                getClass().getResource("/wartheme.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
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
