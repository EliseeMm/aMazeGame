package com.mazegame.Scenes;

import com.mazegame.Algorithms.*;
import com.mazegame.CellsAndWalls.Cell;
import com.mazegame.Grid;
import java.util.ArrayList;
import java.util.Random;

public class GenerateMaze {
    private ArrayList<ArrayList<Cell>> maze;
    private String colorWall;
    private Algorithms algorithms;
    public GenerateMaze(Grid grid, String algo){
        generateMaze(grid,algo);
    }
    public void generateMaze(Grid grid, String algo){
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

    public ArrayList<ArrayList<Cell>> getMaze() {
        return maze;
    }
    public Algorithms getAlgorithms() {
        return algorithms;
    }
    public String getColorWall() {
        return colorWall;
    }
}
