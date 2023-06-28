package com.mazegame;

import com.mazegame.CellsAndWalls.Cell;

import java.util.ArrayList;


public class Grid {
    private final int height;
    private final int width;

    public int getSize() {
        return size;
    }

    private final int size;

    public Grid(int size){
        this.width = size;
        this.height = size;
        this.size = size;
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
