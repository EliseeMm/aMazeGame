package com.mazegame.Algorithms;

import com.mazegame.CellsAndWalls.Cell;
import com.mazegame.CellsAndWalls.CellType;
import com.mazegame.Grid;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AldousBroder implements Algorithms {
    private Grid mazeOfMazeBlocks;
    private ArrayList<ArrayList<Cell>> gridPoints;
    private String wallColor;
    private Random random = new Random();
    private List<Cell> visitedCells = new ArrayList<>();
    private List<Cell> totalCells = new ArrayList<>();
    private final Color color = Color.GREEN;
    public AldousBroder(Grid grid){
        this.mazeOfMazeBlocks = grid;
        gridPoints = mazeOfMazeBlocks.makeGrid();
        wallColor = "green";
        for (ArrayList<Cell> row : gridPoints){
            totalCells.addAll(row);
        }
        algo();
        findEndPoint(gridPoints);
    }
    @Override
    public void findEndPoint(ArrayList<ArrayList<Cell>> grid) {
        int rowIndex = random.nextInt(grid.size());
        int cellIndex = random.nextInt(grid.get(rowIndex).size());
        Cell endPoint = grid.get(rowIndex).get(cellIndex);
        endPoint.setCellType(CellType.END);
    }

    @Override
    public String getWallColor() {
        return wallColor;
    }

    @Override
    public ArrayList<ArrayList<Cell>> getGridPoints() {
        return gridPoints;
    }

    @Override
    public String getAlgorithmName() {
        return "Aldous-Broder Algorithm";
    }

    @Override
    public Color getAlorithmColor() {
        return color;
    }

    public void algo(){
       Cell startingCell = totalCells.remove(random.nextInt(totalCells.size()));
       visitedCells.add(startingCell);
        while (!totalCells.isEmpty()){
            ArrayList<Cell> neighbors =  findNeighbors(startingCell);

            if(!neighbors.isEmpty()) {
                Cell neighbor = neighbors.get(random.nextInt(neighbors.size()));
                startingCell.removeCellWalls(neighbor);
                startingCell = neighbor;

            }

            else {
                do {
                    startingCell = totalCells.remove(random.nextInt(totalCells.size()));
                }while (!visitedCells.contains(startingCell));
            }
            visitedCells.add(startingCell);
        }
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
                    if (cell.withinBox(mazeOfMazeBlocks.getSize()) && !visitedCells.contains(cell)) {
                        neighbors.add(cell);
                    }
                }
            }
        }
        return neighbors;
    }

    public static void main(String[] args) {
        AldousBroder aldousBroder = new AldousBroder(new Grid(12));
        aldousBroder.algo();
    }
}
