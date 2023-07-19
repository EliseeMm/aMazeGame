package com.mazegame.Scenes;

import com.mazegame.Algorithms.*;
import com.mazegame.CellsAndWalls.Cell;
import com.mazegame.Grid;

import java.util.ArrayList;
import java.util.Random;

/**
 * The type Generate maze.
 */
public class GenerateMaze {
    private ArrayList<ArrayList<Cell>> maze;
    private String colorWall;
    private Algorithms algorithms;

    /**
     * Instantiates a new Generate maze.
     *
     * @param grid the grid
     * @param algo the algo
     */
    public GenerateMaze(Grid grid, String algo) {
        generateMaze(grid, algo);
    }

    /**
     * Generate maze.
     *
     * @param grid the grid
     * @param algo the algo
     */
    public void generateMaze(Grid grid, String algo) {
        if (algo.equalsIgnoreCase("kruskal")) {
            algorithms = new Kruskal(grid);
            maze = algorithms.getGridPoints();
        } else if (algo.equalsIgnoreCase("dfs")) {
            algorithms = new DepthFirstSearchMaze(grid);
            maze = algorithms.getGridPoints();
        } else if (algo.equalsIgnoreCase("prim")) {
            algorithms = new Prims(grid);
            maze = algorithms.getGridPoints();
        } else if (algo.equalsIgnoreCase("anb")) {
            algorithms = new AldousBroder(grid);
            maze = algorithms.getGridPoints();
        } else {
            Algorithms[] algorithmsList = {new Kruskal(grid), new DepthFirstSearchMaze(grid), new Prims(grid), new AldousBroder(grid)};
            Random random = new Random();
            int algoIndex = random.nextInt(algorithmsList.length);
            algorithms = algorithmsList[algoIndex];
            maze = algorithms.getGridPoints();
        }
        colorWall = algorithms.getWallColor();
    }

    /**
     * Gets maze.
     *
     * @return the maze
     */
    public ArrayList<ArrayList<Cell>> getMaze() {
        return maze;
    }

    /**
     * Gets algorithms.
     *
     * @return the algorithms
     */
    public Algorithms getAlgorithms() {
        return algorithms;
    }

    /**
     * Gets color wall.
     *
     * @return the color wall
     */
    public String getColorWall() {
        return colorWall;
    }
}
