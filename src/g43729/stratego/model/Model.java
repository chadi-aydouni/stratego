package g43729.stratego.model;

import java.util.List;

/**
 * Model of Stratego.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Facade_pattern">
 * Model pattern
 * </a>
 * @see
 * <a href="https://fr.wikipedia.org/wiki/Fa%C3%A7ade_(patron_de_conception)">
 * Façade (patron de conception)
 * </a>
 * @author EsiProf
 */
public interface Model {

    /**
     * Initializes the stratego game with a default board.
     */
    void initialize();

    /**
     * Checks if a match can start.
     *
     * @throws IllegalStateException if no board is set.
     * @throws IllegalStateException if the board set is incomplete.
     * @throws IllegalStateException if the game is over.
     */
    void start();

    /**
     * Declares if it's the end of the game.
     *
     * @return true if it's the end of the game, false otherwise.
     */
    boolean isOver();

    /**
     * Getter of board.
     *
     * @return the value of board.
     */
    Square[][] getBoard();

    /**
     * Selects a case on board that is not empty and belongs to the player.
     *
     * @param row a row.
     * @param column a column.
     * @throws IllegalArgumentException if the coordinates are out of bounds.
     * @throws IllegalStateException if the cell is empty.
     * @throws IllegalStateException if the cell is occupied by the opponent.
     */
    void select(int row, int column);

    /**
     * Gets the selected piece.
     *
     * @return the selected piece.
     * @throws NullPointerException if there is no selected piece (selected is
     * null).
     */
    Piece getSelected();

    /**
     * Gives a list of possible moves for the selected piece.
     *
     * @return a list of possible moves for the selected piece.
     */
    List<Move> getMoves();

    /**
     * Executes the movement specified for the selected piece.
     *
     * @param move a move.
     * @throws NullPointerException if the move is null.
     */
    void apply(Move move);

    /**
     * Gets the current player.
     *
     * @return the current player.
     */
    Player getCurrent();

    /**
     * Gets the winner(s) of the game.
     *
     * @return the winner(s) of the game.
     */
    List<Player> getWinners();
}
