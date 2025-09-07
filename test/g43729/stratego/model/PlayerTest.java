package g43729.stratego.model;

import g43729.stratego.model.pieces.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class PlayerTest {

    // ----- constructor() -----
    @Test(expected = NullPointerException.class)
    public void testConstructPlayerColorIsNull() {
        new Player(null);
    }

    // ----- getPiece() -----
    @Test
    public void testGetPiecesWhenStockEmpty() {
        Player instance = new Player(PlayerColor.BLUE);
        List<Piece> result = instance.getPieces();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetPiecesWhenStockFill() {
        Player instance = new Player(PlayerColor.BLUE);
        instance.addPiece(new Piece(0, PlayerColor.BLUE));
        instance.addPiece(new Piece(0, PlayerColor.BLUE));
        List<Piece> result = instance.getPieces();
        assertFalse(result.isEmpty());
    }

    // ----- addPiece() -----
    @Test
    public void testAddPiece() {
        Player instance = new Player(PlayerColor.BLUE);
        instance.addPiece(new Piece(0, PlayerColor.BLUE));
        instance.addPiece(new Piece(0, PlayerColor.BLUE));
        List<Piece> expResult = new ArrayList<Piece>();
        expResult.add(new Piece(0, PlayerColor.BLUE));
        expResult.add(new Piece(0, PlayerColor.BLUE));
        List<Piece> result = instance.getPieces();
        assertEquals(expResult, result);
    }

    // ----- getColor() -----
    @Test
    public void testGetColorBlue() {
        Player instance = new Player(PlayerColor.BLUE);
        PlayerColor expResult = PlayerColor.BLUE;
        PlayerColor result = instance.getColor();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetColorRed() {
        Player instance = new Player(PlayerColor.RED);
        PlayerColor expResult = PlayerColor.RED;
        PlayerColor result = instance.getColor();
        assertEquals(expResult, result);
    }

    // ----- toString() -----
    @Test
    public void testToString() {
        Player instance = new Player(PlayerColor.BLUE);
        String result = instance.toString();
        assertFalse(result.isEmpty());
    }

    // ----- equals() -----
    @Test
    public void equalsTrueMySelf() {
        Player player1 = new Player(PlayerColor.BLUE);
        assertTrue(player1.equals(player1));
        assertTrue(player1.hashCode() == player1.hashCode());
    }

    @Test
    public void equalsTrueNoPiece() {
        Player player1 = new Player(PlayerColor.BLUE);
        Player player2 = new Player(PlayerColor.BLUE);
        assertTrue(player1.equals(player2));
        assertTrue(player1.hashCode() == player2.hashCode());
    }

    @Test
    public void equalsTrueWithPiece() {
        Player player1 = new Player(PlayerColor.BLUE);
        Player player2 = new Player(PlayerColor.BLUE);
        player1.addPiece(new Piece(1, PlayerColor.BLUE));
        player2.addPiece(new Piece(1, PlayerColor.BLUE));
        assertTrue(player1.equals(player2));
        assertTrue(player1.hashCode() == player2.hashCode());
    }

    @Test
    public void equalsFalseDifferentColor() {
        Player player1 = new Player(PlayerColor.BLUE);
        Player player2 = new Player(PlayerColor.RED);
        assertFalse(player1.equals(player2));
    }

    @Test
    public void equalsFalseDifferentPiece() {
        Player player1 = new Player(PlayerColor.BLUE);
        Player player2 = new Player(PlayerColor.BLUE);
        player1.addPiece(new Piece(1, PlayerColor.BLUE));
        player2.addPiece(new Piece(2, PlayerColor.BLUE));
        assertFalse(player1.equals(player2));
    }

    @Test
    public void equalsFalseOtherObject() {
        Player player1 = new Player(PlayerColor.BLUE);
        String player2 = "abcd";
        assertFalse(player1.equals(player2));
    }

    @Test
    public void equalsFalseNull() {
        Player player1 = new Player(PlayerColor.BLUE);
        assertFalse(player1.equals(null));
    }

    // ----- remove() -----
    @Test
    public void testRemoveWhenEmptyList() {
        Piece piece = new Piece(1, PlayerColor.RED);
        Player instance = new Player(PlayerColor.RED);
        List<Piece> expResult = new ArrayList<Piece>();
        instance.remove(piece);

        assertEquals(instance.getPieces(), expResult);

    }

    @Test
    public void testRemoveWhenPiecePresent() {
        Piece piece = new Piece(1, PlayerColor.RED);
        Player instance = new Player(PlayerColor.RED);

        instance.addPiece(new Piece(0, PlayerColor.RED));
        instance.addPiece(new Piece(1, PlayerColor.RED));
        instance.addPiece(new Piece(2, PlayerColor.RED));
        instance.remove(piece);

        List<Piece> expResult = new ArrayList<Piece>();
        expResult.add(new Piece(0, PlayerColor.RED));
        expResult.add(new Piece(2, PlayerColor.RED));

        assertEquals(instance.getPieces(), expResult);
    }

    @Test
    public void testRemoveWhenPieceNotPresent() {
        Piece piece = new Piece(3, PlayerColor.RED);
        Player instance = new Player(PlayerColor.RED);

        instance.addPiece(new Piece(0, PlayerColor.RED));
        instance.addPiece(new Piece(1, PlayerColor.RED));
        instance.addPiece(new Piece(2, PlayerColor.RED));
        instance.remove(piece);

        List<Piece> expResult = new ArrayList<Piece>();
        expResult.add(new Piece(0, PlayerColor.RED));
        expResult.add(new Piece(1, PlayerColor.RED));
        expResult.add(new Piece(2, PlayerColor.RED));

        assertEquals(instance.getPieces(), expResult);
    }

    @Test
    public void testRemoveWhenPieceIsNull() {
        Piece piece = null;
        Player instance = new Player(PlayerColor.RED);
        instance.remove(piece);

        List<Piece> expResult = new ArrayList<Piece>();

        assertEquals(instance.getPieces(), expResult);

    }

    // ----- hasFlag() -----
    @Test
    public void testHasFlagWhenFalse() {
        Player instance = new Player(PlayerColor.RED);
        boolean result = instance.hasFlag();
        assertFalse(result);
    }

    @Test
    public void testHasFlagWhenTrue() {
        Player instance = new Player(PlayerColor.BLUE);
        instance.addPiece(new Flag(PlayerColor.BLUE));
        boolean result = instance.hasFlag();
        assertTrue(result);
    }

    @Test
    public void testHasFlagWhenMultipleFlags() {
        Player instance = new Player(PlayerColor.BLUE);
        instance.addPiece(new Flag(PlayerColor.BLUE));
        instance.addPiece(new Flag(PlayerColor.BLUE));
        boolean result = instance.hasFlag();
        assertTrue(result);
    }

}
