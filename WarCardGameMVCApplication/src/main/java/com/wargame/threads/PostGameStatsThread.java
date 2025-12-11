package com.wargame.threads;

import com.wargame.game.WarGame;

public class PostGameStatsThread extends Thread {

    private final WarGame game;

    public PostGameStatsThread(WarGame game) {
        this.game = game;
    }

    @Override
    public void run() {

        try { Thread.sleep(300); } catch (InterruptedException ignored) {}

        int p1Wins = 0;
        int p2Wins = 0;

        for (WarGame.RoundRecord r : game.getRoundHistory()) {
            if (r.winner == 1) p1Wins++;
            else if (r.winner == 2) p2Wins++;
        }

        System.out.println("\n[Thread 2] Game Statistics");
        System.out.println("Player 1 round wins: " + p1Wins);
        System.out.println("Player 2 round wins: " + p2Wins);
    }
}
