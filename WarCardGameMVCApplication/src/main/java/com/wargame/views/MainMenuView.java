package com.wargame.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuView {

    public void show(Stage stage) {

        stage.setTitle("WAR Card Game - Main Menu");

        Button btnPlay    = new Button("Start a New Game");
        Button btnPlayers = new Button("Manage Players");
        Button btnGames   = new Button("View Games");
        Button btnExit    = new Button("Exit");

        btnPlay.setPrefWidth(240);
        btnPlayers.setPrefWidth(240);
        btnGames.setPrefWidth(240);
        btnExit.setPrefWidth(240);

        VBox layout = new VBox(18, btnPlay, btnPlayers, btnGames, btnExit);
        layout.setPadding(new Insets(25));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 350, 350);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        btnPlay.setOnAction(e -> new ChoosePlayerView().show(new Stage()));
        btnPlayers.setOnAction(e -> new PlayerView().showPlayerView(new Stage()));
        btnGames.setOnAction(e -> new GameView().showGameView(new Stage()));
        btnExit.setOnAction(e -> stage.close());
    }
}
