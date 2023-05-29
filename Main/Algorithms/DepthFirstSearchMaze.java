package Main.Algorithms;

import Main.Cell;
import Main.Grid;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class DepthFirstSearchMaze {
    Grid mazeOfMazeBlocks;
    ArrayList<ArrayList<Cell>> gridPoints;
    Random random = new Random();
    private final ArrayList<Cell> visited = new ArrayList<>();

    public DepthFirstSearchMaze(Grid grid){
        this.mazeOfMazeBlocks = grid;
        gridPoints = mazeOfMazeBlocks.makeGrid();
    }

    public void depthFirstSearch(){
        Stack<Cell> stackGridPoints = new Stack<>();
        int index = random.nextInt(gridPoints.size());
        ArrayList<Cell> startPoint = gridPoints.get(index);
        stackGridPoints.push(startPoint.get(index));

        while (!stackGridPoints.empty()){
            Cell current = stackGridPoints.pop();

            System.out.println(current);


            List<Cell> neighbors = findNeighbors(current);
            System.out.println("neighbors "+neighbors);

            if (neighbors.size() > 0) {
                int randomNeighborIndex = random.nextInt(neighbors.size());
                Cell neighbor = neighbors.get(randomNeighborIndex);
                System.out.println("selected neigh"+neighbor);
                neighbor.setWalls(neighbor.cellWalls());

                System.out.println(neighbor.getWalls());
                current.removeCellWalls(neighbor);
                stackGridPoints.push(neighbor);
                visited.add(neighbor);
                System.out.println();
            }
        }
        for (ArrayList<Cell> row: gridPoints) {
            for (Cell cell : row) {
                System.out.println(cell.getWalls());
            }
        }
    }

    public ArrayList<Cell> findNeighbors(Cell gridPoint){
        ArrayList<Cell> neighbors = new ArrayList<>();
        Cell northPoint = new Cell(gridPoint.getX(),gridPoint.getY()+1);
        Cell southPoint = new Cell(gridPoint.getX(),gridPoint.getY()-1);
        Cell eastPoint = new Cell(gridPoint.getX() + 1,gridPoint.getY());
        Cell westPoint = new Cell(gridPoint.getX() - 1,gridPoint.getY());
        neighbors.add(northPoint);
        neighbors.add(southPoint);
        neighbors.add(eastPoint);
        neighbors.add(westPoint);
        neighbors.removeIf(neighbor -> visited.contains(neighbor) || !neighbor.withinBox(mazeOfMazeBlocks.getSize()));
        return neighbors;
    }

}
