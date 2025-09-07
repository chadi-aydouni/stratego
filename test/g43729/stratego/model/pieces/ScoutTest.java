package g43729.stratego.model.pieces;

import g43729.stratego.model.PlayerColor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aydouni Chadi - 43729
 */
public class ScoutTest {

    public ScoutTest() {
    }

    // ----- constructor() -----
    @Test
    public void testConstructor() {
        Scout scout = new Scout(PlayerColor.RED);
        assertEquals(scout.getRank(), 2);
        assertEquals(scout.getColor(), PlayerColor.RED);
        assertEquals(scout.getNbSteps(), 2);
    }

    // ----- equals() -----
    @Test
    public void equalsTrueMySelf() {
        Scout scout = new Scout(PlayerColor.RED);
        assertTrue(scout.equals(scout));
        assertTrue(scout.hashCode() == scout.hashCode());
    }

    @Test
    public void equalsTrue() {
        Scout scout1 = new Scout(PlayerColor.RED);
        Scout scout2 = new Scout(PlayerColor.RED);
        assertTrue(scout1.equals(scout2));
        assertTrue(scout1.hashCode() == scout2.hashCode());
    }

    @Test
    public void equalsFalseDifferentColor() {
        Scout scout1 = new Scout(PlayerColor.RED);
        Scout scout2 = new Scout(PlayerColor.BLUE);
        assertFalse(scout1.equals(scout2));
    }

    @Test
    public void equalsFalseOtherObject() {
        Scout scout1 = new Scout(PlayerColor.RED);
        String scout2 = "abcd";
        assertFalse(scout1.equals(scout2));
    }

    @Test
    public void equalsFalseNull() {
        Scout scout1 = new Scout(PlayerColor.RED);
        assertFalse(scout1.equals(null));
    }

    // ----- toString() -----
    @Test
    public void testToString() {
        Scout scout1 = new Scout(PlayerColor.RED);
        String result = scout1.toString();
        assertFalse(result.isEmpty());
    }

    // ----- isStronger() -----
    @Test
    public void testIsStrongerWhenIsSameRank() {
        Scout scout1 = new Scout(PlayerColor.RED);
        Scout scout2 = new Scout(PlayerColor.RED);
        boolean result = scout1.isStronger(scout2);
        assertFalse(result);
    }

    @Test
    public void testIsStrongerWhenIsStronger() {
        Scout scout = new Scout(PlayerColor.RED);
        Flag flag = new Flag(PlayerColor.RED);
        boolean result = scout.isStronger(flag);
        assertTrue(result);
    }

    @Test
    public void testIsStrongerWhenIsWeaker() {
        Scout scout = new Scout(PlayerColor.RED);
        Bomb bomb = new Bomb(PlayerColor.RED);
        boolean result = scout.isStronger(bomb);
        assertFalse(result);
    }

    // ----- hasSameRank() -----
    @Test
    public void testHasSameRankWhenIsSameRank() {
        Scout scout1 = new Scout(PlayerColor.RED);
        Scout scout2 = new Scout(PlayerColor.RED);
        boolean result = scout1.hasSameRank(scout2);
        assertTrue(result);
    }

    @Test
    public void testHasSameRankWhenIsNotSameRank() {
        Scout scout = new Scout(PlayerColor.RED);
        Marshal marshal = new Marshal(PlayerColor.RED);
        boolean result = scout.hasSameRank(marshal);
        assertFalse(result);
    }

}
