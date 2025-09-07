package g43729.stratego.model.pieces;

import g43729.stratego.model.PlayerColor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aydouni Chadi - 43729
 */
public class FlagTest {

    public FlagTest() {
    }

    // ----- constructor() -----
    @Test
    public void testConstructor() {
        Flag flag = new Flag(PlayerColor.RED);
        assertEquals(flag.getRank(), 0);
        assertEquals(flag.getColor(), PlayerColor.RED);
        assertEquals(flag.getNbSteps(), 0);
    }

    // ----- equals() -----
    @Test
    public void equalsTrueMySelf() {
        Flag flag = new Flag(PlayerColor.RED);
        assertTrue(flag.equals(flag));
        assertTrue(flag.hashCode() == flag.hashCode());
    }

    @Test
    public void equalsTrue() {
        Flag flag1 = new Flag(PlayerColor.RED);
        Flag flag2 = new Flag(PlayerColor.RED);
        assertTrue(flag1.equals(flag2));
        assertTrue(flag1.hashCode() == flag2.hashCode());
    }

    @Test
    public void equalsFalseDifferentColor() {
        Flag flag1 = new Flag(PlayerColor.RED);
        Flag flag2 = new Flag(PlayerColor.BLUE);
        assertFalse(flag1.equals(flag2));
    }

    @Test
    public void equalsFalseOtherObject() {
        Flag flag1 = new Flag(PlayerColor.RED);
        String flag2 = "abcd";
        assertFalse(flag1.equals(flag2));
    }

    @Test
    public void equalsFalseNull() {
        Flag flag1 = new Flag(PlayerColor.RED);
        assertFalse(flag1.equals(null));
    }

    // ----- toString() -----
    @Test
    public void testToString() {
        Flag flag1 = new Flag(PlayerColor.RED);
        String result = flag1.toString();
        assertFalse(result.isEmpty());
    }

    // ----- isStronger() -----
    @Test
    public void testIsStrongerWhenIsSameRank() {
        Flag flag1 = new Flag(PlayerColor.RED);
        Flag flag2 = new Flag(PlayerColor.RED);
        boolean result = flag1.isStronger(flag2);
        assertFalse(result);
    }

    @Test
    public void testIsStrongerWhenIsWeaker() {
        Flag flag = new Flag(PlayerColor.RED);
        General general = new General(PlayerColor.RED);
        boolean result = flag.isStronger(general);
        assertFalse(result);
    }

    // ----- hasSameRank() -----
    @Test
    public void testHasSameRankWhenIsSameRank() {
        Flag flag1 = new Flag(PlayerColor.RED);
        Flag flag2 = new Flag(PlayerColor.RED);
        boolean result = flag1.hasSameRank(flag2);
        assertTrue(result);
    }
    
    @Test
    public void testHasSameRankWhenIsNotSameRank() {
        Flag flag = new Flag(PlayerColor.RED);
        Marshal marshal = new Marshal(PlayerColor.RED);
        boolean result = flag.hasSameRank(marshal);
        assertFalse(result);
    }
}
