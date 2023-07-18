package com.mazegame.Algorithms;

import com.mazegame.CellsAndWalls.Cell;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * The interface Algorithms.
 */
public interface Algorithms {
    /**
     * Find end point.
     *
     * @param grid the grid
     */
    void findEndPoint(ArrayList<ArrayList<Cell>> grid);

    /**
     * Gets wall color.
     *
     * @return the wall color
     */
    String getWallColor();

    /**
     * Gets grid points.
     *
     * @return the grid points
     */
    ArrayList<ArrayList<Cell>> getGridPoints();

    /**
     * Gets algorithm name.
     *
     * @return the algorithm name
     */
    String getAlgorithmName();

    /**
     * Gets alorithm color.
     *
     * @return the alorithm color
     */
    Color getAlorithmColor();
}
