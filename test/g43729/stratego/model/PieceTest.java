package g43729.stratego.model;

import static g43729.stratego.model.PlayerColor.BLUE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static g43729.stratego.model.PlayerColor.RED;

public class PieceTest {

    // ----- construct() without nbSteps -----
    @Test
    public void testConstructPiece() {
        Piece instance = new Piece(0, BLUE);
        assertEquals(BLUE, instance.getColor());
        assertEquals(0, instance.getRank());
        assertEquals(1, instance.getNbSteps());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructPieceWhenRankIsNegative() {
        Piece instance = new Piece(-1, BLUE);
    }

    // ----- construct() with nbSteps -----
    @Test
    public void testConstructPiece_2() {
        Piece instance = new Piece(0, BLUE, 3);
        assertEquals(BLUE, instance.getColor());
        assertEquals(0, instance.getRank());
        assertEquals(3, instance.getNbSteps());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructPieceWhenRankIsNegative_2() {
        Piece instance = new Piece(-1, BLUE, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructPieceWhenNbStepsIsNegative_2() {
        Piece instance = new Piece(1, BLUE, -1);
    }

    // ----- getColor() -----
    @Test
    public void testGetColorBlue() {
        Piece instance = new Piece(0, BLUE);
        PlayerColor expResult = BLUE;
        PlayerColor result = instance.getColor();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetColorRed() {
        Piece instance = new Piece(12, RED);
        PlayerColor expResult = RED;
        PlayerColor result = instance.getColor();
        assertEquals(expResult, result);
    }

    // ----- getNbSteps() -----
    @Test
    public void testGetNbStepsWhenDefault() {
        Piece instance = new Piece(12, RED);
        int expResult = 1;
        int result = instance.getNbSteps();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetNbStepsWhenSpecified() {
        Piece instance = new Piece(12, RED, 5);
        int expResult = 5;
        int result = instance.getNbSteps();
        assertEquals(expResult, result);
    }

    // ----- toString() -----
    @Test
    public void testToString() {
        Piece instance = new Piece(0, PlayerColor.BLUE);
        String result = instance.toString();
        assertFalse(result.isEmpty());

    }

    // ----- equals() -----
    @Test
    public void equalsTrueMySelf() {
        Piece piece1 = new Piece(10, BLUE);
        assertTrue(piece1.equals(piece1));
        assertTrue(piece1.hashCode() == piece1.hashCode());
    }

    @Test
    public void equalsTrue() {
        Piece piece1 = new Piece(10, BLUE);
        Piece piece2 = new Piece(10, BLUE);
        assertTrue(piece1.equals(piece2));
        assertTrue(piece1.hashCode() == piece2.hashCode());
    }

    @Test
    public void equalsFalseDifferentColor() {
        Piece piece1 = new Piece(10, BLUE);
        Piece piece2 = new Piece(10, RED);
        assertFalse(piece1.equals(piece2));
    }

    @Test
    public void equalsFalseDifferentRank() {
        Piece piece1 = new Piece(10, BLUE);
        Piece piece2 = new Piece(9, BLUE);
        assertFalse(piece1.equals(piece2));
    }

    @Test
    public void equalsFalseOtherObject() {
        Piece piece1 = new Piece(10, BLUE);
        String piece2 = "abcd";
        assertFalse(piece1.equals(piece2));
    }

    @Test
    public void equalsFalseNull() {
        Piece piece1 = new Piece(10, BLUE);
        assertFalse(piece1.equals(null));
    }

    // ----- isStronger() -----
    @Test
    public void testIsStrongerWhenIsStronger() {
        Piece other = new Piece(3, PlayerColor.RED);
        Piece instance = new Piece(5, PlayerColor.RED);
        boolean result = instance.isStronger(other);
        assertTrue(result);
    }

    @Test
    public void testIsStrongerWhenIsWeaker() {
        Piece other = new Piece(5, PlayerColor.RED);
        Piece instance = new Piece(3, PlayerColor.RED);
        boolean result = instance.isStronger(other);
        assertFalse(result);
    }

    @Test
    public void testIsStrongerWhenIsSameRank() {
        Piece other = new Piece(3, PlayerColor.RED);
        Piece instance = new Piece(3, PlayerColor.RED);
        boolean result = instance.isStronger(other);
        assertFalse(result);
    }

    // ----- hasSameRank() -----
    @Test
    public void testHasSameRankWhenIsSameRank() {
        Piece other = new Piece(3, PlayerColor.RED);
        Piece instance = new Piece(3, PlayerColor.RED);
        boolean result = instance.hasSameRank(other);
        assertTrue(result);
    }

    @Test
    public void testHasSameRankWhenIsStronger() {
        Piece other = new Piece(3, PlayerColor.RED);
        Piece instance = new Piece(5, PlayerColor.RED);
        boolean result = instance.hasSameRank(other);
        assertFalse(result);
    }

    @Test
    public void testHasSameRankWhenIsWeaker() {
        Piece other = new Piece(5, PlayerColor.RED);
        Piece instance = new Piece(3, PlayerColor.RED);
        boolean result = instance.hasSameRank(other);
        assertFalse(result);
    }

    // ----- canCross() -----
    @Test
    public void testCanCrossWhenTrue() {
        Square square = new Square(SquareType.LAND);
        Piece instance = new Piece(0, PlayerColor.RED);
        boolean result = instance.canCross(square);
        assertTrue(result);
    }

    @Test
    public void testCanCrossWhenFalse() {
        Square square = new Square(SquareType.WATER);
        Piece instance = new Piece(0, PlayerColor.RED);
        boolean result = instance.canCross(square);
        assertFalse(result);
    }

}
