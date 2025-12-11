package com.wargame.views;

import com.wargame.controllers.GameController;
import com.wargame.controllers.PlayerController;
import com.wargame.controllers.RoundController;
import com.wargame.factory.GameFactory;
import com.wargame.threads.PostGameStatsThread;
import com.wargame.threads.WarCounterThread;
import com.wargame.game.WarGame;
import com.wargame.models.Game;
import com.wargame.models.Player;
import com.wargame.models.Round;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

        stage.setTitle("Gameplay - " + p1.getFirstName() + " vs " + p2.getFirstName());

        Label lblPlayers = new Label(
                p1.getFirstName() + " vs " + p2.getFirstName()
        );
        lblPlayers.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        VBox topBox = new VBox(10, lblPlayers, lblStatus);
        topBox.setPadding(new Insets(10));

        Button btnFinish = new Button("Finish Game");
        btnFinish.setDisable(true);

        btnNext.setOnAction(e -> advanceRound(btnFinish));
        btnFinish.setOnAction(e -> finishGame(stage));

        HBox bottomButtons = new HBox(10, btnNext, btnFinish);
        bottomButtons.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setTop(topBox);
        root.setBottom(bottomButtons);

        stage.setScene(new Scene(root, 400, 250));
        stage.show();
    }

    private void advanceRound(Button btnFinish) {

        if (currentRoundIndex >= game.getRoundHistory().size()) {
            lblStatus.setText("All rounds complete. Click Finish Game.");
            btnNext.setDisable(true);
            btnFinish.setDisable(false);
            return;
        }

        WarGame.RoundRecord r = game.getRoundHistory().get(currentRoundIndex);

        String roundText = "Round " + r.roundNumber + ":\n" +
                p1.getFirstName() + " drew: " + r.p1Card + "\n" +
                p2.getFirstName() + " drew: " + r.p2Card + "\n";

        if (r.winner == 1) {
            roundText += "Winner: " + p1.getFirstName();
        } else if (r.winner == 2) {
            roundText += "Winner: " + p2.getFirstName();
        } else {
            roundText += "Tie (WAR!)";
        }

        lblStatus.setText(roundText);
        currentRoundIndex++;

        if (currentRoundIndex == game.getRoundHistory().size()) {
            btnFinish.setDisable(false);
            btnNext.setDisable(true);
        }
    }

    private void finishGame(Stage stage) {

        new PostGameStatsThread(game).start();
        btnNext.setDisable(true);

        String winnerName = finalWinner;
        int winnerID = winnerName.equals(p1.getFirstName())
                ? p1.getPlayerID()
                : p2.getPlayerID();

        // Update player wins
        new PlayerController().increaseWins(winnerID);

        Game savedGame = new Game(
                p1.getPlayerID(),
                p2.getPlayerID(),
                winnerID,
                game.getP1RoundWins(),   // NEW
                game.getP2RoundWins(),   // NEW
                game.getTotalWars()
        );

        GameController gc = new GameController();
        int gameID = gc.saveGame(savedGame);

        // Save all rounds
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

        new Alert(
                Alert.AlertType.INFORMATION,
                "Winner: " + winnerName +
                        "\nGame saved as ID: " + gameID
        ).show();

        new WarCounterThread(game).start();
        stage.close();
    }


}
