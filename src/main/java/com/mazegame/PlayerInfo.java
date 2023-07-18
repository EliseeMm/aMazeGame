package com.mazegame;

/**
 * The type Player info.
 */
public class PlayerInfo {
    private static String playerName;
    private static int playerScore;

    /**
     * Gets player name.
     *
     * @return the player name
     */
    public static String getPlayerName() {
        return playerName;
    }

    /**
     * Sets player name.
     *
     * @param playerName the player name
     */
    public void setPlayerName(String playerName) {
        PlayerInfo.playerName = playerName;
    }

    /**
     * Gets player score.
     *
     * @return the player score
     */
    public static int getPlayerScore() {
        return playerScore;
    }

    /**
     * Sets player score.
     *
     * @param playerScore the player score
     */
    public static void setPlayerScore(int playerScore) {
        PlayerInfo.playerScore = playerScore;
    }

    @Override
    public String toString() {
        return playerName;
    }

}
