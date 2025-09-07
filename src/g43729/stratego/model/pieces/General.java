package g43729.stratego.model.pieces;

import g43729.stratego.model.Piece;
import g43729.stratego.model.PlayerColor;

/**
 * A general has a rank of 9 and can do one step.
 *
 * @author Aydouni Chadi - 43729
 */
public class General extends Piece {

    /**
     * Constructor of General. A general has a rank of 9, a color and can do one
     * step.
     *
     * @param color a color.
     */
    public General(PlayerColor color) {
        super(9, color);
    }

}
