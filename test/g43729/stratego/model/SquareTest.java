package g43729.stratego.model;

import static g43729.stratego.model.PlayerColor.BLUE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SquareTest {

    // ----- put() -----
    @Test(expected = IllegalStateException.class)
    public void testPutWhenSquareIsWater() {
        Piece piece = new Piece(1, PlayerColor.RED);
        Square instance = new Square(SquareType.WATER);
        instance.put(piece);
    }

    @Test(expected = NullPointerException.class)
    public void testPutWhenPieceIsNull() {
        Piece piece = null;
        Square instance = new Square(SquareType.LAND);
        instance.put(piece);
    }

    @Test(expected = IllegalStateException.class)
    public void testPutWhenisNotEmpty() {
        Piece piece = new Piece(1, PlayerColor.RED);
        Square instance = new Square(SquareType.LAND);
        instance.put(piece);
        instance.put(piece);
    }

    @Test
    public void testPutWhenSquareIsEmpty() {
        Piece piece = new Piece(8, PlayerColor.RED);
        Square instance = new Square(SquareType.LAND);
        instance.put(piece);
        Piece expResult = instance.getPiece();
        assertEquals(expResult, piece);
    }

    // ----- getPiece() -----
    @Test(expected = IllegalStateException.class)
    public void testGetPieceWhenSquareFill() {
        Piece piece1 = new Piece(8, PlayerColor.RED);
        Piece piece2 = new Piece(1, PlayerColor.BLUE);
        Square instance = new Square(SquareType.LAND);
        instance.put(piece1);
        instance.put(piece2);
    }

    @Test
    public void testGetPieceWhenSquareEmpty() {
        Square instance = new Square(SquareType.LAND);
        Piece expResult = instance.getPiece();
        assertNull(expResult);
    }

    // ----- equals() -----
    @Test
    public void equalsTrueMyself() {
        Square square1 = new Square(SquareType.LAND);
        assertTrue(square1.equals(square1));
        assertTrue(square1.hashCode() == square1.hashCode());
    }

    @Test
    public void equalsTrueSameType() {
        Square square1 = new Square(SquareType.LAND);
        Square square2 = new Square(SquareType.LAND);
        assertTrue(square1.equals(square2));
        assertTrue(square1.hashCode() == square2.hashCode());
    }

    @Test
    public void equalsFalseDifferentType() {
        Square square1 = new Square(SquareType.WATER);
        Square square2 = new Square(SquareType.LAND);
        assertFalse(square1.equals(square2));
    }

    @Test
    public void equalsTrueSameContent() {
        Square square1 = new Square(SquareType.LAND);
        Square square2 = new Square(SquareType.LAND);
        square1.put(new Piece(1, BLUE));
        square2.put(new Piece(1, BLUE));
        assertTrue(square1.equals(square2));
    }

    @Test
    public void equalsFalseDifferentContent() {
        Square square1 = new Square(SquareType.LAND);
        Square square2 = new Square(SquareType.LAND);
        square1.put(new Piece(1, BLUE));
        square2.put(new Piece(3, BLUE));
        assertFalse(square1.equals(square2));
    }

    @Test
    public void equalsFalseOneEmptySameType() {
        Square square1 = new Square(SquareType.LAND);
        Square square2 = new Square(SquareType.LAND);
        square1.put(new Piece(1, BLUE));
        assertFalse(square1.equals(square2));
    }

    @Test
    public void equalsFalseOneEmptyDifferentType() {
        Square square1 = new Square(SquareType.LAND);
        Square square2 = new Square(SquareType.WATER);
        square1.put(new Piece(1, BLUE));
        assertFalse(square1.equals(square2));
    }

    @Test
    public void equalsFalseOtherObject() {
        Square square1 = new Square(SquareType.LAND);
        String square2 = "abcd";
        assertFalse(square1.equals(square2));
    }

    @Test
    public void equalsFalseNull() {
        Square square1 = new Square(SquareType.LAND);
        assertFalse(square1.equals(null));
    }

    // ----- toString() -----
    @Test
    public void testToString() {
        Square instance = new Square(SquareType.LAND);
        String result = instance.toString();
        assertFalse(result.isEmpty());
    }

    // ----- isFree() -----
    @Test
    public void testIsFreeWhenSquareEmpty() {
        Square instance = new Square(SquareType.LAND);
        boolean result = instance.isFree();
        assertTrue(result);
    }

    @Test
    public void testIsFreeWhenSquareOccupied() {
        Square instance = new Square(SquareType.LAND);
        instance.put(new Piece(1, PlayerColor.RED));
        boolean result = instance.isFree();
        assertFalse(result);
    }

    // ----- isMyOwn() -----
    @Test
    public void testIsMyOwnWhenSameColor() {
        PlayerColor color = PlayerColor.RED;
        Square instance = new Square(SquareType.LAND);
        instance.put(new Piece(1, PlayerColor.RED));
        boolean result = instance.isMyOwn(color);
        assertTrue(result);
    }

    @Test
    public void testIsMyOwnWhenDifferentColor() {
        PlayerColor color = PlayerColor.RED;
        Square instance = new Square(SquareType.LAND);
        instance.put(new Piece(1, PlayerColor.BLUE));
        boolean result = instance.isMyOwn(color);
        assertFalse(result);
    }

    @Test
    public void testIsMyOwnWhenSquareEmpty() {
        PlayerColor color = PlayerColor.RED;
        Square instance = new Square(SquareType.LAND);
        boolean result = instance.isMyOwn(color);
        assertFalse(result);
    }

    // ----- remove() -----
    @Test
    public void testRemoveWhenNoPiece() {
        Square instance = new Square(SquareType.LAND);
        instance.remove();
        assertTrue(instance.getPiece() == null);
    }

    @Test
    public void testRemoveWhenPiece() {
        Square instance = new Square(SquareType.LAND);
        instance.put(new Piece(0, PlayerColor.RED));
        instance.remove();
        assertTrue(instance.getPiece() == null);
    }

    // ----- isLand() -----
    @Test
    public void testIsLandWhenIsLand() {
        Square instance = new Square(SquareType.LAND);
        boolean result = instance.isLand();
        assertTrue(result);
    }

    @Test
    public void testIsLandWhenIsWater() {
        Square instance = new Square(SquareType.WATER);
        boolean result = instance.isLand();
        assertFalse(result);
    }

}
