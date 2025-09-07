package g43729.stratego.model.pieces;

import g43729.stratego.model.Piece;
import g43729.stratego.model.PlayerColor;

/**
 * A spy has a rank of 1 and can do one step. If he attacks a marshal, he
 * automatically wins.
 *
 * @author Aydouni Chadi - 43729
 */
public class Spy extends Piece {

    /**
     * Constructor of Spy. A spy has a rank of 1, a color and can do one step.
     *
     * @param color a color.
     */
    public Spy(PlayerColor color) {
        super(1, color);
    }

    /**
     * Indicates if the spy is stronger than the piece specified. If the other
     * piece is a marshal, the spy automatically wins.
     *
     * @param other a piece.
     * @return true if the piece is a marshal or if the spy is stronger, false
     * otherwise.
     */
    public boolean isStronger(Piece other) {
        return (other.getRank() == 10) || super.isStronger(other);
    }

}
