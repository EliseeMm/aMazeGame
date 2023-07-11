package com.mazegame.Algorithms;

import com.mazegame.CellsAndWalls.Cell;

import java.util.ArrayList;

public interface Algorithms {
    void findEndPoint(ArrayList<ArrayList<Cell>> grid);

    String getWallColor();

    ArrayList<ArrayList<Cell>> getGridPoints();
}
