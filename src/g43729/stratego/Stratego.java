package g43729.stratego;

import g43729.stratego.model.*;
import g43729.stratego.view.*;
import g43729.stratego.controller.*;

import java.util.Scanner;

/**
 * Main instance of the game.
 *
 * @author Aydouni Chadi - 43729
 */
public class Stratego {

    /**
     * Instance of the game.
     */
    public static void main(String[] args) {
//        Initialisation du contrôleur 
        Controller ctrl = new Controller(new Game(), new View(new Scanner(System.in)));

//        Initialisation du plateau et de l'accueil
        ctrl.initialize();

//        Début du jeu
        ctrl.startGame();
    }

}
