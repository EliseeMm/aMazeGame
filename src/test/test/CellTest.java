package src.test.test;

import com.mazegame.CellsAndWalls.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    @Test
    public void testMakePoint(){
        Cell gridPoint = new Cell(1,2);
        assertEquals(1,gridPoint.getX());
        assertEquals(2,gridPoint.getY());
    }
    @Test
    public void testEqualPoint(){
        Cell gridPoint = new Cell(2,6);
        Cell gridPoint1 = new Cell(2,6);
        assertEquals(gridPoint,gridPoint1);
    }

    @Test
    public void testNotEqualPoints(){
        Cell gridPoint = new Cell(22,22);
        Cell gridPoint1 = new Cell(21,22);
        assertNotEquals(gridPoint,gridPoint1);
    }
    @Test
    public void testWithin(){
        Cell gridPoint = new Cell(3,3);
        assertTrue(gridPoint.withinBox(new Cell(0,0),new Cell(5,5)));
        assertFalse(gridPoint.withinBox(new Cell(10,10),new Cell(15,15)));
    }

    @Test
    public void findWalls() {
        Cell cellA = new Cell(5,0);
        cellA.setWalls(cellA.cellWalls());
        assertEquals("[(5.0,0.5), (5.0,-0.5), (4.5,0.0), (5.5,0.0)]",cellA.getWalls().toString());


    }

    @Test
    public void removeWalls(){
        Cell cellA = new Cell(0,0);
        Cell cellB = new Cell(1,0);

        cellA.setWalls(cellA.cellWalls());
        cellB.setWalls(cellB.cellWalls());

        assertEquals("[(0.0,0.5), (0.0,-0.5), (-0.5,0.0), (0.5,0.0)]",cellA.getWalls().toString());
        assertEquals("[(1.0,0.5), (1.0,-0.5), (0.5,0.0), (1.5,0.0)]",cellB.getWalls().toString());

        cellA.removeCellWalls(cellB);

        assertEquals("[(0.0,0.5), (0.0,-0.5), (-0.5,0.0)]",cellA.getWalls().toString());
        assertEquals("[(1.0,0.5), (1.0,-0.5), (1.5,0.0)]",cellB.getWalls().toString());
    }

}
