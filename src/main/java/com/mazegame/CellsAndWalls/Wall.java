package com.mazegame.CellsAndWalls;

/**
 * The type Wall.
 */
public class Wall {

    private final float x;
    private final float y;

    private WallPosition wallPosition;

    /**
     * Instantiates a new Wall.
     *
     * @param x the x
     * @param y the y
     */
    public Wall(float x, float y) {
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

    /**
     * Y float.
     *
     * @return the float
     */
    public float y() {
        return this.y;
    }

    /**
     * X float.
     *
     * @return the float
     */
    public float x() {
        return this.x;
    }

    /**
     * Sets wall position.
     *
     * @param wallPosition the wall position
     */
    public void setWallPosition(WallPosition wallPosition) {
        this.wallPosition = wallPosition;
    }

    /**
     * Gets wall position.
     *
     * @return the wall position
     */
    public WallPosition getWallPosition() {
        return wallPosition;
    }
}
