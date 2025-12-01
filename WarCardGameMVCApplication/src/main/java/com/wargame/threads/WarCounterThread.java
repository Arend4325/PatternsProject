package com.wargame.threads;

import com.wargame.game.WarGame;

public class WarCounterThread extends Thread {

    private final WarGame game;

    public WarCounterThread(WarGame game) {
        this.game = game;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(300);
        } catch (InterruptedException ignored) {}

        int warCount = game.getTotalWars();

        System.out.println("\n[Thread] Total wars in this game: " + warCount);
    }
}
