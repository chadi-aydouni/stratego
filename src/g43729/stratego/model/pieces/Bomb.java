package g43729.stratego.model.pieces;

import g43729.stratego.model.Piece;
import g43729.stratego.model.PlayerColor;

/**
 * A bomb has a rank of 11 and cannot move.
 *
 * @author Aydouni Chadi - 43729
 */
public class Bomb extends Piece {

    /**
     * Constructor of Bomb. A bomb has a rank of 11, a color and cannot move.
     *
     * @param color a color.
     */
    public Bomb(PlayerColor color) {
        super(11, color, 0);
    }

}
