package com.mazegame.DatabaseHandler;

import com.mazegame.ScoresAndNames.ScoreNameAlgorithm;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;


/**
 * The type Db connect.
 */
public class DbConnect {
    /**
     * Instantiates a new Db connect.
     */
    public DbConnect() {

    }


    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:scores.db");
            createScoresTable(connection);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Create scores table.
     *
     * @param connection the connection
     * @throws SQLException the sql exception
     */
    public void createScoresTable(Connection connection) throws SQLException {
        final Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE if NOT EXISTS scores_table(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "algorithm_type TEXT NOT NULL, " +
                "score INTEGER NOT NULL )");
    }


    /**
     * Add player score.
     *
     * @param connection the connection
     * @param name       the name
     * @param algorithm  the algorithm
     * @param score      the score
     */
    public void addPlayerScore(Connection connection, String name, String algorithm, int score) {
        try (final PreparedStatement stmt = connection.prepareStatement("INSERT INTO scores_table(name,algorithm_type, score) VALUES (?,?,?)")) {
            if (score != 0) {
                stmt.setString(1, name);
                stmt.setString(2, algorithm);
                stmt.setInt(3, score);
                stmt.execute();
            }
        } catch (SQLException SQLITE_ERROR) {
            System.out.println(SQLITE_ERROR.getMessage());
        }
    }


    /**
     * Read score table array list.
     *
     * @param connection the connection
     * @return the array list
     * @throws SQLException the sql exception
     */
    public ArrayList<ScoreNameAlgorithm> readScoreTable(Connection connection)
            throws SQLException {
        ArrayList<ScoreNameAlgorithm> scoreNameAlgorithms;
        ArrayList<Integer> scores;
        try (final PreparedStatement stmt = connection.prepareStatement("SELECT name,algorithm_type,score "
                + "FROM scores_table " +
                "ORDER BY score DESC " +
                "LIMIT 5"
        )) {
            boolean gotAResultSet = stmt.execute();
            if (!gotAResultSet) {
                throw new RuntimeException("Expected a SQL resultSet, but we got an update count instead!");
            }
            try (ResultSet results = stmt.getResultSet()) {
                scoreNameAlgorithms = new ArrayList<>();
                scores = new ArrayList<>();
                while (results.next()) {
                    final String playerName = results.getString("name");
                    final String algorithmType = results.getString("algorithm_type");
                    final int score = Integer.parseInt(results.getString("score"));
                    scores.add(score);
                    scoreNameAlgorithms.add(new ScoreNameAlgorithm(playerName, score, algorithmType));
                }
            }
        }
        if (!scores.isEmpty()) {
            deleteLowScores(Collections.min(scores), connection);
        }
        return scoreNameAlgorithms;
    }

    private void deleteLowScores(int lowestScore, Connection connection) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM scores_table " +
                "WHERE score <" + lowestScore)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}