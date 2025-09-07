package g43729.stratego.controller;

import g43729.stratego.model.*;
import g43729.stratego.view.*;
import java.util.List;

/**
 * Manages the game dynamics.
 *
 * @author Aydouni Chadi - 43729
 */
public class Controller {

    private Model game;
    private View view;

    /**
     * Constructor of Controller.
     *
     * @param game a game.
     * @param view a view.
     * @throws NullPointerException if game and/or view is null.
     */
    public Controller(Model game, View view) {
        if (game == null) {
            throw new NullPointerException("game cannot be null.");
        }
        if (view == null) {
            throw new NullPointerException("view cannot be null.");
        }
        this.game = game;
        this.view = view;
    }

    /**
     * Initializes the game (board and welcome message).
     */
    public void initialize() {
        game.initialize();
        view.initialize();
    }

    /**
     * Starts a game of Stratego.
     */
    public void startGame() {
        game.start();
        view.displayHelp();
        gameManager();
    }

    /**
     * Displays the board and manages the game behavior depending on the answer.
     */
    private void gameManager() {
        boolean gameIsOver = false;
        boolean commandIsCorrect;
        boolean hideOpponentPieces = true;
        boolean playerColorDisplayed = false;

        while (!gameIsOver) {
            // Reset du vérificateur de commande et on (ré)affiche le plateau.
            commandIsCorrect = false;
            view.displayBoard(game.getBoard(), game.getCurrent(), hideOpponentPieces);

            // On affiche le joueur courant si on ne l'a pas déjà fait une fois.
            if (!playerColorDisplayed) {
                view.displayCurrentPlayer(game.getCurrent());
                playerColorDisplayed = true;
            }

            // On demande une réponse qu'on découpe en morceaux dans un tableau.
            String ans = view.askCommand();
            String[] answerArguments = ans.split(" ");

            // ----- VERIFICATION DE LA REPONSE ----- 
            if (answerArguments[0].equals("quit")) {
                view.quit();
                break;
            }

            if (answerArguments[0].equals("help")) {
                commandIsCorrect = true;
                view.displayHelp();
            }

            if (answerArguments[0].equals("moves")) {
                commandIsCorrect = true;
                movesManager();
            }

            if (answerArguments[0].equals("select")) {
                if (answerArguments.length == 3 && answerArguments[1].matches("-?\\d+") && answerArguments[2].matches("-?\\d+")) {
                    commandIsCorrect = true;
                    selectManager(answerArguments);
                }
            }

            if (answerArguments[0].equals("apply")) {
                if (answerArguments.length == 2 && answerArguments[1].matches("-?\\d+")) {
                    commandIsCorrect = true;
                    try {
                        applyManager(answerArguments);
                        playerColorDisplayed = false;
                        gameIsOver = game.isOver();
                    } catch (Exception e) {
                        view.displayError(e.getMessage());
                    }
                }
            }

            // ----- VERIFICATION DE FIN DE TOUR -----
            if (!commandIsCorrect) {
                view.displayError("the command is incomplete/incorrect.");
            }

            if (gameIsOver) {
                gameOverManager();
            }
        }
    }

    /**
     * Instructions if the answer is "moves".
     */
    private void movesManager() {
        try {
            view.displayMoves(game.getMoves());
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Instructions if the answer is "select".
     *
     * @param answerArguments the answer's arguments.
     */
    private void selectManager(String[] answerArguments) {
        int row = Integer.parseInt(answerArguments[1]);
        int column = Integer.parseInt(answerArguments[2]);

        try {
            game.select(row, column);
            view.displaySelected(game.getSelected());
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Instructions if the answer is "apply".
     *
     * @param answerArguments the answer's arguments.
     */
    private void applyManager(String[] answerArguments) {

        /* On test l'index : si ce dernier n'est pas un indice de la liste,
        la fonction testIndexWithin() renverra une exception que pourra
        attraper et afficher le catch.
            
        Si aucune pièce n'a été sélectionnée et que le test de l'indice est
        passé, getSelected() de getMoves() renverra une exception. */
        int index = Integer.parseInt(answerArguments[1]);
        List<Move> moves = game.getMoves();
        testIndexWithin(index, moves);
        game.apply(moves.get(index));
    }

    /**
     * Instructions if the game is over.
     */
    private void gameOverManager() {
        view.displayBoard(game.getBoard(), game.getCurrent(), false);
        view.displayOver(game.getWinners());
    }

    /**
     * Checks if the index is within the list.
     *
     * @param index an index.
     */
    private void testIndexWithin(int index, List<Move> list) {
        if (index >= list.size() || index < 0) {
            throw new IllegalArgumentException("the input index doesn't exist.");
        }
    }

}
