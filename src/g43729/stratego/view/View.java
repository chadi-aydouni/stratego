package g43729.stratego.view;

import java.util.Scanner;
import g43729.stratego.model.*;
import java.util.List;

/**
 * Allows interaction with the users and displays the game.
 *
 * @author Aydouni Chadi - 43729
 */
public class View {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    private static final String ANSI_BOLD = "\u001B[1m";
    private int SEPARATION_LENGTH = 10 + (8 * new Board().getSquares()[0].length);
    private Scanner in;

    /**
     * Constructor of View.
     *
     * @param in an instance of Scanner.
     */
    public View(Scanner in) {
        this.in = in;
    }

    /**
     * Displays a welcome message.
     */
    public void initialize() {
        System.out.println("Welcome to " + ANSI_BOLD + "Stratego!" + ANSI_RESET);
        System.out.println("");
    }

    /**
     * Displays a goodbye message.
     */
    public void quit() {
        System.out.println("Goodbye !");
        displaySeparationDash(SEPARATION_LENGTH);
    }

    /**
     * Displays an error.
     *
     * @param message message produced by the error.
     */
    public void displayError(String message) {
        System.out.println(ANSI_BOLD + "Error: " + ANSI_RESET + message);
        displaySeparationDash(SEPARATION_LENGTH);
    }

    /**
     * Displays the selected piece.
     *
     * @param piece a piece.
     */
    public void displaySelected(Piece piece) {

        if (piece.getColor() == PlayerColor.RED) {
            System.out.println(ANSI_BOLD + ANSI_RED + displayColor(piece.getColor()) + " " + displayPieceName(piece) + ANSI_RESET + " selected");
        }

        if (piece.getColor() == PlayerColor.BLUE) {
            System.out.println(ANSI_BOLD + ANSI_BLUE + displayColor(piece.getColor()) + " " + displayPieceName(piece) + ANSI_RESET + " selected");
        }

        displaySeparationDash(SEPARATION_LENGTH);
    }

    /**
     * Displays the possible moves for the selected piece.
     *
     * @param moves the possible moves for the selected piece.
     */
    public void displayMoves(List<Move> moves) {
        System.out.println(ANSI_BOLD + moves.size() + ANSI_RESET + " possible movement(s)");

        for (Move move : moves) {
            tabulation(1);
            System.out.println(moves.indexOf(move) + " - " + ANSI_BOLD + displayColor(move.getPiece().getColor()) + " " + displayPieceName(move.getPiece()) + ANSI_RESET + " can move onto line "
                    + ANSI_BOLD + move.getEnd().getRow() + ANSI_RESET + " and column " + ANSI_BOLD + move.getEnd().getColumn() + ANSI_RESET);
        }
    }

    /**
     * Displays the current player.
     *
     * @param player a player.
     */
    public void displayCurrentPlayer(Player player) {
        System.out.print(ANSI_BOLD + "Turn of ");
        if (player.getColor() == PlayerColor.RED) {
            System.out.println(ANSI_RED + displayColor(player.getColor()) + ANSI_RESET);
        }

        if (player.getColor() == PlayerColor.BLUE) {
            System.out.println(ANSI_BLUE + displayColor(player.getColor()) + ANSI_RESET);
        }
        System.out.println("");
    }

    /**
     * Displays a list of commands.
     */
    public void displayHelp() {
        System.out.println(ANSI_BOLD + "Commands:" + ANSI_RESET);
        System.out.println("* help: displays the commands");
        System.out.println("* select [row] [column] : selects a piece at the designated position");
        System.out.println("* moves : shows the possible movements for the selected piece");
        System.out.println("* apply [index] : moves the selected piece with the selected movement");
        System.out.println("* quit: exits the game");
        displaySeparationDash(SEPARATION_LENGTH);
    }

    /**
     * Asks the user to enter a command.
     *
     * @return the input command stringified.
     */
    public String askCommand() {
        System.out.print("Input your command: ");
        String ans = in.nextLine().toLowerCase();
        displaySeparationDash(SEPARATION_LENGTH);
        return ans;
    }

    /**
     * Displays the game board. If piecesHidden is true, the opponent's pieces
     * ranks won't be shown. Otherwise, every piece will be shown.
     *
     * @param squares the game board.
     * @param player a player.
     * @param piecesHidden indicates if every piece must be shown.
     */
    public void displayBoard(Square[][] squares, Player player, boolean piecesHidden) {
        displaySeparationEquals(SEPARATION_LENGTH);
        displayBoardHeader(squares);
        displaySeparationEquals(SEPARATION_LENGTH);

        if (piecesHidden) {
            displayHiddenBoardBody(squares, player);
        } else {
            displayWholeBoardBody(squares);
        }

        System.out.println("");
    }

    /**
     * Shows a congratulations message to the winner(s).
     *
     * @param winners a list of winner(s).
     */
    public void displayOver(List<Player> winners) {
        displaySeparationDash(SEPARATION_LENGTH);
        System.out.print(ANSI_BOLD + "Congratulations " + ANSI_RESET);
        for (Player winner : winners) {
            if (winner.getColor() == PlayerColor.RED) {
                System.out.print(ANSI_RED + displayColor(winner.getColor()) + ANSI_RESET + " ");
            }

            if (winner.getColor() == PlayerColor.BLUE) {
                System.out.print(ANSI_BLUE + displayColor(winner.getColor()) + ANSI_RESET + " ");
            }
        }
        System.out.println("!");
        displaySeparationDash(SEPARATION_LENGTH);
    }

    /**
     * Displays the board header.
     *
     * @param squares a board.
     */
    private void displayBoardHeader(Square[][] squares) {
        System.out.print("  col# ||  ");
        for (int i = 0; i < squares[0].length; i++) {
            System.out.print(" | " + toZeroFormat(i) + " | ");
        }
        System.out.println("");
    }

    /**
     * Displays a sequence of "=".
     *
     * @param nb the number of "=" desired.
     */
    private void displaySeparationEquals(int nb) {
        for (int i = 0; i < nb; i++) {
            System.out.print("=");
        }
        System.out.println("");
    }

    /**
     * Displays a sequence of "-".
     *
     * @param nb the number of "-" desired.
     */
    private void displaySeparationDash(int nb) {
        for (int i = 0; i < nb; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }

    /**
     * Displays the board body where opponent pieces are hidden.
     *
     * @param squares a board.
     * @param player a player.
     */
    private void displayHiddenBoardBody(Square[][] squares, Player player) {
        for (int i = 0; i < squares.length; i++) {
            System.out.print("row#" + toZeroFormat(i) + " ||  ");
            for (Square square : squares[i]) {

                // ----- JOUEUR COURANT ROUGE -----
                if (player.getColor() == PlayerColor.RED) {
                    if (!square.isLand()) {
                        System.out.print(" | " + ANSI_BLUE_BACKGROUND + "  " + ANSI_RESET + " | ");
                    } else if (square.isFree()) {
                        System.out.print(" | " + ANSI_WHITE_BACKGROUND + "  " + ANSI_RESET + " | ");
                    } else if (square.getPiece().getColor() == PlayerColor.RED) {
                        System.out.print(" | " + ANSI_WHITE_BACKGROUND + ANSI_RED + displayPieceAbbr(square.getPiece()) + ANSI_RESET + " | ");
                    } else {
                        System.out.print(" | " + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "??" + ANSI_RESET + " | ");
                    }
                }

                // ----- JOUEUR COURANT BLEU -----
                if (player.getColor() == PlayerColor.BLUE) {
                    if (!square.isLand()) {
                        System.out.print(" | " + ANSI_BLUE_BACKGROUND + "  " + ANSI_RESET + " | ");
                    } else if (square.isFree()) {
                        System.out.print(" | " + ANSI_WHITE_BACKGROUND + "  " + ANSI_RESET + " | ");
                    } else if (square.getPiece().getColor() == PlayerColor.RED) {
                        System.out.print(" | " + ANSI_WHITE_BACKGROUND + ANSI_RED + "??" + ANSI_RESET + " | ");
                    } else {
                        System.out.print(" | " + ANSI_WHITE_BACKGROUND + ANSI_BLUE + displayPieceAbbr(square.getPiece()) + ANSI_RESET + " | ");
                    }
                }
            }
            System.out.println("");
        }
    }

    /**
     * Displays the board body where evey piece is shown.
     *
     * @param squares a board.
     */
    private void displayWholeBoardBody(Square[][] squares) {
        for (int i = 0; i < squares.length; i++) {
            System.out.print("row#" + toZeroFormat(i) + " ||  ");
            for (Square square : squares[i]) {
                if (!square.isLand()) {
                    System.out.print(" | " + ANSI_BLUE_BACKGROUND + "  " + ANSI_RESET + " | ");
                } else if (square.isFree()) {
                    System.out.print(" | " + ANSI_WHITE_BACKGROUND + "  " + ANSI_RESET + " | ");
                } else if (square.getPiece().getColor() == PlayerColor.RED) {
                    System.out.print(" | " + ANSI_WHITE_BACKGROUND + ANSI_RED + displayPieceAbbr(square.getPiece()) + ANSI_RESET + " | ");
                } else {
                    System.out.print(" | " + ANSI_WHITE_BACKGROUND + ANSI_BLUE + displayPieceAbbr(square.getPiece()) + ANSI_RESET + " | ");
                }
            }
            System.out.println("");
        }
    }

    /**
     * Gives the abbreviation of the piece's name.
     *
     * @param piece a piece.
     * @return the abbreviation of the piece's name.
     */
    private String displayPieceAbbr(Piece piece) {
        switch (piece.getRank()) {
            case 0:
                return "FL";
            case 1:
                return "SP";
            case 2:
                return "SC";
            case 3:
                return "MI";
            case 9:
                return "GE";
            case 10:
                return "MA";
            case 11:
                return "BO";
            default:
                break;
        }
        return null;
    }

    /**
     * Gives the full name of the piece.
     *
     * @param piece a piece.
     * @return the full name of the piece.
     */
    private String displayPieceName(Piece piece) {
        switch (piece.getRank()) {
            case 0:
                return "FLAG";
            case 1:
                return "SPY";
            case 2:
                return "SCOUT";
            case 3:
                return "MINER";
            case 9:
                return "GENERAL";
            case 10:
                return "MARSHAL";
            case 11:
                return "BOMB";
            default:
                break;
        }
        return null;
    }

    /**
     * Displays the color's name.
     *
     * @param color a color.
     * @return the color's name.
     */
    private String displayColor(PlayerColor color) {
        if (color == PlayerColor.RED) {
            return "RED";
        } else {
            return "BLUE";
        }
    }

    /**
     * Stringifies an integer onto this format : "0x" where x is the integer.
     *
     * @param nb a number.
     * @return a string representing the integer.
     */
    private String toZeroFormat(int nb) {
        return String.format("%02d", nb);
    }

    /**
     * Tabulates "nb" times.
     *
     * @param nb number of tabulations.
     */
    private void tabulation(int nb) {
        for (int i = 0; i < nb; i++) {
            System.out.print('\t');
        }
    }

}
