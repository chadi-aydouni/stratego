package g43729.stratego.model.pieces;

import g43729.stratego.model.Piece;
import g43729.stratego.model.PlayerColor;

/**
 * A marshal has a rank of 10 and can do one step. If he's attacked by a spy, he
 * automatically loses.
 *
 * @author Aydouni Chadi - 43729
 */
public class Marshal extends Piece {

    /**
     * Constructor of Marshal. A marshal has a rank of 10, a color and can do
     * one step.
     *
     * @param color a color.
     */
    public Marshal(PlayerColor color) {
        super(10, color);
    }

}
