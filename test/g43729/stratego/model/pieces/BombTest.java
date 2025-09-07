package g43729.stratego.model.pieces;

import g43729.stratego.model.PlayerColor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aydouni Chadi - 43729
 */
public class BombTest {

    public BombTest() {
    }

    // ----- constructor() -----
    @Test
    public void testConstructor() {
        Bomb bomb = new Bomb(PlayerColor.RED);
        assertEquals(bomb.getRank(), 11);
        assertEquals(bomb.getColor(), PlayerColor.RED);
        assertEquals(bomb.getNbSteps(), 0);
    }

    // ----- equals() -----
    @Test
    public void equalsTrueMySelf() {
        Bomb bomb = new Bomb(PlayerColor.RED);
        assertTrue(bomb.equals(bomb));
        assertTrue(bomb.hashCode() == bomb.hashCode());
    }

    @Test
    public void equalsTrue() {
        Bomb bomb1 = new Bomb(PlayerColor.RED);
        Bomb bomb2 = new Bomb(PlayerColor.RED);
        assertTrue(bomb1.equals(bomb2));
        assertTrue(bomb1.hashCode() == bomb2.hashCode());
    }

    @Test
    public void equalsFalseDifferentColor() {
        Bomb bomb1 = new Bomb(PlayerColor.RED);
        Bomb bomb2 = new Bomb(PlayerColor.BLUE);
        assertFalse(bomb1.equals(bomb2));
    }

    @Test
    public void equalsFalseOtherObject() {
        Bomb bomb1 = new Bomb(PlayerColor.RED);
        String bomb2 = "abcd";
        assertFalse(bomb1.equals(bomb2));
    }

    @Test
    public void equalsFalseNull() {
        Bomb bomb1 = new Bomb(PlayerColor.RED);
        assertFalse(bomb1.equals(null));
    }

    // ----- toString() -----
    @Test
    public void testToString() {
        Bomb bomb1 = new Bomb(PlayerColor.RED);
        String result = bomb1.toString();
        assertFalse(result.isEmpty());
    }

    // ----- isStronger() -----
    @Test
    public void testIsStrongerWhenIsSameRank() {
        Bomb bomb1 = new Bomb(PlayerColor.RED);
        Bomb bomb2 = new Bomb(PlayerColor.RED);
        boolean result = bomb1.isStronger(bomb2);
        assertFalse(result);
    }

    @Test
    public void testIsStrongerWhenIsSronger() {
        Bomb bomb = new Bomb(PlayerColor.RED);
        General general = new General(PlayerColor.RED);
        boolean result = bomb.isStronger(general);
        assertTrue(result);
    }

    // ----- hasSameRank() -----
    @Test
    public void testHasSameRankWhenIsSameRank() {
        Bomb bomb1 = new Bomb(PlayerColor.RED);
        Bomb bomb2 = new Bomb(PlayerColor.RED);
        boolean result = bomb1.hasSameRank(bomb2);
        assertTrue(result);
    }

    @Test
    public void testHasSameRankWhenIsNotSameRank() {
        Bomb bomb = new Bomb(PlayerColor.RED);
        Marshal marshal = new Marshal(PlayerColor.RED);
        boolean result = bomb.hasSameRank(marshal);
        assertFalse(result);
    }

}
