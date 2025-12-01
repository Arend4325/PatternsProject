package com.wargame.views;

import com.wargame.controllers.PlayerController;
import com.wargame.models.Player;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ChoosePlayerView {

    private final PlayerController playerController = new PlayerController();

    public void show(Stage stage) {

        stage.setTitle("Start New Game");

        // ----------------------------------------------------
        // Dropdowns
        // ----------------------------------------------------
        ComboBox<Player> cmbPlayer1 = new ComboBox<>();
        ComboBox<Player> cmbPlayer2 = new ComboBox<>();

        cmbPlayer1.setPrefWidth(220);
        cmbPlayer2.setPrefWidth(220);

        List<Player> players = playerController.getAllPlayers();

        if (players.size() < 2) {
            Alert a = new Alert(Alert.AlertType.WARNING,
                    "You need at least 2 players to start a game.");
            a.show();
            stage.close();
            return;
        }

        cmbPlayer1.setItems(FXCollections.observableArrayList(players));
        cmbPlayer2.setItems(FXCollections.observableArrayList(players));

        cmbPlayer1.setPromptText("Select Player 1");
        cmbPlayer2.setPromptText("Select Player 2");

        // ----------------------------------------------------
        // Buttons
        // ----------------------------------------------------
        Button btnStart = new Button("Begin Game");
        Button btnBack  = new Button("Back to Menu");

        btnStart.setPrefWidth(220);
        btnBack.setPrefWidth(220);

        // ----------------------------------------------------
        // BUTTON LOGIC
        // ----------------------------------------------------
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

            GameplayView gameplay = new GameplayView(p1, p2);
            gameplay.show(new Stage());
        });

        btnBack.setOnAction(e -> stage.close());

        // ----------------------------------------------------
        // Layout
        // ----------------------------------------------------
        VBox layout = new VBox(15,
                new Label("Choose Players for the Match:"),
                cmbPlayer1,
                cmbPlayer2,
                btnStart,
                btnBack
        );

        layout.setPadding(new Insets(20));

        stage.setScene(new Scene(layout, 300, 300));
        stage.show();
    }
}
