package g43729.stratego.model.pieces;

import g43729.stratego.model.Piece;
import g43729.stratego.model.PlayerColor;

/**
 * A flag has a rank of 0 and cannot move. If taken, its owner automatically
 * loses the game.
 *
 * @author Aydouni Chadi - 43729
 */
public class Flag extends Piece {

    /**
     * Constructor of Flag. A flag has a rank of 0, a color and cannot move.
     *
     * @param color a color.
     */
    public Flag(PlayerColor color) {
        super(0, color, 0);
    }

}
