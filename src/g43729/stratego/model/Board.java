package g43729.stratego.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the board. A board is defined by an array of Square
 * (cells).
 *
 * @author Aydouni Chadi - 43729
 */
public class Board {

    private Square[][] squares;
    private final int DEFAULT_ROWS = 6;
    private final int DEFAULT_COLUMNS = 5;

    /**
     * Constructor of Board. Generates an empty array filled with squares of
     * different types.
     */
    public Board() {
        squares = new Square[DEFAULT_ROWS][DEFAULT_COLUMNS];
        for (Square[] square : squares) {
            for (int j = 0; j < square.length; j++) {
                square[j] = new Square(SquareType.LAND);
            }
        }

        addLakeToBoard(new Position(2, 1));
        addLakeToBoard(new Position(2, 2));
        addLakeToBoard(new Position(2, 3));
    }

    /**
     * Adds a lake to the given position on board.
     *
     * @param position a position.
     * @throws IllegalArgumentException if the input position is not on the
     * board.
     */
    private void addLakeToBoard(Position position) {
        if (!isInside(position)) {
            throw new IllegalArgumentException("the input position is not on the board: " + position);
        }
        squares[position.getRow()][position.getColumn()] = new Square(SquareType.WATER);
    }

    /**
     * Getter of squares.
     *
     * @return the value of squares.
     */
    public Square[][] getSquares() {
        return squares;
    }

    /**
     * Indicates whether or not a position is inside the board.
     *
     * @param position a position.
     * @return true if position is inside the board, false otherwise.
     */
    public boolean isInside(Position position) {
        int row = position.getRow();
        int column = position.getColumn();

        if (row < 0 || row >= DEFAULT_ROWS) {
            return false;
        }

        if (column < 0 || column >= DEFAULT_COLUMNS) {
            return false;
        }

        return true;
    }

    /**
     * Gives the square at the input position.
     *
     * @param position a position.
     * @return the square at the input position.
     * @throws IllegalArgumentException if the input position is not on the
     * board.
     */
    public Square getSquare(Position position) {
        if (!isInside(position)) {
            throw new IllegalArgumentException("the input position is not on the board: " + position);
        }
        return squares[position.getRow()][position.getColumn()];
    }

    /**
     * Places a piece on a position on board.
     *
     * @param piece a piece.
     * @param position a position.
     * @throws IllegalArgumentException if the input position is not on the
     * board.
     * @throws IllegalStateException if the position is of water type or is not
     * empty.
     * @throws NullPointerException if the piece is null.
     *
     */
    public void put(Piece piece, Position position) {
        getSquare(position).put(piece);
    }

    /**
     * Removes a piece on a position on board.
     *
     * @param position a position.
     * @throws IllegalArgumentException if the input position is not on the
     * board.
     */
    public void remove(Position position) {
        // remove() retire la pièce et pas le Square.
        getSquare(position).remove();
    }

    /**
     * Checks if a square at a position is free (piece is null).
     *
     * @param position a position.
     * @return true if the square is free, false otherwise.
     * @throws IllegalArgumentException if the input position is not on the
     * board.
     */
    public boolean isFree(Position position) {
        return getSquare(position).isFree();
    }

    /**
     * Indicates if a piece (on a case at a position) has the color specified.
     *
     * @param position a position.
     * @param color a color.
     * @return true if the piece has the same color, false otherwise.
     * @throws IllegalArgumentException if the input position is not on the
     * board.
     */
    public boolean isMyOwn(Position position, PlayerColor color) {
        return getSquare(position).isMyOwn(color);
    }

    /**
     * Gives the piece at the input position.
     *
     * @param position a position.
     * @return the piece at the input position.
     * @throws IllegalArgumentException if the input position is not on the
     * board.
     */
    public Piece getPiece(Position position) {
        return getSquare(position).getPiece();
    }

    /**
     * Gives a list of the positions occupied by the player.
     *
     * @param player a player.
     * @return a list of the positions occupied by the player.
     */
    public List<Position> getTakenSquare(Player player) {
        List<Position> playerPositions = new ArrayList<Position>();

        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                if (squares[i][j].isMyOwn(player.getColor())) {
                    playerPositions.add(new Position(i, j));
                }
            }
        }
        return playerPositions;
    }

}
