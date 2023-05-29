package Main;

import java.util.ArrayList;

public class Cell {
    private final float x;
    private final float y;
    private CellType cellType;
    private ArrayList<Cell> walls = new ArrayList<>();

    public Cell(float x, float y) {
        this.x = x;
        this.y = y;
        this.cellType = CellType.CELL;
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

    public ArrayList<Cell> cellWalls() {
        ArrayList<Cell> Walls = new ArrayList<>();
        Cell upperWall = new Cell(x, (float) (y + 0.5));
        Cell lowerWall = new Cell(x, (float) (y - 0.5));
        Cell leftWall = new Cell((float) (x - 0.5), y);
        Cell rightWall = new Cell((float) (x + 0.5), y);

        upperWall.setCellType(CellType.WALL);
        lowerWall.setCellType(CellType.WALL);
        leftWall.setCellType(CellType.WALL);
        rightWall.setCellType(CellType.WALL);


        Walls.add(upperWall);
        Walls.add(lowerWall);
        Walls.add(leftWall);
        Walls.add(rightWall);
        return Walls;
    }

    public void setWalls(ArrayList<Cell> cellWalls) {
        this.walls = cellWalls;
    }

    public ArrayList<Cell> getWalls() {
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
        ArrayList<Cell> wallsInCellA = this.getWalls();
        ArrayList<Cell> wallsInCellB = b.getWalls();

        for (int i = 0; i < wallsInCellA.size(); i++) {
            for (int j = 0; j < wallsInCellB.size(); j++) {
                if (wallsInCellA.get(i).equals(wallsInCellB.get(j))) {

                    wallsInCellA.remove(wallsInCellA.get(i));
                    wallsInCellB.remove(wallsInCellB.get(j));

                    this.setWalls(wallsInCellA);
                    b.setWalls(wallsInCellB);

                }
            }
        }
    }

//    public boolean withinBox(Cell bottomLeftCorner,Cell topRightCorner){
//        return this.y >= bottomLeftCorner.getY() && this.y <= topRightCorner.getY() &&
//                this.x >= bottomLeftCorner.getX() && this.x <= topRightCorner.getX();
//    }
//}
    public boolean withinBox(int size){
        return this.x <= size && this.x >= 0 && this.y <= size && this.y >= 0;
    }
}