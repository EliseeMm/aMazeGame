package test;

import Main.Cell;
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

}
