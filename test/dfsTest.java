package test;

import Main.Algorithms.DepthFirstSearchMaze;
import Main.Cell;
import Main.Grid;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class dfsTest {

    /**
     * The allCellsHaveLessThan4Walls function tests that all cells in the maze have less than 4 walls.
     * This is done by creating a grid and then running the depth first search algorithm 1000 times.
     * The function then checks that each cell has less than 4 walls, if not it will fail.
     * return True if all cells in the maze have less than 4 walls
     */
    @Test
    void allCellsHaveLessThan4Walls() {
        Grid grid = new Grid(4);
        ArrayList<ArrayList<Cell>> grids = grid.makeGrid();

        for(int i = 0;i < 1000;i++) {
            DepthFirstSearchMaze dfs = new DepthFirstSearchMaze(grid);
            ArrayList<ArrayList<Cell>> maze = dfs.depthFirstSearch();

            for (ArrayList<Cell> row : maze) {
                for (Cell cell : row) {
                    assertTrue(cell.getWalls().size() < 4);
                }
            }
        }
    }
}
