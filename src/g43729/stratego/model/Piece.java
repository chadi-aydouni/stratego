package g43729.stratego.model;

import java.util.Objects;

/**
 * The pieces represent military units. Each piece has a rank (a number)
 * representing its position in the hierarchy, a color representing its camp and
 * a number of steps available.
 *
 * @author Aydouni Chadi - 43729
 */
public class Piece {

    private int rank;
    private PlayerColor color;
    private int nbSteps;

    /**
     * Constructor of Piece.
     *
     * @param rank rank of the piece.
     * @param color color of the piece.
     * @throws IllegalArgumentException if the rank is null.
     */
    public Piece(int rank, PlayerColor color) {
        if (rank < 0) {
            throw new IllegalArgumentException("the input rank is incorrect : " + rank);
        }
        this.rank = rank;
        this.color = color;
        this.nbSteps = 1;
    }

    /**
     * Constructor of Piece. Takes into account the number of steps specified.
     *
     * @param rank rank of the piece.
     * @param color color of the piece.
     * @param nbSteps number of steps of the piece.
     * @throws IllegalArgumentException if the rank is null or if nbSteps is
     * negative.
     */
    public Piece(int rank, PlayerColor color, int nbSteps) {
        if (rank < 0) {
            throw new IllegalArgumentException("the input rank is incorrect : " + rank);
        }
        if (nbSteps < 0) {
            throw new IllegalArgumentException("the number of steps is incorrect :" + nbSteps);
        }
        this.rank = rank;
        this.color = color;
        this.nbSteps = nbSteps;
    }

    /**
     * Get the value of rank.
     *
     * @return the value of rank.
     */
    public int getRank() {
        return rank;
    }

    /**
     * Get the value of color.
     *
     * @return the value of color.
     */
    public PlayerColor getColor() {
        return color;
    }

    /**
     * Gets the value of NbSteps.
     *
     * @return the value of NbSteps.
     */
    public int getNbSteps() {
        return nbSteps;
    }

    /**
     * Indicates if the piece is stronger than the one specified.
     *
     * @param other a piece.
     * @return true if the piece is stronger than the other, false otherwise.
     */
    public boolean isStronger(Piece other) {
        return this.rank > other.getRank();
    }

    /**
     * Indicates if the piece has the same rank as the piece specified.
     *
     * @param other a piece.
     * @return true if the pieces have the same rank, false otherwise.
     */
    public boolean hasSameRank(Piece other) {
        return this.rank == other.getRank();
    }

    /**
     * Indicates if the piece can cross the square (land type).
     *
     * @param square a square.
     * @return true if the piece can cross the square, false otherwise.
     */
    public boolean canCross(Square square) {
        return square.isLand();
    }

    /**
     * Generate a hash code value for the object.
     *
     * @return a hash code value for the piece.
     */
    @Override
    public int hashCode() {
        return Objects.hash(rank, color, nbSteps);
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
        final Piece other = (Piece) obj;
        if (this.rank != other.rank) {
            return false;
        }
        if (this.nbSteps != other.nbSteps) {
            return false;
        }
        if (this.color != other.color) {
            return false;
        }
        return true;
    }

    /**
     * Returns the result of calling toString for a non-null argument and "null"
     * for a null argument.
     *
     * @return a string representing the piece.
     */
    @Override
    public String toString() {
        return "Rank: " + rank + " " + "Color: " + color + "NbSteps : " + nbSteps;
    }

}
