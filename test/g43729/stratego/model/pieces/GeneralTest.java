package g43729.stratego.model.pieces;

import g43729.stratego.model.PlayerColor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aydouni Chadi - 43729
 */
public class GeneralTest {

    public GeneralTest() {
    }

    // ----- constructor() -----
    @Test
    public void testConstructor() {
        General general = new General(PlayerColor.RED);
        assertEquals(general.getRank(), 9);
        assertEquals(general.getColor(), PlayerColor.RED);
        assertEquals(general.getNbSteps(), 1);
    }

    // ----- equals() -----
    @Test
    public void equalsTrueMySelf() {
        General general = new General(PlayerColor.RED);
        assertTrue(general.equals(general));
        assertTrue(general.hashCode() == general.hashCode());
    }

    @Test
    public void equalsTrue() {
        General general1 = new General(PlayerColor.RED);
        General general2 = new General(PlayerColor.RED);
        assertTrue(general1.equals(general2));
        assertTrue(general1.hashCode() == general2.hashCode());
    }

    @Test
    public void equalsFalseDifferentColor() {
        General general1 = new General(PlayerColor.RED);
        General general2 = new General(PlayerColor.BLUE);
        assertFalse(general1.equals(general2));
    }

    @Test
    public void equalsFalseOtherObject() {
        General general1 = new General(PlayerColor.RED);
        String general2 = "abcd";
        assertFalse(general1.equals(general2));
    }

    @Test
    public void equalsFalseNull() {
        General general1 = new General(PlayerColor.RED);
        assertFalse(general1.equals(null));
    }

    // ----- toString() -----
    @Test
    public void testToString() {
        General general1 = new General(PlayerColor.RED);
        String result = general1.toString();
        assertFalse(result.isEmpty());
    }

    // ----- isStronger() -----
    @Test
    public void testIsStrongerWhenIsSameRank() {
        General general1 = new General(PlayerColor.RED);
        General general2 = new General(PlayerColor.RED);
        boolean result = general1.isStronger(general2);
        assertFalse(result);
    }

    @Test
    public void testIsStrongerWhenIsStronger() {
        General general = new General(PlayerColor.RED);
        Flag flag = new Flag(PlayerColor.RED);
        boolean result = general.isStronger(flag);
        assertTrue(result);
    }

    @Test
    public void testIsStrongerWhenIsWeaker() {
        General general = new General(PlayerColor.RED);
        Bomb bomb = new Bomb(PlayerColor.RED);
        boolean result = general.isStronger(bomb);
        assertFalse(result);
    }

    // ----- hasSameRank() -----
    @Test
    public void testHasSameRankWhenIsSameRank() {
        General general1 = new General(PlayerColor.RED);
        General general2 = new General(PlayerColor.RED);
        boolean result = general1.hasSameRank(general2);
        assertTrue(result);
    }

    @Test
    public void testHasSameRankWhenIsNotSameRank() {
        General general = new General(PlayerColor.RED);
        Marshal marshal = new Marshal(PlayerColor.RED);
        boolean result = general.hasSameRank(marshal);
        assertFalse(result);
    }

}
