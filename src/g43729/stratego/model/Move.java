package g43729.stratego.model;

import java.util.Objects;

/**
 * Represents possible moves for a piece.
 *
 * @author Aydouni Chadi - 43729
 */
public class Move {

    private Piece piece;
    private Position start;
    private Position end;

    /**
     * Constructor of Move.
     *
     * @param piece a piece.
     * @param start the initial position of the piece.
     * @param end the final position of the piece.
     * @throws NullPointerException if at least one of the arguments is null.
     */
    public Move(Piece piece, Position start, Position end) {
        if (piece == null || start == null || end == null) {
            throw new NullPointerException("one or more arguments are null.");
        }
        this.piece = piece;
        this.start = start;
        this.end = end;
    }

    /**
     * Gets the piece.
     *
     * @return the piece.
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Gets the value of start.
     *
     * @return the value of start.
     */
    public Position getStart() {
        return start;
    }

    /**
     * Gets the value of end.
     *
     * @return the value of end.
     */
    public Position getEnd() {
        return end;
    }

    /**
     * Generate a hash code value for the object.
     *
     * @return a hash code value for the move.
     */
    @Override
    public int hashCode() {
        return Objects.hash(piece, start, end);
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
        final Move other = (Move) obj;
        if (!Objects.equals(this.piece, other.piece)) {
            return false;
        }
        if (!Objects.equals(this.start, other.start)) {
            return false;
        }
        if (!Objects.equals(this.end, other.end)) {
            return false;
        }
        return true;
    }

}
