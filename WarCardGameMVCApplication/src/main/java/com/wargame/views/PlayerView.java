package com.wargame.views;

import com.wargame.controllers.PlayerController;
import com.wargame.models.Player;
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

public class PlayerView {

    private final PlayerController playerController = new PlayerController();
    private final TableView<Player> table = new TableView<>();

    public void showPlayerView(Stage stage) {

        stage.setTitle("Player Management");

        Label title = new Label("PLAYER MANAGEMENT");
        title.getStyleClass().add("title-label");

        TableColumn<Player, Integer> colID = new TableColumn<>("ID");
        colID.setCellValueFactory(new PropertyValueFactory<>("playerID"));
        colID.setPrefWidth(60);

        TableColumn<Player, String> colFirst = new TableColumn<>("First Name");
        colFirst.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Player, String> colLast = new TableColumn<>("Last Name");
        colLast.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Player, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmail.setPrefWidth(180);

        TableColumn<Player, Integer> colWins = new TableColumn<>("Wins");
        colWins.setCellValueFactory(new PropertyValueFactory<>("totalWins"));

        table.getColumns().addAll(colID, colFirst, colLast, colEmail, colWins);
        refreshTable();

        Button btnAdd = new Button("Add Player");
        Button btnDelete = new Button("Delete Player");

        btnAdd.setOnAction(e -> openAddPlayerWindow());
        btnDelete.setOnAction(e -> deleteSelectedPlayer());

        HBox buttons = new HBox(12, btnAdd, btnDelete);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(10));

        VBox center = new VBox(15, title, table, buttons);
        center.setPadding(new Insets(20));
        center.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane(center);
        applyBackground(root);

        Scene scene = new Scene(root, 650, 450);
        scene.getStylesheets().add(
                getClass().getResource("/wartheme.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void refreshTable() {
        ObservableList<Player> data =
                FXCollections.observableArrayList(playerController.getAllPlayers());
        table.setItems(data);
    }

    private void openAddPlayerWindow() {
        Stage window = new Stage();
        window.setTitle("Add Player");

        TextField txtFirst = new TextField();
        txtFirst.setPromptText("First Name");

        TextField txtLast = new TextField();
        txtLast.setPromptText("Last Name");

        TextField txtEmail = new TextField();
        txtEmail.setPromptText("Email");

        Button btnSave = new Button("Save");

        btnSave.setOnAction(e -> {
            Player p = new Player(
                    txtFirst.getText(),
                    txtLast.getText(),
                    txtEmail.getText()
            );

            if (playerController.addPlayer(p)) {
                refreshTable();
                window.close();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to add player.").show();
            }
        });

        VBox layout = new VBox(10,
                new Label("Add New Player"),
                txtFirst, txtLast, txtEmail,
                btnSave
        );

        layout.setPadding(new Insets(15));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 250);
        scene.getStylesheets().add(
                getClass().getResource("/wartheme.css").toExternalForm()
        );

        window.setScene(scene);
        window.show();
    }

    private void deleteSelectedPlayer() {
        Player selected = table.getSelectionModel().getSelectedItem();

        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Select a player to delete.").show();
            return;
        }

        boolean deleted =
                playerController.deletePlayer(selected.getPlayerID());

        if (deleted) {
            refreshTable();
        }
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
