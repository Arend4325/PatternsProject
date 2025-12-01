package com.wargame.views;

import com.wargame.controllers.PlayerController;
import com.wargame.models.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayerView {

    private final PlayerController playerController = new PlayerController();
    private final TableView<Player> table = new TableView<>();

    public void showPlayerView(Stage stage) {

        stage.setTitle("Player Management");

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
        Button btnRefresh = new Button("Refresh");

        btnAdd.setOnAction(e -> openAddPlayerWindow());
        btnDelete.setOnAction(e -> deleteSelectedPlayer());
        btnRefresh.setOnAction(e -> refreshTable());

        HBox buttonRow = new HBox(10, btnAdd, btnDelete, btnRefresh);
        buttonRow.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setCenter(table);
        root.setBottom(buttonRow);

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
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
            Player p = new Player(txtFirst.getText(), txtLast.getText(), txtEmail.getText());

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

        window.setScene(new Scene(layout, 300, 250));
        window.show();
    }

    private void deleteSelectedPlayer() {
        Player selected = table.getSelectionModel().getSelectedItem();

        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Select a player to delete.").show();
            return;
        }

        boolean deleted = playerController.deletePlayer(selected.getPlayerID());

        if (deleted) {
            refreshTable();
        } else {
            new Alert(Alert.AlertType.ERROR,
                    "Cannot delete player.\n(They may be referenced by past games)"
            ).show();
        }
    }
}
