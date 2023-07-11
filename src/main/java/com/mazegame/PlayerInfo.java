package com.mazegame;

public class PlayerInfo {
    private static String playerName;
    private static int playerScore;

    public static String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        PlayerInfo.playerName = playerName;
    }

    public static int getPlayerScore() {
        return playerScore;
    }

    public static void setPlayerScore(int playerScore) {
        PlayerInfo.playerScore = playerScore;
    }

    @Override
    public String toString() {
        return playerName;
    }

}
