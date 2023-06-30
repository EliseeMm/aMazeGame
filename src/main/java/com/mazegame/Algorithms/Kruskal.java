package com.mazegame.Algorithms;

import com.mazegame.CellsAndWalls.Cell;
import com.mazegame.CellsAndWalls.CellType;
import com.mazegame.Grid;
import com.mazegame.CellsAndWalls.Wall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Kruskal implements Algorithms{
    Grid mazeOfMazeBlocks;
    ArrayList<ArrayList<Cell>> gridPoints;
    Random random = new Random();
    private String wallColor;

    private static List<Wall> wallList = new ArrayList<>();
    private static List<Wall> wallsWithTwoNeighbors = new ArrayList<>();

    public Kruskal(Grid grid){
        this.mazeOfMazeBlocks = grid;
        gridPoints = mazeOfMazeBlocks.makeGrid();
        wallColor = "red";
        populateWalls(gridPoints);
        setCellIds(gridPoints);
        findEndPoint(gridPoints);
        kruskal();
    }

    private void populateWalls(ArrayList<ArrayList<Cell>> gridPoints){
        for(ArrayList<Cell> row: gridPoints){
            for(Cell cell:row){
              ArrayList<Wall> cellWalls = cell.getWalls();
                for(Wall wall: cellWalls){
                    if (!wallList.contains(wall)){
                        wallList.add(wall);
                    }
                }
            }
        }
    }
    public List<Wall> getWallList(){
        return wallList;
    }

    public void setCellIds(ArrayList<ArrayList<Cell>> gridPoints){
        for(ArrayList<Cell> row: gridPoints){
            for(Cell cell:row) {
                cell.setCellID(cell);
            }
        }
    }

    public void getCellIds(ArrayList<ArrayList<Cell>> gridPoints){
        for(ArrayList<Cell> row: gridPoints){
            for(Cell cell:row) {
                System.out.println(cell.getCellID());
            }
        }
    }

    public Cell find(Cell p){
        if (p.getCellID().equals(p)){
            return p;
        }
        return find(p.getCellID());
    }

    public void union(Cell a, Cell b,Wall wall){
        Cell rootOfA = find(a);
        Cell rootOfB = find(b);
        if(!rootOfA.equals(rootOfB)){
            a.getWalls().remove(wall);
            b.getWalls().remove(wall);
            rootOfB.setCellID(rootOfA);
        }
    }
    public ArrayList<ArrayList<Cell>> getGridPoints(){
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

    public ArrayList<ArrayList<Cell>> kruskal() {
        Collections.shuffle(wallList);
        while (!wallList.isEmpty()) {
            Wall wall = wallList.get(0);
            List<Cell> adjacentCells = this.findAdjacentCell(gridPoints, wall);
            if (adjacentCells.size() == 2) {
                union(adjacentCells.get(0), adjacentCells.get(1), wall);
            }
            wallList.remove(wall);
        }
        return gridPoints;
    }
    @Override
    public String toString(){
        return "kruskal";
    }
}
