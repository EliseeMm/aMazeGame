package com.mazegame.Algorithms;

import com.mazegame.CellsAndWalls.Cell;
import com.mazegame.CellsAndWalls.CellType;
import com.mazegame.Grid;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * The type Depth first search maze.
 */
public class DepthFirstSearchMaze implements Algorithms {
    private final Grid mazeOfMazeBlocks;
    private final String wallColor;
    private final ArrayList<ArrayList<Cell>> gridPoints;
    private final Random random = new Random();
    private final ArrayList<Cell> visited = new ArrayList<>();

    private final Color color = Color.BLUE;

    /**
     * Instantiates a new Depth first search maze.
     *
     * @param grid the grid
     */
    public DepthFirstSearchMaze(Grid grid) {
        this.mazeOfMazeBlocks = grid;
        gridPoints = grid.makeGrid();
        findEndPoint(gridPoints);
        this.wallColor = "blue";
    }

    public ArrayList<ArrayList<Cell>> getGridPoints() {
        Stack<Cell> stackGridPoints = new Stack<>();
        int index = random.nextInt(gridPoints.size());
        ArrayList<Cell> startPoint = gridPoints.get(index);
        Cell startingCell = startPoint.get(index);
        stackGridPoints.push(startingCell);
        visited.add(startingCell);

        while (!stackGridPoints.empty()) {
            Cell current = stackGridPoints.pop();
            List<Cell> neighbors = findNeighbors(current);

            if (neighbors.size() > 0) {
                stackGridPoints.push(current);
                int randomNeighborIndex = random.nextInt(neighbors.size());
                Cell neighbor = neighbors.get(randomNeighborIndex);
                current.removeCellWalls(neighbor);
                stackGridPoints.push(neighbor);
                visited.add(neighbor);
            }
        }
        return gridPoints;
    }

    @Override
    public String getAlgorithmName() {
        return "Depth First Search Algorithm";
    }

    @Override
    public Color getAlorithmColor() {
        return color;
    }

    private ArrayList<Cell> findNeighbors(Cell gridPoint) {
        ArrayList<Cell> neighbors = new ArrayList<>();

        Cell northPoint = new Cell(gridPoint.getX(), gridPoint.getY() + 1);
        Cell southPoint = new Cell(gridPoint.getX(), gridPoint.getY() - 1);
        Cell eastPoint = new Cell(gridPoint.getX() + 1, gridPoint.getY());
        Cell westPoint = new Cell(gridPoint.getX() - 1, gridPoint.getY());

        for (ArrayList<Cell> row : gridPoints) {
            for (Cell cell : row) {
                if (cell.equals(northPoint) || cell.equals(southPoint) || cell.equals(eastPoint) || cell.equals(westPoint)) {
                    if (cell.withinBox(mazeOfMazeBlocks.getSize()) && !visited.contains(cell)) {
                        neighbors.add(cell);
                    }
                }
            }
        }
        return neighbors;
    }

    @Override
    public void findEndPoint(ArrayList<ArrayList<Cell>> grid) {
        int rowIndex = random.nextInt(grid.size());
        int cellIndex = random.nextInt(grid.get(rowIndex).size());
        Cell endPoint = grid.get(rowIndex).get(cellIndex);
        endPoint.setCellType(CellType.END);


    }

    public String getWallColor() {
        return wallColor;
    }

    @Override
    public String toString() {
        return "dfs";
    }
}
