package g43729.stratego.model.pieces;

import g43729.stratego.model.PlayerColor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aydouni Chadi - 43729
 */
public class MinerTest {

    public MinerTest() {
    }

    // ----- constructor() -----
    @Test
    public void testConstructor() {
        Miner miner = new Miner(PlayerColor.RED);
        assertEquals(miner.getRank(), 3);
        assertEquals(miner.getColor(), PlayerColor.RED);
        assertEquals(miner.getNbSteps(), 1);
    }

    // ----- equals() -----
    @Test
    public void equalsTrueMySelf() {
        Miner miner = new Miner(PlayerColor.RED);
        assertTrue(miner.equals(miner));
        assertTrue(miner.hashCode() == miner.hashCode());
    }

    @Test
    public void equalsTrue() {
        Miner miner1 = new Miner(PlayerColor.RED);
        Miner miner2 = new Miner(PlayerColor.RED);
        assertTrue(miner1.equals(miner2));
        assertTrue(miner1.hashCode() == miner2.hashCode());
    }

    @Test
    public void equalsFalseDifferentColor() {
        Miner miner1 = new Miner(PlayerColor.RED);
        Miner miner2 = new Miner(PlayerColor.BLUE);
        assertFalse(miner1.equals(miner2));
    }

    @Test
    public void equalsFalseOtherObject() {
        Miner miner1 = new Miner(PlayerColor.RED);
        String miner2 = "abcd";
        assertFalse(miner1.equals(miner2));
    }

    @Test
    public void equalsFalseNull() {
        Miner miner1 = new Miner(PlayerColor.RED);
        assertFalse(miner1.equals(null));
    }

    // ----- toString() -----
    @Test
    public void testToString() {
        Miner miner1 = new Miner(PlayerColor.RED);
        String result = miner1.toString();
        assertFalse(result.isEmpty());
    }

    // ----- isStronger() -----
    @Test
    public void testIsStrongerWhenIsSameRank() {
        Miner miner1 = new Miner(PlayerColor.RED);
        Miner miner2 = new Miner(PlayerColor.RED);
        boolean result = miner1.isStronger(miner2);
        assertFalse(result);
    }

    @Test
    public void testIsStrongerWhenIsSronger() {
        Miner miner = new Miner(PlayerColor.RED);
        Flag flag = new Flag(PlayerColor.RED);
        boolean result = miner.isStronger(flag);
        assertTrue(result);
    }

    @Test
    public void testIsStrongerWhenIsWeaker() {
        Miner miner = new Miner(PlayerColor.RED);
        General general = new General(PlayerColor.RED);
        boolean result = miner.isStronger(general);
        assertFalse(result);
    }

    @Test
    public void testIsStrongerWhenAgainstBomb() {
        Miner miner = new Miner(PlayerColor.RED);
        Bomb bomb = new Bomb(PlayerColor.RED);
        boolean result = miner.isStronger(bomb);
        assertTrue(result);
    }

    // ----- hasSameRank() -----
    @Test
    public void testHasSameRankWhenIsSameRank() {
        Miner miner1 = new Miner(PlayerColor.RED);
        Miner miner2 = new Miner(PlayerColor.RED);
        boolean result = miner1.hasSameRank(miner2);
        assertTrue(result);
    }

    @Test
    public void testHasSameRankWhenIsNotSameRank() {
        Miner miner = new Miner(PlayerColor.RED);
        Marshal marshal = new Marshal(PlayerColor.RED);
        boolean result = miner.hasSameRank(marshal);
        assertFalse(result);
    }

}
