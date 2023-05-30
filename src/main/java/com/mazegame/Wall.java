package com.mazegame;

public record Wall(float x, float y) {

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
}
