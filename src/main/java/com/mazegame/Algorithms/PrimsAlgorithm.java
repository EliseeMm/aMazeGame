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
        wallColor = "pink";
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

    public ArrayList<ArrayList<Cell>> prims(){
        int index = random.nextInt(gridPoints.size());
        ArrayList<Cell> startPoint = gridPoints.get(index);

//      choose a random starting cell
        Cell startingCell = startPoint.get(index);


//      mark it as visited
        visitedCells.add(startingCell);

        List<Wall> frontierWalls = new ArrayList<>();


//      add its wall to the frontier
        for (Cell cell : visitedCells) {
            ArrayList<Wall> walls = cell.getWalls();
            frontierWalls.addAll(walls);
        }
        while (!frontierWalls.isEmpty()) {
//      randomly select a cell from the frontier
            Wall frontierWall = frontierWalls.get(random.nextInt(frontierWalls.size()));

//      find unvisited adjacent
            List<Cell> adjacentCells = findAdjacentCell(gridPoints, frontierWall);

//
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

        return gridPoints;
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
    public ArrayList<Cell> findNeighbors(Cell gridPoint){
        ArrayList<Cell> neighbors = new ArrayList<>();

        Cell northPoint = new Cell(gridPoint.getX(),gridPoint.getY()+1);
        Cell southPoint = new Cell(gridPoint.getX(),gridPoint.getY()-1);
        Cell eastPoint = new Cell(gridPoint.getX() + 1,gridPoint.getY());
        Cell westPoint = new Cell(gridPoint.getX() - 1,gridPoint.getY());

        for (ArrayList<Cell> row: gridPoints) {
            for (Cell cell : row) {
                if (cell.equals(northPoint) || cell.equals(southPoint) || cell.equals(eastPoint) || cell.equals(westPoint)){
                    if(cell.withinBox(mazeOfMazeBlocks.getSize()) && !visitedCells.contains(cell)){
                        neighbors.add(cell);
                    }
                }
            }
        }
        return neighbors;
    }

    public static void main(String[] args) {
        Grid grid = new Grid(12);
        PrimsAlgorithm primsAlgorithm = new PrimsAlgorithm(grid);
        primsAlgorithm.prims();
    }
}
