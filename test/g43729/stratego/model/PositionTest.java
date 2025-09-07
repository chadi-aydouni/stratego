package g43729.stratego.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class PositionTest {

    // ----- getRow() -----
    @Test
    public void testGetRow() {
        Position instance = new Position(67, 42);
        int expResult = 67;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    // ----- getColumn() -----
    @Test
    public void testGetColumn() {
        Position instance = new Position(34, -5);
        int expResult = -5;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }

    // ----- toString() -----
    @Test
    public void testToString() {
        Position instance = new Position(-25, 12);
        String result = instance.toString();
        assertFalse(result.isEmpty());
    }

    // ----- equals() -----
    @Test
    public void equalsTrueMySelf() {
        Position position1 = new Position(-4, 7);
        assertTrue(position1.equals(position1));
        assertTrue(position1.hashCode() == position1.hashCode());
    }

    @Test
    public void equalsTrue() {
        Position position1 = new Position(-4, 7);
        Position position2 = new Position(-4, 7);
        assertTrue(position1.equals(position2));
        assertTrue(position1.hashCode() == position2.hashCode());
    }

    @Test
    public void equalsFalseDifferentRow() {
        Position position1 = new Position(-4, 7);
        Position position2 = new Position(8, 7);
        assertFalse(position1.equals(position2));
    }

    @Test
    public void equalsFalseDifferentColumn() {
        Position position1 = new Position(2, 7);
        Position position2 = new Position(2, 5);
        assertFalse(position1.equals(position2));
    }

    @Test
    public void equalsFalseOtherObject() {
        Position position1 = new Position(2, 96);
        String position2 = "abcd";
        assertFalse(position1.equals(position2));
    }

    @Test
    public void equalsFalseNull() {
        Position position1 = new Position(2, 96);
        assertFalse(position1.equals(null));
    }

    // ----- next() -----
    @Test
    public void testNextWhenDirectionUP() {
        Direction direction = Direction.UP;
        Position instance = new Position(2, 3);
        Position expResult = new Position(1, 3);
        Position result = instance.next(direction);
        assertEquals(expResult, result);
    }

    @Test
    public void testNextWhenDirectionDOWN() {
        Direction direction = Direction.DOWN;
        Position instance = new Position(2, 3);
        Position expResult = new Position(3, 3);
        Position result = instance.next(direction);
        assertEquals(expResult, result);
    }

    @Test
    public void testNextWhenDirectionLEFT() {
        Direction direction = Direction.LEFT;
        Position instance = new Position(2, 3);
        Position expResult = new Position(2, 2);
        Position result = instance.next(direction);
        assertEquals(expResult, result);
    }

    @Test
    public void testNextWhenDirectionRIGHT() {
        Direction direction = Direction.RIGHT;
        Position instance = new Position(2, 3);
        Position expResult = new Position(2, 4);
        Position result = instance.next(direction);
        assertEquals(expResult, result);
    }

}
