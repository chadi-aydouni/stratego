package g43729.stratego.model.pieces;

import g43729.stratego.model.PlayerColor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aydouni Chadi - 43729
 */
public class SpyTest {

    public SpyTest() {
    }

    // ----- constructor() -----
    @Test
    public void testConstructor() {
        Spy spy = new Spy(PlayerColor.RED);
        assertEquals(spy.getRank(), 1);
        assertEquals(spy.getColor(), PlayerColor.RED);
        assertEquals(spy.getNbSteps(), 1);
    }

    // ----- equals() -----
    @Test
    public void equalsTrueMySelf() {
        Spy spy = new Spy(PlayerColor.RED);
        assertTrue(spy.equals(spy));
        assertTrue(spy.hashCode() == spy.hashCode());
    }

    @Test
    public void equalsTrue() {
        Spy spy1 = new Spy(PlayerColor.RED);
        Spy spy2 = new Spy(PlayerColor.RED);
        assertTrue(spy1.equals(spy2));
        assertTrue(spy1.hashCode() == spy2.hashCode());
    }

    @Test
    public void equalsFalseDifferentColor() {
        Spy spy1 = new Spy(PlayerColor.RED);
        Spy spy2 = new Spy(PlayerColor.BLUE);
        assertFalse(spy1.equals(spy2));
    }

    @Test
    public void equalsFalseOtherObject() {
        Spy spy1 = new Spy(PlayerColor.RED);
        String spy2 = "abcd";
        assertFalse(spy1.equals(spy2));
    }

    @Test
    public void equalsFalseNull() {
        Spy spy1 = new Spy(PlayerColor.RED);
        assertFalse(spy1.equals(null));
    }

    // ----- toString() -----
    @Test
    public void testToString() {
        Spy spy1 = new Spy(PlayerColor.RED);
        String result = spy1.toString();
        assertFalse(result.isEmpty());
    }

    // ----- isStronger() -----
    @Test
    public void testIsStrongerWhenIsSameRank() {
        Spy spy1 = new Spy(PlayerColor.RED);
        Spy spy2 = new Spy(PlayerColor.RED);
        boolean result = spy1.isStronger(spy2);
        assertFalse(result);
    }

    @Test
    public void testIsStrongerWhenIsSronger() {
        Spy spy = new Spy(PlayerColor.RED);
        Flag flag = new Flag(PlayerColor.RED);
        boolean result = spy.isStronger(flag);
        assertTrue(result);
    }

    @Test
    public void testIsStrongerWhenIsWeaker() {
        Spy spy = new Spy(PlayerColor.RED);
        General general = new General(PlayerColor.RED);
        boolean result = spy.isStronger(general);
        assertFalse(result);
    }

    @Test
    public void testIsStrongerWhenAgainstMarshal() {
        Spy spy = new Spy(PlayerColor.RED);
        Marshal marshal = new Marshal(PlayerColor.RED);
        boolean result = spy.isStronger(marshal);
        assertTrue(result);
    }

    // ----- hasSameRank() -----
    @Test
    public void testHasSameRankWhenIsSameRank() {
        Spy spy1 = new Spy(PlayerColor.RED);
        Spy spy2 = new Spy(PlayerColor.RED);
        boolean result = spy1.hasSameRank(spy2);
        assertTrue(result);
    }

    @Test
    public void testHasSameRankWhenIsNotSameRank() {
        Spy spy = new Spy(PlayerColor.RED);
        Marshal marshal = new Marshal(PlayerColor.RED);
        boolean result = spy.hasSameRank(marshal);
        assertFalse(result);
    }

}
