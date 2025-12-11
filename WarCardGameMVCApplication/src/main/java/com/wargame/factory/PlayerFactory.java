package com.wargame.factory;

//subject to change
public class PlayerFactory {
    public static WarPlayer createHuman(String name) {
        return new WarPlayer(name);
    }

    public static WarPlayer createAI(String name, int difficulty) {
        return new AIWarPlayer(name, difficulty);
    }
}
