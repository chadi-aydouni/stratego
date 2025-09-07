package g43729.stratego.model;

import g43729.stratego.model.pieces.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class puts together every element of the game. It works as a facade,
 * masking underlying code. The graphic part of the game will interact with it.
 *
 * @author Aydouni Chadi - 43729
 */
public class Game implements Model {

    private Board board;
    private Player current;
    private Player opponent;
    private Position selected;

    /**
     * Constructor of Game. The current player is RED, the opponent is BLUE.
     */
    public Game() {
        this.current = new Player(PlayerColor.RED);
        this.opponent = new Player(PlayerColor.BLUE);
    }

    /**
     * Gets the current player.
     *
     * @return the current player.
     */
    @Override
    public Player getCurrent() {
        return current;
    }

    /**
     * Initializes the Stratego game with a default board ...
     * <ul>
     * <li>Red Flag // ROW 0 COLUMN 1 </li>
     * <li>Red Spy // ROW 5 COLUMN 4 </li>
     * <li>Red Scout // ROW 5 COLUMN 0 </li>
     * <li>Red Miner // ROW 1 COLUMN 2 </li>
     * <li>Red General // ROW 3 COLUMN 2</li>
     * <li>Red Marshal // ROW 4 COLUMN 4</li>
     * <li>Red Bomb // ROW 1 COLUMN 0</li>
     * </ul>
     *  
     * <ul>
     * <li>Blue Flag // ROW 4 COLUMN 2 </li>
     * <li>Blue Spy // ROW 4 COLUMN 3 </li>
     * <li>Blue Scout // ROW 0 COLUMN 4 </li>
     * <li>Blue Miner // ROW 2 COLUMN 0 </li>
     * <li>Blue General // ROW 1 COLUMN 1</li>
     * <li>Blue Marshal // ROW 5 COLUMN 3</li>
     * <li>Blue Bomb // ROW 3 COLUMN 1</li>
     * </ul>
     * ... and adds the pieces to the players rosters.
     */
    @Override
    public void initialize() {
        board = new Board();

        // ----- RED PLAYER -----
        addPieceToBoardAndPlayerRoster(new Flag(PlayerColor.RED), new Position(0, 1));
        addPieceToBoardAndPlayerRoster(new Spy(PlayerColor.RED), new Position(5, 4));
        addPieceToBoardAndPlayerRoster(new Scout(PlayerColor.RED), new Position(5, 0));
        addPieceToBoardAndPlayerRoster(new Miner(PlayerColor.RED), new Position(1, 2));
        addPieceToBoardAndPlayerRoster(new General(PlayerColor.RED), new Position(3, 2));
        addPieceToBoardAndPlayerRoster(new Marshal(PlayerColor.RED), new Position(4, 4));
        addPieceToBoardAndPlayerRoster(new Bomb(PlayerColor.RED), new Position(1, 0));

        // ----- BLUE PLAYER -----
        addPieceToBoardAndPlayerRoster(new Flag(PlayerColor.BLUE), new Position(4, 2));
        addPieceToBoardAndPlayerRoster(new Spy(PlayerColor.BLUE), new Position(4, 3));
        addPieceToBoardAndPlayerRoster(new Scout(PlayerColor.BLUE), new Position(0, 4));
        addPieceToBoardAndPlayerRoster(new Miner(PlayerColor.BLUE), new Position(2, 0));
        addPieceToBoardAndPlayerRoster(new General(PlayerColor.BLUE), new Position(1, 1));
        addPieceToBoardAndPlayerRoster(new Marshal(PlayerColor.BLUE), new Position(5, 3));
        addPieceToBoardAndPlayerRoster(new Bomb(PlayerColor.BLUE), new Position(3, 1));

    }

    /**
     * Checks if a match can start.
     *
     * @throws IllegalStateException if no board is set.
     * @throws IllegalStateException if the board set is incomplete.
     * @throws IllegalStateException if the game is over.
     */
    @Override
    public void start() {
        if (board == null) {
            throw new IllegalStateException("the board is not set.");
        }

        if (!isComplete()) {
            throw new IllegalStateException("the board is incomplete.");
        }

        if (isOver()) {
            throw new IllegalStateException("the game is over.");
        }
    }

    /**
     * Declares if it's the end of the game :
     * <ul>
     * <li>a player lost his last flag</li>
     * <li>both player cannot move</li>
     * </ul>
     *
     * @return true if it's the end of the game, false otherwise
     */
    @Override
    public boolean isOver() {
        return !current.hasFlag() || !opponent.hasFlag() || (!hasMoves(current) && !hasMoves(opponent));
    }

    /**
     * Getter of board.
     *
     * @return the value of board.
     */
    @Override
    public Square[][] getBoard() {
        return board.getSquares();
    }

    /**
     * Checks whether or not the board is complete. Criterias : those described
     * by Initialize().
     *
     * @return true if the board is complete, false otherwise.
     */
    public boolean isComplete() {
        Game defaultGame = new Game();
        defaultGame.initialize();
        return Arrays.deepEquals(this.getBoard(), defaultGame.getBoard());
    }

    /**
     * Adds a piece to the board and the right player roster.
     *
     * @param piece a piece.
     * @param position a position.
     * @throws IllegalArgumentException if the input position is not on the
     * board.
     * @throws IllegalStateException if the input position is of water type or
     * is not empty.
     * @throws NullPointerException if the piece is null.
     *
     */
    private void addPieceToBoardAndPlayerRoster(Piece piece, Position position) {
        board.put(piece, position);
        if (piece.getColor() == current.getColor()) {
            current.addPiece(piece);
        } else {
            opponent.addPiece(piece);
        }
    }

    /**
     * Selects a cell on board that is not empty and belongs to the player.
     *
     * @param row a row.
     * @param column a column.
     * @throws IllegalArgumentException if the coordinates are out of bounds.
     * @throws IllegalStateException if the cell is empty.
     * @throws IllegalStateException if the cell is occupied by the opponent.
     */
    @Override
    public void select(int row, int column) {
        Position position = new Position(row, column);

        if (!board.isInside(position)) {
            throw new IllegalArgumentException("the input position is not on the board.");
        }

        if (board.isFree(position)) {
            throw new IllegalStateException("the input position designates an empty cell.");
        }

        if (!board.isMyOwn(position, current.getColor())) {
            throw new IllegalStateException("the input position is occupied by the opponent.");
        }

        selected = position;
    }

    /**
     * Gets the selected piece.
     *
     * @return the selected piece.
     * @throws NullPointerException if there is no selected piece (selected is
     * null).
     */
    @Override
    public Piece getSelected() {
        if (selected == null) {
            throw new NullPointerException("there is no selected piece.");
        }
        return board.getPiece(selected);
    }

    /**
     * Gives a list of possible moves for the selected piece. A piece cannot
     * leap over a lake or another piece.
     *
     * @return a list of possible moves for the selected piece.
     * @throws NullPointerException if there is no selected piece (selected is
     * null).
     */
    @Override
    public List<Move> getMoves() {
        Piece piece = getSelected();
        Position startPosition = selected;
        List<Move> moves = new ArrayList<Move>();

        if (piece.getNbSteps() != 0) {
            for (Direction direction : Direction.values()) {
                // On remet à zéro après chaque mouvement.
                Position finalPosition = startPosition;
                Boolean sightBlocked = false;

                /* 3 cas possibles :
                    1) le mouvement est valide et le chemin n'est pas bloqué.
                    2) le mouvement est valide mais le chemin est bloqué (adversaire sur case finale).
                    3) le mouvement n'est pas valide, le chemin est forcément bloqué.
                 */
                for (int i = 0; i < piece.getNbSteps() && !sightBlocked; i++) {
                    finalPosition = finalPosition.next(direction);
                    Move move = new Move(piece, startPosition, finalPosition);

                    if (board.isInside(move.getEnd()) && piece.canCross(board.getSquare(move.getEnd())) && (!board.isMyOwn(move.getEnd(), current.getColor()) || board.isFree(move.getEnd()))) {
                        moves.add(move);
                        if (!board.isFree(move.getEnd())) {
                            sightBlocked = true;
                        }
                    } else {
                        sightBlocked = true;
                    }
                }
            }
        }
        return moves;
    }

    /**
     * Executes the movement specified for the selected piece.
     *
     * @param move a move.
     * @throws NullPointerException if the move is null.
     */
    @Override
    public void apply(Move move) {
        if (move == null) {
            throw new NullPointerException("no movement has been specified.");
        }

        Piece currentPiece = move.getPiece();
        Position start = move.getStart();
        Position end = move.getEnd();

        board.remove(start);

        /* Si la case d'arrivée est libre, on y déplace la pièce.
           Sinon, c'est qu'il y a un adversaire. */
        if (board.isFree(end)) {
            board.put(currentPiece, end);
        } else {
            Piece opponentPiece = board.getPiece(end);
            if (currentPiece.isStronger(opponentPiece)) {
                opponent.remove(opponentPiece);
                board.remove(end);
                board.put(currentPiece, end);
            } else if (currentPiece.hasSameRank(opponentPiece)) {
                current.remove(currentPiece);
                opponent.remove(opponentPiece);
                board.remove(end);
            } else {
                current.remove(currentPiece);
            }

        }
        selected = null;
        swapPlayers();
    }

    /**
     * Swaps the players if the game is not over and the opponent has moves.
     */
    public void swapPlayers() {
        if (!isOver() && hasMoves(opponent)) {
            Player swap = current;
            current = opponent;
            opponent = swap;
        }
    }

    /**
     * Checks if a player still has a possible movement.
     *
     * @param player a player.
     * @return true if a movement is still possible, false otherwise.
     */
    public boolean hasMoves(Player player) {
        List<Position> playerPiecesPosition = board.getTakenSquare(player);

        for (Position position : playerPiecesPosition) {
            /* getTakenSquare() nous assure déjà d'avoir des positions à 
            l'intérieur et de la bonne couleur. Pas besoin de passer par la
            méthode select. */
            selected = new Position(position.getRow(), position.getColumn());
            List<Move> PieceMoves = getMoves();
            if (!PieceMoves.isEmpty()) {
                selected = null;
                return true;
            }
        }
        selected = null;
        return false;
    }

    /**
     * Gets the winner(s) of the game.
     *
     * @return the winner(s) of the game.
     * @throws IllegalStateException if the game is not over.
     */
    @Override
    public List<Player> getWinners() {
        if (!isOver()) {
            throw new IllegalStateException("the game is not over.");
        }

        List<Player> winnersList = new ArrayList<Player>();
        if (current.hasFlag() && hasMoves(current)) {
            winnersList.add(current);
        } else if (opponent.hasFlag() && hasMoves(opponent)) {
            winnersList.add(opponent);

        } else {
            winnersList.add(current);
            winnersList.add(opponent);
        }
        return winnersList;
    }

}
