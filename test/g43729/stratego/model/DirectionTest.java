package g43729.stratego.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aydouni Chadi - 43729
 */
public class DirectionTest {

    public DirectionTest() {
    }

    // ----- valueOf() -----
    
    @Test
    public void testValues() {
        Direction[] expResult = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
        Direction[] result = Direction.values();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testValueOfWhenUP() {
        String arg0 = "UP";
        Direction expResult = Direction.UP;
        Direction result = Direction.valueOf(arg0);
        assertEquals(expResult, result);
    }

    @Test
    public void testValueOfWhenDOWN() {
        String arg0 = "DOWN";
        Direction expResult = Direction.DOWN;
        Direction result = Direction.valueOf(arg0);
        assertEquals(expResult, result);
    }

    @Test
    public void testValueOfWhenLEFT() {
        String arg0 = "LEFT";
        Direction expResult = Direction.LEFT;
        Direction result = Direction.valueOf(arg0);
        assertEquals(expResult, result);
    }

    @Test
    public void testValueOfWhenRIGHT() {
        String arg0 = "RIGHT";
        Direction expResult = Direction.RIGHT;
        Direction result = Direction.valueOf(arg0);
        assertEquals(expResult, result);
    }

    // ----- getRow() -----
    @Test
    public void testGetRowWhenUP() {
        Direction instance = Direction.UP;
        int expResult = -1;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetRowWhenDOWN() {
        Direction instance = Direction.DOWN;
        int expResult = 1;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetRowWhenLEFT() {
        Direction instance = Direction.LEFT;
        int expResult = 0;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetRowWhenRIGHT() {
        Direction instance = Direction.RIGHT;
        int expResult = 0;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    // ----- getColumn() -----
    @Test
    public void testGetColumnWhenUP() {
        Direction instance = Direction.UP;
        int expResult = 0;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetColumnWhenDOWN() {
        Direction instance = Direction.DOWN;
        int expResult = 0;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetColumnWhenLEFT() {
        Direction instance = Direction.LEFT;
        int expResult = -1;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetColumnWhenRIGHT() {
        Direction instance = Direction.RIGHT;
        int expResult = 1;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }

}
