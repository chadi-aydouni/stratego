package g43729.stratego.model.pieces;

import g43729.stratego.model.Piece;
import g43729.stratego.model.PlayerColor;

/**
 * A scout has a rank of 2 and can do two steps in the same direction. He cannot
 * leap over pieces/lakes.
 *
 * @author Aydouni Chadi - 43729
 */
public class Scout extends Piece {

    /**
     * Constructor of Scout. A scout has a rank of 2, a color and can do two
     * steps in the same direction.
     *
     * @param color a color.
     */
    public Scout(PlayerColor color) {
        super(2, color, 2);
    }

}
