package com.mazegame.CellsAndWalls;

public class Wall{

    private final float x;
    private final float y;

    private WallPosition wallPosition;

    public Wall(float x,float y){
        this.x = x;
        this.y = y;
        this.wallPosition = WallPosition.TOP;
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
        if (!(o instanceof Wall gridPoint)) {
            return false;
        }

        return gridPoint.x() == this.x() && gridPoint.y() == this.y();
    }

    public float y() {
        return this.y;
    }

    public float x() {
        return this.x;
    }

    public void setWallPosition(WallPosition wallPosition) {
        this.wallPosition = wallPosition;
    }

    public WallPosition getWallPosition() {
        return wallPosition;
    }
}
