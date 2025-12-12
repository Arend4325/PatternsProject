package com.wargame.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainMenuView {

    public void show(Stage stage) {

        stage.setTitle("WAR Card Game");

        Label title = new Label("WAR CARD GAME");
        title.getStyleClass().add("title-label");

        Button btnPlay    = new Button("Start a New Game");
        Button btnPlayers = new Button("Manage Players");
        Button btnGames   = new Button("View Games");
        Button btnExit    = new Button("Exit");

        btnPlay.setPrefWidth(240);
        btnPlayers.setPrefWidth(240);
        btnGames.setPrefWidth(240);
        btnExit.setPrefWidth(240);

        btnPlay.setOnAction(e -> new ChoosePlayerView().show(new Stage()));
        btnPlayers.setOnAction(e -> new PlayerView().showPlayerView(new Stage()));
        btnGames.setOnAction(e -> new GameView().showGameView(new Stage()));
        btnExit.setOnAction(e -> stage.close());

        VBox layout = new VBox(20,
                title,
                btnPlay,
                btnPlayers,
                btnGames,
                btnExit
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        BackgroundImage bg = new BackgroundImage(
                new Image(getClass()
                        .getResource("/images/table_bg.png")
                        .toExternalForm()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                        100, 100, true, true, true, true
                )
        );

        layout.setBackground(new Background(bg));

        Scene scene = new Scene(layout, 380, 380);
        scene.getStylesheets().add(
                getClass().getResource("/wartheme.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
