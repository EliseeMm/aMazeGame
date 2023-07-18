package com.mazegame;

import com.mazegame.CellsAndWalls.Cell;

import java.util.ArrayList;


/**
 * The type Grid.
 */
public class Grid {
    private final int height;
    private final int width;

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

    private final int size;

    /**
     * Instantiates a new Grid.
     *
     * @param size the size
     */
    public Grid(int size) {
        this.width = size;
        this.height = size;
        this.size = size;
    }

    /**
     * Make grid array list.
     *
     * @return the array list
     */
    public ArrayList<ArrayList<Cell>> makeGrid() {
        ArrayList<ArrayList<Cell>> grid = new ArrayList<>();
        for (int y = width - 1; y >= 0; y -= 1) {
            ArrayList<Cell> rows = new ArrayList<>();
            for (int x = 0; x < height; x += 1) {
                Cell gridPoint = new Cell(x, y);
                rows.add(gridPoint);
            }
            grid.add(rows);
        }
        return grid;
    }

}
