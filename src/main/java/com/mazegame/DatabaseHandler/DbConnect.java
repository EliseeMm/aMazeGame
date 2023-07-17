package com.mazegame.DatabaseHandler;

import com.mazegame.ScoresAndNames.ScoreNameAlgorithm;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * DbTest is a small command-line tool used to check that we can connect to a SQLite database.
 * <p>
 * By default, (without any command-line arguments) it attempts to create a SQLite table in an in-memory database.
 * If it succeeds, we assume that all the working parts we need to use SQLite databases are in place and working.
 * <p>
 * The only command-line argument this app understands is
 * `-f <filename>`
 * which tells that application to create the test table in a real (disk-resident) database named by the given
 * filename. Note that the application _does not delete_ the named file, but leaves it in the filesystem
 * for later examination if desired.
 */
public class DbConnect {
    public DbConnect() {

    }


    public Connection getConnection() {

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:scores.db");
            createScoresTable(connection);
            System.out.println("Connected to database ");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createScoresTable(Connection connection) {
        try (final Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE TABLE scores_table(" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT UNIQUE NOT NULL, " +
                    "algorithm_type TEXT NOT NULL, " +
                    "score INTEGER NOT NULL )");
            System.out.println("Success creating test table!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addPlayerScore(Connection connection, String name, String algorithm, int score) {
        try (final PreparedStatement stmt = connection.prepareStatement("INSERT INTO scores_table(name,algorithm_type, score) VALUES (?,?,?)")) {

            stmt.setString(1, name);
            stmt.setString(2, algorithm);
            stmt.setInt(3, score);

            boolean gotAResultSet = stmt.execute();
            if (gotAResultSet) {
                throw new RuntimeException("Unexpectedly got a SQL resultSet.");
            } else {
                final int updateCount = stmt.getUpdateCount();
                if (updateCount == 1) {
                    System.out.println("1 row INSERTED into ingredients");
                } else {
                    throw new RuntimeException("Expected 1 row to be inserted, but got " + updateCount);
                }
            }
        } catch (SQLException SQLITE_ERROR) {
            System.out.println(SQLITE_ERROR.getMessage());
        }
    }
    public ArrayList<ScoreNameAlgorithm> readScoreTable(Connection connection )
            throws SQLException {
        ArrayList<ScoreNameAlgorithm> scoreNameAlgorithms;
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
                int rowNo = 1;
                scoreNameAlgorithms = new ArrayList<>();

                while (results.next()) {
                    final String playerName = results.getString("name");
                    final String algorithmType = results.getString("algorithm_type");
                    final int score = Integer.parseInt(results.getString("score"));
                    final StringBuilder b = new StringBuilder("Row ").append(rowNo).append("-")
                            .append(playerName).append("-")
                            .append(algorithmType).append("-")
                            .append(score);
                    scoreNameAlgorithms.add(new ScoreNameAlgorithm(playerName, score, algorithmType));
                }
            }
        }
        return scoreNameAlgorithms;
    }
}