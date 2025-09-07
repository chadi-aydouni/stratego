package g43729.stratego.model;

/**
 * A piece can move to four directions : UP, DOWN, LEFT, RIGHT.
 *
 * @author Aydouni Chadi - 43729
 */
public enum Direction {
    UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);
    private int row;
    private int column;

    /**
     * Constructor of Direction.
     *
     * @param row the row moving value of a direction.
     * @param column the column moving value of a direction.
     */
    private Direction(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Gets the row moving value of a direction.
     *
     * @return the row moving value of a direction.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column moving value of a direction.
     *
     * @return the column moving value of a direction.
     */
    public int getColumn() {
        return column;
    }

}
