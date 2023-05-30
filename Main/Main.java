package Main;

import Main.Algorithms.DepthFirstSearchMaze;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid(2);
        ArrayList<ArrayList<Cell>> grids = grid.makeGrid();
        DepthFirstSearchMaze dfs = new DepthFirstSearchMaze(grid);
        dfs.depthFirstSearch();

    }
}
