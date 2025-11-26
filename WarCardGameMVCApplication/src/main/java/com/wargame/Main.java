package com.wargame;

import com.wargame.controllers.PlayerController;
import com.wargame.models.Player;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PlayerController playerController = new PlayerController();

        while (true) {
            System.out.println("\n===== WAR CARD GAME MENU =====");
            System.out.println("1. Add Player");
            System.out.println("2. View Players");
            System.out.println("0. Exit");
            System.out.print("Enter option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            if (option == 0) {
                System.out.println("Goodbye!");
                break;
            }

            switch (option) {

                case 1 -> {
                    System.out.print("Enter first name: ");
                    String first = scanner.nextLine();

                    System.out.print("Enter last name: ");
                    String last = scanner.nextLine();

                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();

                    Player p = new Player(first, last, email);
                    playerController.addPlayer(p);

                    System.out.println("Player added with ID: " + p.getPlayerID());
                }

                case 2 -> {
                    List<Player> players = playerController.getAllPlayers();
                    System.out.println("\n--- Players ---");
                    for (Player p : players) {
                        System.out.println(p.getPlayerID() + ": " + p);
                    }
                }

                default -> System.out.println("Invalid option.");
            }
        }
    }
}
