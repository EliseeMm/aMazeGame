package Main;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid(2);
        ArrayList<ArrayList<Cell>> grids = grid.makeGrid();

//        for (ArrayList<Cell> row: grids){
//            System.out.println(row);
//        }
        for (ArrayList<Cell> row: grids){
            for(Cell cell : row){
                cell.setWalls(cell.cellWalls());
            }
        }

        for (ArrayList<Cell> row: grids){
            for(Cell cell : row){
                System.out.println(cell.getWalls());
            }
            System.out.println();
        }
    }
}
