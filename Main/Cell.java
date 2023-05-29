package Main;

public class Cell {
    private final int x;
    private final  int y;

    boolean visited;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        this.visited = false;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setVisited(){
        this.visited = true;
    }

    public boolean isVisited() {
        return visited;
    }



    @Override
    public String toString(){
        return "("+ this.x + ","+this.y+")";
    }

    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if (!(o instanceof Cell)){
            return  false;
        }

        Cell gridPoint = (Cell) o;
        return gridPoint.getX() == this.getX() && gridPoint.getY() == this.getY();
    }

    public boolean withinBox(Cell bottomLeftCorner,Cell topRightCorner){
        return this.y >= bottomLeftCorner.getY() && this.y <= topRightCorner.getY() &&
                this.x >= bottomLeftCorner.getX() && this.x <= topRightCorner.getX();
    }



}
