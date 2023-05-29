package Main;

import java.util.ArrayList;


public class Grid {
    private final int height;
    private final int width;

    public Grid(int size){
        this.width = size;
        this.height = size;
    }

    public ArrayList<ArrayList<Cell>> makeGrid(){
        ArrayList<ArrayList<Cell>> grid = new ArrayList<>();
            for (int y = width -1; y >= 0 ; y -= 1) {
                ArrayList<Cell> rows = new ArrayList<>();
                for (int x = 0; x <  height; x += 1) {
                    Cell gridPoint = new Cell(x, y);
                    rows.add(gridPoint);
                }
                grid.add(rows);
            }
        return grid;
    }
}
