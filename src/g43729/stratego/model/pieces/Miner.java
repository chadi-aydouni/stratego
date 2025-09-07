package g43729.stratego.model.pieces;

import g43729.stratego.model.Piece;
import g43729.stratego.model.PlayerColor;

/**
 * A miner has a rank of 3 and can do one step. He has the ability to destroy
 * bombs.
 *
 * @author Aydouni Chadi - 43729
 */
public class Miner extends Piece {

    /**
     * Constructor of Miner. A miner has a rank of 3, a color and can do one
     * step.
     *
     * @param color a color.
     */
    public Miner(PlayerColor color) {
        super(3, color);
    }

    /**
     * Indicates if the miner is stronger than the piece specified. If the other
     * piece is a bomb, the miner automatically wins.
     *
     * @param other a piece.
     * @return true if the piece is a bomb or if the miner is stronger, false
     * otherwise.
     */
    public boolean isStronger(Piece other) {
        return (other.getRank() == 11) || super.isStronger(other);
    }

}
