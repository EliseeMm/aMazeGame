package Main;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid(5);
        ArrayList<ArrayList<Cell>> grids = grid.makeGrid();
        for (ArrayList<Cell> row: grids){
            for(Cell cell : row){
                System.out.print("#");
            }
            System.out.println();
        }
    }
}
