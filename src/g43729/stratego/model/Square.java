package g43729.stratego.model;

import java.util.Objects;

/**
 * Each board cell is represented by an instance of Square. A cell can host only
 * one player's piece. It is defined by its piece and a type of square.
 *
 * @author Aydouni Chadi - 43729
 */
public class Square {

    private Piece piece;
    private SquareType type;

    /**
     * Constructor of Square. Creates an empty square of a given type.
     *
     * @param type a type.
     */
    public Square(SquareType type) {
        piece = null;
        this.type = type;
    }

    /**
     * Getter of piece.
     *
     * @return the value of piece.
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Puts a piece on the designated cell.
     *
     * @param piece a piece.
     * @throws NullPointerException if the piece is null.
     * @throws IllegalStateException if the cell is of water type or is not
     * empty.
     */
    public void put(Piece piece) {
        if (!isLand()) {
            throw new IllegalStateException("the cell is of water type.");
        }

        if (piece == null) {
            throw new NullPointerException("the input piece is incorrect: " + piece);
        }

        if (this.piece != null) {
            throw new IllegalStateException("the cell is not empty: " + this.piece);
        }

        this.piece = piece;
    }

    /**
     * Indicates if a square is free (piece is null).
     *
     * @return true if the square is free, false otherwise.
     */
    public boolean isFree() {
        return piece == null;
    }

    /**
     * Indicates if the piece has the color specified.
     *
     * @param color a color.
     * @return true if the piece has the same color, false otherwise.
     */
    public boolean isMyOwn(PlayerColor color) {
        return !isFree() && piece.getColor() == color;
    }

    /**
     * Indicates if a square is of LAND type.
     *
     * @return true if the square is of LAND type, false otherwise.
     */
    public boolean isLand() {
        return type == SquareType.LAND;
    }

    /**
     * Removes the piece from the cell (if it's not empty).
     */
    public void remove() {
        if (!isFree()) {
            this.piece = null;
        }
    }

    /**
     * Generate a hash code value for the object.
     *
     * @return a hash code value for the square.
     */
    @Override
    public int hashCode() {
        return Objects.hash(piece, type);
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
        final Square other = (Square) obj;
        if (!Objects.equals(this.piece, other.piece)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

    /**
     * Returns the result of calling toString for a non-null argument and "null"
     * for a null argument.
     *
     * @return a string representing the square.
     */
    @Override
    public String toString() {
        if (piece == null) {
            return "null";
        }
        return piece.toString();
    }

}
