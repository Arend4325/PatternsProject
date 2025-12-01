package com.wargame.factory;

import com.wargame.game.*;

public class GameFactory {

    public static WarGame createWarGame(String p1Name, String p2Name) {

        WarPlayer wp1 = new WarPlayer(p1Name);
        WarPlayer wp2 = new WarPlayer(p2Name);

        WarGame game = new WarGame(wp1, wp2);
        game.setupGame();

        return game;
    }
}
