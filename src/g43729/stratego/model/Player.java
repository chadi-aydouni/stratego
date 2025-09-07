package g43729.stratego.model;

import g43729.stratego.model.pieces.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class represents a player. A player is defined by a color. A player has
 * a list of active pieces.
 *
 * @author Aydouni Chadi - 43729
 */
public class Player {

    private PlayerColor color;
    private List<Piece> pieces;

    /**
     * Constructor of Player, generates an empty list of pieces.
     *
     * @param color color of the player
     * @throws NullPointerException if the color is null.
     */
    public Player(PlayerColor color) {
        if (color == null) {
            throw new NullPointerException("the color specified is incorrect: " + color);
        }
        this.color = color;
        this.pieces = new ArrayList<Piece>();
    }

    /**
     * Geter of pieces.
     *
     * @return the value of pieces.
     */
    public List<Piece> getPieces() {
        return pieces;
    }

    /**
     * Getter of color.
     *
     * @return the value of color.
     */
    public PlayerColor getColor() {
        return color;
    }

    /**
     * Adds a piece to the player's list.
     *
     * @param piece a piece.
     */
    public void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

    /**
     * Removes the piece from the player's list (if it's not empty).
     *
     * @param piece a piece.
     */
    public void remove(Piece piece) {
        if (!pieces.isEmpty()) {
            pieces.remove(piece);
        }
    }

    /**
     * Indicates if a player has at least one flag in his list.
     *
     * @return true if there is a flag, false otherwise.
     */
    public boolean hasFlag() {
        return pieces.contains(new Flag(color));
    }

    /**
     * Generate a hash code value for the object.
     *
     * @return a hash code value for the player.
     */
    @Override
    public int hashCode() {
        return Objects.hash(pieces, color);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false
     * otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.color != other.color) {
            return false;
        }
        if (!Objects.equals(this.pieces, other.pieces)) {
            return false;
        }
        return true;
    }

}
