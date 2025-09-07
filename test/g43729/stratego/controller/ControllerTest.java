package g43729.stratego.controller;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Scanner;
import g43729.stratego.view.*;
import g43729.stratego.model.*;

/**
 *
 * @author Aydouni Chadi - 43729
 */
public class ControllerTest {

    public ControllerTest() {
    }

    // ----- constructor() -----
    @Test(expected = NullPointerException.class)
    public void testCreationWithNullGame() {
        Controller ctrl = new Controller(null, new View(new Scanner(System.in)));
    }

    @Test(expected = NullPointerException.class)
    public void testCreationWithNullView() {
        Controller ctrl = new Controller(new Game(), null);
    }

    @Test(expected = NullPointerException.class)
    public void testCreationWithNullArguments() {
        Controller ctrl = new Controller(null, null);
    }

}
