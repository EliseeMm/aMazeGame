package com.mazegame.CellsAndWalls;

import java.util.ArrayList;

public class Cell {
    private final float x;
    private final float y;
    private CellType cellType;
    private ArrayList<Wall> walls;
    private Cell cellID;

    public Cell(float x, float y) {
        this.x = x;
        this.y = y;
        this.cellType = CellType.CELL;
        this.walls = cellWalls();
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Cell gridPoint)) {
            return false;
        }

        return gridPoint.getX() == this.getX() && gridPoint.getY() == this.getY();
    }

    public boolean withinBox(Cell bottomLeftCorner, Cell topRightCorner) {
        return this.y >= bottomLeftCorner.getY() && this.y <= topRightCorner.getY() &&
                this.x >= bottomLeftCorner.getX() && this.x <= topRightCorner.getX();
    }

    public ArrayList<Wall> cellWalls() {
        ArrayList<Wall> Walls = new ArrayList<>();
        Wall upperWall = new Wall(x, (float) (y + 0.5));
        Wall lowerWall = new Wall(x, (float) (y - 0.5));
        Wall leftWall = new Wall((float) (x - 0.5), y);
        Wall rightWall = new Wall((float) (x + 0.5), y);

        upperWall.setWallPosition(WallPosition.TOP);
        lowerWall.setWallPosition(WallPosition.BOTTOM);
        leftWall.setWallPosition(WallPosition.LEFT);
        rightWall.setWallPosition(WallPosition.RIGHT);

        Walls.add(upperWall);
        Walls.add(lowerWall);
        Walls.add(leftWall);
        Walls.add(rightWall);
        return Walls;
    }

    public void setWalls(ArrayList<Wall> cellWalls) {
        this.walls = cellWalls;
    }

    public ArrayList<Wall> getWalls() {
        return this.walls;
    }


    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }


    /**
     * The removeCellWalls function removes the walls between two cells.
     * param Cell b Remove the walls between two cells
     */
    public void removeCellWalls(Cell b) {
        ArrayList<Wall> wallsInCellA = this.getWalls();
        ArrayList<Wall> wallsInCellB = b.getWalls();

        for (int i = 0; i < wallsInCellA.size(); i++) {

                if (wallsInCellB.contains(wallsInCellA.get(i))) {

                    Wall cell = wallsInCellA.get(i);
                    wallsInCellA.remove(cell);
                    wallsInCellB.remove(cell);

                    this.setWalls(wallsInCellA);
                    b.setWalls(wallsInCellB);

                }
            }
    }
    public void removeAWall(Wall wall){
        this.getWalls().remove(wall);
    }

    public boolean withinBox(int size){
        return this.x < size && this.x >= 0 && this.y < size && this.y >= 0;
    }

    public boolean withinTwoPoints(Cell cellA,Cell cellB){
        return  this.x > cellA.getX() && this.x < cellB.getX() && this.y  > cellA.getY() && this.y < cellB.getY();
    }
    public void setCellID(Cell id){
        this.cellID = id;
    }
    public Cell getCellID(){
        return this.cellID;
    }
}