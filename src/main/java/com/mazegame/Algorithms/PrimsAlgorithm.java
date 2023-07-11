package com.mazegame.Algorithms;

import com.mazegame.CellsAndWalls.Cell;
import com.mazegame.CellsAndWalls.CellType;
import com.mazegame.CellsAndWalls.Wall;
import com.mazegame.Grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimsAlgorithm implements Algorithms{
    Grid mazeOfMazeBlocks;
    ArrayList<ArrayList<Cell>> gridPoints;
    String wallColor;
    Random random = new Random();
    List<Cell> visitedCells = new ArrayList<>();
    List<Wall> wallList = new ArrayList<>();
    public PrimsAlgorithm(Grid grid){
        this.mazeOfMazeBlocks = grid;
        gridPoints = mazeOfMazeBlocks.makeGrid();
        wallColor = "yellow";
        prims();
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

    public void prims(){
        int index = random.nextInt(gridPoints.size());
        ArrayList<Cell> startPoint = gridPoints.get(index);

        Cell startingCell = startPoint.get(index);

        visitedCells.add(startingCell);

        List<Wall> frontierWalls = new ArrayList<>();

        for (Cell cell : visitedCells) {
            ArrayList<Wall> walls = cell.getWalls();
            frontierWalls.addAll(walls);
        }
        while (!frontierWalls.isEmpty()) {
            Wall frontierWall = frontierWalls.get(random.nextInt(frontierWalls.size()));

            List<Cell> adjacentCells = findAdjacentCell(gridPoints, frontierWall);

            if (adjacentCells.size() == 2) {
                if(visitedCells.contains(adjacentCells.get(0)) && !visitedCells.contains(adjacentCells.get(1))) {
                    adjacentCells.get(0).removeAWall(frontierWall);
                    adjacentCells.get(1).removeAWall(frontierWall);
                    visitedCells.add(adjacentCells.get(1));

                    frontierWalls.addAll(adjacentCells.get(1).getWalls());
                }

                if(visitedCells.contains(adjacentCells.get(1)) && !visitedCells.contains(adjacentCells.get(0))) {
                    adjacentCells.get(0).removeAWall(frontierWall);
                    adjacentCells.get(1).removeAWall(frontierWall);
                    visitedCells.add(adjacentCells.get(0));

                    frontierWalls.addAll(adjacentCells.get(0).getWalls());
                }

            }
            wallList.add(frontierWall);
            frontierWalls.remove(frontierWall);
        }

    }
    public List<Cell> findAdjacentCell(ArrayList<ArrayList<Cell>> gridPoints, Wall wall) {
        List<Cell> adjacentCells = new ArrayList<>();
        for (ArrayList<Cell> row : gridPoints) {
            for (Cell cell : row) {
                if(cell.getWalls().contains(wall)){
                    adjacentCells.add(cell);
                }
            }
        }

        return adjacentCells;
    }
}
