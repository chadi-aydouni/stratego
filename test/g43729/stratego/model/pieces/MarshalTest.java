package g43729.stratego.model.pieces;

import g43729.stratego.model.PlayerColor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aydouni Chadi - 43729
 */
public class MarshalTest {
    
    public MarshalTest() {
    }

    // ----- constructor() -----
    @Test
    public void testConstructor() {
        Marshal marshal = new Marshal(PlayerColor.RED);
        assertEquals(marshal.getRank(), 10);
        assertEquals(marshal.getColor(), PlayerColor.RED);
        assertEquals(marshal.getNbSteps(), 1);
    }

    // ----- equals() -----
    @Test
    public void equalsTrueMySelf() {
        Marshal marshal = new Marshal(PlayerColor.RED);
        assertTrue(marshal.equals(marshal));
        assertTrue(marshal.hashCode() == marshal.hashCode());
    }

    @Test
    public void equalsTrue() {
        Marshal marshal1 = new Marshal(PlayerColor.RED);
        Marshal marshal2 = new Marshal(PlayerColor.RED);
        assertTrue(marshal1.equals(marshal2));
        assertTrue(marshal1.hashCode() == marshal2.hashCode());
    }

    @Test
    public void equalsFalseDifferentColor() {
        Marshal marshal1 = new Marshal(PlayerColor.RED);
        Marshal marshal2 = new Marshal(PlayerColor.BLUE);
        assertFalse(marshal1.equals(marshal2));
    }

    @Test
    public void equalsFalseOtherObject() {
        Marshal marshal1 = new Marshal(PlayerColor.RED);
        String marshal2 = "abcd";
        assertFalse(marshal1.equals(marshal2));
    }

    @Test
    public void equalsFalseNull() {
        Marshal marshal1 = new Marshal(PlayerColor.RED);
        assertFalse(marshal1.equals(null));
    }

    // ----- toString() -----
    @Test
    public void testToString() {
        Marshal marshal1 = new Marshal(PlayerColor.RED);
        String result = marshal1.toString();
        assertFalse(result.isEmpty());
    }

    // ----- isStronger() -----
    @Test
    public void testIsStrongerWhenIsSameRank() {
        Marshal marshal1 = new Marshal(PlayerColor.RED);
        Marshal marshal2 = new Marshal(PlayerColor.RED);
        boolean result = marshal1.isStronger(marshal2);
        assertFalse(result);
    }
    
    @Test
    public void testIsStrongerWhenIsSronger() {
        Marshal marshal = new Marshal(PlayerColor.RED);
        General general = new General(PlayerColor.RED);
        boolean result = marshal.isStronger(general);
        assertTrue(result);
    }
    
    @Test
    public void testIsStrongerWhenIsWeaker() {
        Marshal marshal = new Marshal(PlayerColor.RED);
        Bomb bomb = new Bomb(PlayerColor.RED);
        boolean result = marshal.isStronger(bomb);
        assertFalse(result);
    }

    // ----- hasSameRank() -----
    @Test
    public void testHasSameRankWhenIsSameRank() {
        Marshal marshal1 = new Marshal(PlayerColor.RED);
        Marshal marshal2 = new Marshal(PlayerColor.RED);
        boolean result = marshal1.hasSameRank(marshal2);
        assertTrue(result);
    }
    
    @Test
    public void testHasSameRankWhenIsNotSameRank() {
        Marshal marshal = new Marshal(PlayerColor.RED);
        General general = new General(PlayerColor.RED);
        boolean result = marshal.hasSameRank(general);
        assertFalse(result);
    }
    
}
