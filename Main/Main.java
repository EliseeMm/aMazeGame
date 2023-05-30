package Main;

import Main.Algorithms.DepthFirstSearchMaze;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid(4);


        ArrayList<ArrayList<Cell>> grids = grid.makeGrid();
        DepthFirstSearchMaze dfs = new DepthFirstSearchMaze(grid);
        ArrayList<ArrayList<Cell>> maze = dfs.depthFirstSearch();

    }
}
