package com.wargame.views;

import com.wargame.controllers.GameController;
import com.wargame.controllers.PlayerController;
import com.wargame.controllers.RoundController;
import com.wargame.factory.GameFactory;
import com.wargame.game.WarGame;
import com.wargame.models.Game;
import com.wargame.models.Player;
import com.wargame.models.Round;
import com.wargame.threads.PostGameStatsThread;
import com.wargame.threads.WarCounterThread;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GameplayView {

    private final Player p1;
    private final Player p2;

    private final WarGame game;
    private final String finalWinner;

    private int currentRoundIndex = 0;

    private final Label lblStatus = new Label("Click Next Round to begin.");
    private final Button btnNext = new Button("Next Round");

    public GameplayView(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;

        this.game = GameFactory.createWarGame(
                p1.getFirstName(),
                p2.getFirstName()
        );

        this.finalWinner = game.playGame();
    }

    public void show(Stage stage) {

        stage.setTitle("Gameplay");

        Label title = new Label("WAR GAMEPLAY");
        title.getStyleClass().add("title-label");

        Label lblPlayers = new Label(
                p1.getFirstName() + " vs " + p2.getFirstName()
        );

        VBox top = new VBox(10, title, lblPlayers, lblStatus);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(20));

        Button btnFinish = new Button("Finish Game");
        btnFinish.setDisable(true);

        btnNext.setOnAction(e -> advanceRound(btnFinish));
        btnFinish.setOnAction(e -> finishGame(stage));

        HBox buttons = new HBox(12, btnNext, btnFinish);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setBottom(buttons);

        applyBackground(root);

        Scene scene = new Scene(root, 500, 350);
        scene.getStylesheets().add(
                getClass().getResource("/wartheme.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void advanceRound(Button btnFinish) {
        if (currentRoundIndex >= game.getRoundHistory().size()) {
            lblStatus.setText("All rounds complete.");
            btnNext.setDisable(true);
            btnFinish.setDisable(false);
            return;
        }

        WarGame.RoundRecord r =
                game.getRoundHistory().get(currentRoundIndex);

        lblStatus.setText(
                "Round " + r.roundNumber +
                        "\n" + p1.getFirstName() + ": " + r.p1Card +
                        "\n" + p2.getFirstName() + ": " + r.p2Card +
                        "\n" + (r.winner == 0 ? "WAR!" :
                        r.winner == 1 ? p1.getFirstName() : p2.getFirstName())
        );

        currentRoundIndex++;

        if (currentRoundIndex == game.getRoundHistory().size()) {
            btnNext.setDisable(true);
            btnFinish.setDisable(false);
        }
    }

    private void finishGame(Stage stage) {

        new WarCounterThread(game).start();
        new PostGameStatsThread(game).start();
        int winnerID = finalWinner.equals(p1.getFirstName())
                ? p1.getPlayerID()
                : p2.getPlayerID();

        new PlayerController().increaseWins(winnerID);

        Game savedGame = new Game(
                p1.getPlayerID(),
                p2.getPlayerID(),
                winnerID,
                game.getP1RoundWins(),
                game.getP2RoundWins(),
                game.getTotalWars()
        );

        int gameID = new GameController().saveGame(savedGame);

        RoundController rc = new RoundController();
        for (WarGame.RoundRecord rr : game.getRoundHistory()) {
            rc.saveRound(new Round(
                    gameID,
                    rr.roundNumber,
                    rr.p1Card.toString(),
                    rr.p2Card.toString(),
                    rr.winner,
                    rr.war
            ));
        }

        new Alert(Alert.AlertType.INFORMATION,
                "Winner: " + finalWinner +
                        "\nGame saved as ID: " + gameID
        ).show();

        stage.close();
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
