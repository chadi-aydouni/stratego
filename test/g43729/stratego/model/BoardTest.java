package g43729.stratego.model;

import g43729.stratego.model.pieces.Flag;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    private final Square[][] defaultBoard = {
        {new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND)},
        {new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND)},
        {new Square(SquareType.LAND), new Square(SquareType.WATER), new Square(SquareType.WATER), new Square(SquareType.WATER), new Square(SquareType.LAND)},
        {new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND)},
        {new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND)},
        {new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND)}};

    // ----- getSquare() -----
    @Test
    public void testGetSquareWhenSquareIsFill() {
        Position position = new Position(3, 2);
        Board instance = new Board();
        instance.put(new Piece(4, PlayerColor.BLUE), position);
        Square expResult = new Square(SquareType.LAND);
        expResult.put(new Piece(4, PlayerColor.BLUE));
        Square result = instance.getSquare(position);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetSquareWhenSquareIsEmpty() {
        Position position = new Position(0, 1);
        Board instance = new Board();
        Square expResult = new Square(SquareType.LAND);
        Square result = instance.getSquare(position);
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSquareWhenPositionOutside() {
        Position position = new Position(-1, 2);
        Board instance = new Board();
        instance.getSquare(position);
    }

    @Test
    public void testGetSquaresWhenDefaultBoard() {
        Board instance = new Board();
        Square[][] expResult = defaultBoard;
        Square[][] result = instance.getSquares();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testGetSquaresWhen2PiecesAdded() {
        Board instance = new Board();
        instance.put(new Piece(4, PlayerColor.RED), new Position(1, 2));
        instance.put(new Piece(1, PlayerColor.BLUE), new Position(4, 3));
        Square[][] expResult = defaultBoard;
        defaultBoard[1][2].put(new Piece(4, PlayerColor.RED));
        defaultBoard[4][3].put(new Piece(1, PlayerColor.BLUE));
        Square[][] result = instance.getSquares();
        assertArrayEquals(expResult, result);
    }

    // ----- put() -----
    @Test
    public void testPutOnePiece() {
        Piece piece = new Piece(2, PlayerColor.BLUE);
        Position position = new Position(0, 0);
        Board instance = new Board();
        instance.put(piece, position);
        Square expResult = new Square(SquareType.LAND);
        expResult.put(piece);
        Square result = instance.getSquare(position);
        assertEquals(expResult, result);
    }

    @Test(expected = NullPointerException.class)
    public void testPutWhenPieceIsNull() {
        Piece piece = null;
        Position position = new Position(2, 4);
        Board instance = new Board();
        instance.put(piece, position);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutWhenPositionIsOutside() {
        Piece piece = new Piece(1, PlayerColor.BLUE);
        Position position = new Position(-1, 2);
        Board instance = new Board();
        instance.put(piece, position);
    }

    @Test(expected = IllegalStateException.class)
    public void testPutWhenPositionIsLake() {
        Piece piece = new Piece(1, PlayerColor.BLUE);
        Position position = new Position(2, 1);
        Board instance = new Board();
        instance.put(piece, position);
    }

    // ----- isInside() -----
    @Test
    public void testIsInsideWhenCornerUpLeft() {
        Position position = new Position(0, 0);
        Board instance = new Board();
        boolean result = instance.isInside(position);
        assertTrue(result);
    }

    @Test
    public void testIsInsideWhenCornerUpRight() {
        Position position = new Position(0, 4);
        Board instance = new Board();
        boolean result = instance.isInside(position);
        assertTrue(result);
    }

    @Test
    public void testIsInsideWhenCornerDownleft() {
        Position position = new Position(5, 0);
        Board instance = new Board();
        boolean result = instance.isInside(position);
        assertTrue(result);
    }

    @Test
    public void testIsInsideWhenCornerDownRight() {
        Position position = new Position(5, 4);
        Board instance = new Board();
        boolean result = instance.isInside(position);
        assertTrue(result);
    }

    @Test
    public void testIsInsideWhenBoundaryUp() {
        Position position = new Position(0, 1);
        Board instance = new Board();
        boolean result = instance.isInside(position);
        assertTrue(result);
    }

    @Test
    public void testIsInsideWhenBoundaryDown() {
        Position position = new Position(5, 2);
        Board instance = new Board();
        boolean result = instance.isInside(position);
        assertTrue(result);
    }

    @Test
    public void testIsInsideWhenBoundaryLeft() {
        Position position = new Position(1, 0);
        Board instance = new Board();
        boolean result = instance.isInside(position);
        assertTrue(result);
    }

    @Test
    public void testIsInsideWhenBoundaryRight() {
        Position position = new Position(3, 4);
        Board instance = new Board();
        boolean result = instance.isInside(position);
        assertTrue(result);
    }

    @Test
    public void testIsInsideWhenOutsideBoundaryUp() {
        Position position = new Position(-1, 1);
        Board instance = new Board();
        boolean result = instance.isInside(position);
        assertFalse(result);
    }

    @Test
    public void testIsInsideWhenOutsideBoundaryDown() {
        Position position = new Position(6, 2);
        Board instance = new Board();
        boolean result = instance.isInside(position);
        assertFalse(result);
    }

    @Test
    public void testIsInsideWhenOutsideBoundaryLeft() {
        Position position = new Position(1, -1);
        Board instance = new Board();
        boolean result = instance.isInside(position);
        assertFalse(result);
    }

    @Test
    public void testIsInsideWhenOutsideBoundaryRight() {
        Position position = new Position(3, 5);
        Board instance = new Board();
        boolean result = instance.isInside(position);
        assertFalse(result);
    }

    @Test
    public void testIsInsideWhenInside() {
        Position position = new Position(2, 1);
        Board instance = new Board();
        boolean result = instance.isInside(position);
        assertTrue(result);
    }

    @Test(expected = NullPointerException.class)
    public void testIsInsideWhenPositionIsNull() {
        Board instance = new Board();
        instance.isInside(null);
    }

    // ----- isFree() -----
    @Test
    public void testIsFreeWhenSquareEmpty() {
        Position position = new Position(1, 1);
        Board instance = new Board();
        boolean result = instance.isFree(position);
        assertTrue(result);
    }

    @Test
    public void testIsFreeWhenSquareOccupied() {
        Position position = new Position(1, 1);
        Board instance = new Board();
        instance.put(new Piece(1, PlayerColor.RED), position);
        boolean result = instance.isFree(position);
        assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsFreeWhenPositionOutside() {
        Position position = new Position(-1, 1);
        Board instance = new Board();
        boolean result = instance.isFree(position);
        assertFalse(result);
    }

    // ----- isMyOwn() -----
    @Test
    public void testIsMyOwnWhenSameColor() {
        Position position = new Position(1, 1);
        PlayerColor color = PlayerColor.RED;
        Board instance = new Board();
        instance.put(new Piece(1, PlayerColor.RED), position);
        boolean result = instance.isMyOwn(position, color);
        assertTrue(result);
    }

    @Test
    public void testIsMyOwnWhenDifferentColor() {
        Position position = new Position(1, 1);
        PlayerColor color = PlayerColor.RED;
        Board instance = new Board();
        instance.put(new Piece(1, PlayerColor.BLUE), position);
        boolean result = instance.isMyOwn(position, color);
        assertFalse(result);
    }

    @Test
    public void testIsMyOwnWhenSquareEmpty() {
        Position position = new Position(1, 1);
        PlayerColor color = PlayerColor.RED;
        Board instance = new Board();
        boolean result = instance.isMyOwn(position, color);
        assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsMyOwnWhenPositionOutside() {
        Position position = new Position(-1, 1);
        PlayerColor color = PlayerColor.RED;
        Board instance = new Board();
        boolean result = instance.isMyOwn(position, color);
        assertFalse(result);
    }

    // ----- getPiece() -----
    @Test
    public void testGetPieceWhenSquareOccupied() {
        Position position = new Position(1, 1);
        Board instance = new Board();
        instance.put(new Piece(1, PlayerColor.RED), position);
        Piece expResult = new Piece(1, PlayerColor.RED);
        Piece result = instance.getPiece(position);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetPieceWhenSquareEmpty() {
        Position position = new Position(2, 1);
        Board instance = new Board();
        Piece expResult = null;
        Piece result = instance.getPiece(position);
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPieceWhenPositionOutside() {
        Position position = new Position(-1, 1);
        Board instance = new Board();
        Piece expResult = null;
        Piece result = instance.getPiece(position);
        assertEquals(expResult, result);
    }

    // ----- remove() -----
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveWhenPositionOutside() {
        Position position = new Position(-1, 1);
        Board instance = new Board();
        instance.remove(position);
    }

    @Test
    public void testRemoveWhenEmpty() {
        Position position = new Position(0, 0);
        Board instance = new Board();
        Board expResult = new Board();
        instance.remove(position);
        assertArrayEquals(instance.getSquares(), expResult.getSquares());
    }

    @Test
    public void testRemoveWhenOccupied() {
        Position position = new Position(1, 1);
        Board instance = new Board();
        instance.put(new Piece(0, PlayerColor.RED), position);
        Board expResult = new Board();
        instance.remove(position);
        assertArrayEquals(instance.getSquares(), expResult.getSquares());
    }

    // ----- getTakenSquare() -----
    @Test
    public void testGetTakenSquareWhenNoPiecesOnBoard() {
        Player player = new Player(PlayerColor.RED);
        Board instance = new Board();
        List<Position> expResult = new ArrayList<Position>();
        List<Position> result = instance.getTakenSquare(player);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetTakenSquareWhenOpponentPiecesOnlyOnBoard() {
        Player player = new Player(PlayerColor.RED);
        Board instance = new Board();
        instance.put(new Flag(PlayerColor.BLUE), new Position(1, 1));
        List<Position> expResult = new ArrayList<Position>();
        List<Position> result = instance.getTakenSquare(player);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetTakenSquareWhenPiecesOnBoard() {
        Player player1 = new Player(PlayerColor.RED);
        Player player2 = new Player(PlayerColor.BLUE);
        Board instance = new Board();
        instance.put(new Flag(PlayerColor.RED), new Position(1, 1));
        instance.put(new Flag(PlayerColor.BLUE), new Position(2, 4));
        instance.put(new Flag(PlayerColor.RED), new Position(0, 3));

        List<Position> expResultRed = new ArrayList<Position>();
        List<Position> expResultBlue = new ArrayList<Position>();
        expResultRed.add(new Position(0, 3));
        expResultRed.add(new Position(1, 1));
        expResultBlue.add(new Position(2, 4));

        List<Position> resultRed = instance.getTakenSquare(player1);
        List<Position> resultBlue = instance.getTakenSquare(player2);

        assertEquals(expResultRed, resultRed);
        assertEquals(expResultBlue, resultBlue);
    }

    @Test
    public void testGetTakenSquareWhenPiecesOnCorners() {
        Player player = new Player(PlayerColor.RED);
        Board instance = new Board();
        instance.put(new Flag(PlayerColor.RED), new Position(0, 0));
        instance.put(new Flag(PlayerColor.RED), new Position(0, 4));
        instance.put(new Flag(PlayerColor.RED), new Position(5, 0));
        instance.put(new Flag(PlayerColor.RED), new Position(5, 4));

        List<Position> expResult = new ArrayList<Position>();
        // Attention à l'ordre, on parcourt ligne par ligne
        expResult.add(new Position(0, 0));
        expResult.add(new Position(0, 4));
        expResult.add(new Position(5, 0));
        expResult.add(new Position(5, 4));

        List<Position> result = instance.getTakenSquare(player);
        assertEquals(expResult, result);
    }

}
