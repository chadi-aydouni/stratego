package g43729.stratego.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aydouni Chadi - 43729
 */
public class MoveTest {

    public MoveTest() {
    }

    // ----- constructor() -----
    @Test(expected = NullPointerException.class)
    public void testConstructMoveWhenPieceIsNull() {
        Move instance = new Move(null, new Position(1, 1), new Position(1, 1));
        assertEquals(null, instance.getPiece());
        assertEquals(new Position(1, 1), instance.getStart());
        assertEquals(new Position(1, 1), instance.getEnd());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructMoveWhenStartIsNull() {
        Move instance = new Move(new Piece(1, PlayerColor.RED), null, new Position(1, 1));
        assertEquals(new Piece(1, PlayerColor.RED), instance.getPiece());
        assertEquals(null, instance.getStart());
        assertEquals(new Position(1, 1), instance.getEnd());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructMoveWhenEndIsNull() {
        Move instance = new Move(new Piece(1, PlayerColor.RED), new Position(1, 1), null);
        assertEquals(new Piece(1, PlayerColor.RED), instance.getPiece());
        assertEquals(new Position(1, 1), instance.getStart());
        assertEquals(null, instance.getEnd());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructMoveWhenTwoAreNull() {
        Move instance = new Move(null, new Position(1, 1), null);
        assertEquals(null, instance.getPiece());
        assertEquals(null, instance.getStart());
        assertEquals(null, instance.getEnd());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructMoveWhenEverythingIsNull() {
        Move instance = new Move(null, null, null);
        assertEquals(null, instance.getPiece());
        assertEquals(null, instance.getStart());
        assertEquals(null, instance.getEnd());
    }

    @Test
    public void testConstructMoveWhenEverythingIsOK() {
        Move instance = new Move(new Piece(1, PlayerColor.RED), new Position(1, 1), new Position(1, 2));
        assertEquals(new Piece(1, PlayerColor.RED), instance.getPiece());
        assertEquals(new Position(1, 1), instance.getStart());
        assertEquals(new Position(1, 2), instance.getEnd());
    }

    // ----- getPiece() -----
    @Test
    public void testGetPiece() {
        Move instance = new Move(new Piece(1, PlayerColor.RED), new Position(1, 1), new Position(1, 2));
        Piece expResult = new Piece(1, PlayerColor.RED);
        Piece result = instance.getPiece();
        assertEquals(expResult, result);
    }

    // ----- getStart() -----
    @Test
    public void testGetStart() {
        Move instance = new Move(new Piece(1, PlayerColor.RED), new Position(1, 1), new Position(1, 2));
        Position expResult = new Position(1, 1);
        Position result = instance.getStart();
        assertEquals(expResult, result);
    }

    // ----- getEnd() -----
    @Test
    public void testGetEnd() {
        Move instance = new Move(new Piece(1, PlayerColor.RED), new Position(1, 1), new Position(1, 2));
        Position expResult = new Position(1, 2);
        Position result = instance.getEnd();
        assertEquals(expResult, result);
    }

    // ----- equals() -----
    @Test
    public void equalsTrueMySelf() {
        Move instance = new Move(new Piece(1, PlayerColor.RED), new Position(1, 1), new Position(1, 2));
        assertTrue(instance.equals(instance));
        assertTrue(instance.hashCode() == instance.hashCode());
    }

    @Test
    public void equalsTrue() {
        Move instance = new Move(new Piece(1, PlayerColor.RED), new Position(1, 1), new Position(1, 2));
        Move instance2 = new Move(new Piece(1, PlayerColor.RED), new Position(1, 1), new Position(1, 2));
        assertTrue(instance.equals(instance2));
        assertTrue(instance.hashCode() == instance2.hashCode());
    }

    @Test
    public void equalsFalseDifferentPiece() {
        Move instance = new Move(new Piece(1, PlayerColor.RED), new Position(1, 1), new Position(1, 2));
        Move instance2 = new Move(new Piece(2, PlayerColor.RED), new Position(1, 1), new Position(1, 2));
        assertFalse(instance.equals(instance2));
    }

    @Test
    public void equalsFalseDifferentStart() {
        Move instance = new Move(new Piece(1, PlayerColor.RED), new Position(1, 1), new Position(1, 2));
        Move instance2 = new Move(new Piece(1, PlayerColor.RED), new Position(1, 2), new Position(1, 2));
        assertFalse(instance.equals(instance2));
    }

    @Test
    public void equalsFalseDifferentEnd() {
        Move instance = new Move(new Piece(1, PlayerColor.RED), new Position(1, 1), new Position(1, 2));
        Move instance2 = new Move(new Piece(1, PlayerColor.RED), new Position(1, 2), new Position(1, 1));
        assertFalse(instance.equals(instance2));
    }

    @Test
    public void equalsFalseOtherObject() {
        Move instance = new Move(new Piece(1, PlayerColor.RED), new Position(1, 1), new Position(1, 2));
        String instance2 = "abcd";
        assertFalse(instance.equals(instance2));
    }

    @Test
    public void equalsFalseNull() {
        Move instance = new Move(new Piece(1, PlayerColor.RED), new Position(1, 1), new Position(1, 2));
        assertFalse(instance.equals(null));
    }

}
