package g43729.stratego.model;

import java.util.Objects;

/**
 * This class represents coordinates of the board cells. Each position is
 * defined by a row and a column.
 *
 * @author Aydouni Chadi - 43729
 */
public class Position {

    private int row;
    private int column;

    /**
     * Constructor of Position.
     *
     * @param row row of position.
     * @param column column of position.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Getter of column.
     *
     * @return the value of column.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Getter of row.
     *
     * @return the value of row.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gives the position next to the current position in the given direction.
     *
     * @param direction a direction.
     * @return the position next to the current position in the given direction.
     */
    public Position next(Direction direction) {
        return new Position(row + direction.getRow(), column + direction.getColumn());
    }

    /**
     * Generate a hash code value for the object.
     *
     * @return a hash code value for the position.
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, column);
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
        final Position other = (Position) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }
        return true;
    }

    /**
     * Returns the result of calling toString for a non-null argument and "null"
     * for a null argument.
     *
     * @return a string representing the position.
     */
    @Override
    public String toString() {
        return "(" + row + ";" + column + ")";
    }

}
