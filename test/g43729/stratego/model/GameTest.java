package g43729.stratego.model;

import static g43729.stratego.model.PlayerColor.BLUE;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static g43729.stratego.model.PlayerColor.RED;
import g43729.stratego.model.pieces.*;

public class GameTest {

    private final Square[][] defaultBoard = {
        {new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND)},
        {new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND)},
        {new Square(SquareType.LAND), new Square(SquareType.WATER), new Square(SquareType.WATER), new Square(SquareType.WATER), new Square(SquareType.LAND)},
        {new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND)},
        {new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND)},
        {new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND), new Square(SquareType.LAND)}};

    @Before
    public void setUp() throws Exception {
        defaultBoard[0][1].put(new Flag(RED));
        defaultBoard[5][4].put(new Spy(RED));
        defaultBoard[5][0].put(new Scout(RED));
        defaultBoard[1][2].put(new Miner(RED));
        defaultBoard[3][2].put(new General(RED));
        defaultBoard[4][4].put(new Marshal(RED));
        defaultBoard[1][0].put(new Bomb(RED));

        defaultBoard[4][2].put(new Flag(BLUE));
        defaultBoard[4][3].put(new Spy(BLUE));
        defaultBoard[0][4].put(new Scout(BLUE));
        defaultBoard[2][0].put(new Miner(BLUE));
        defaultBoard[1][1].put(new General(BLUE));
        defaultBoard[5][3].put(new Marshal(BLUE));
        defaultBoard[3][1].put(new Bomb(BLUE));
    }

    // ----- initialize() -----
    @Test
    public void testInitialize() {
        Game instance = new Game();
        instance.initialize();
        Square[][] result = instance.getBoard();
        assertArrayEquals(defaultBoard, result);
    }

    // ----- start() -----
    @Test(expected = IllegalStateException.class)
    public void testStartWhenNoBoard() {
        Game instance = new Game();
        instance.start();
    }

    @Test
    public void testStartWhenInitializeDone() {
        Game instance = new Game();
        instance.initialize();
        instance.start();
        assertFalse(instance.isOver());
    }

    // ----- isOver() -----
    @Test
    public void testIsOverWhenGameBegin() {
        Game instance = new Game();
        instance.initialize();
        assertFalse(instance.isOver());
    }

    @Test
    public void testIsOverWhenBlueHasNoFlag() {
        Game instance = new Game();
        instance.initialize();

        instance.select(3, 2);
        instance.apply(instance.getMoves().get(0));

        boolean result = instance.isOver();
        assertTrue(result);
    }

    @Test
    public void testIsOverWhenRedHasNoFlag() {
        Game instance = new Game();
        instance.initialize();

        instance.select(3, 2);
        instance.apply(instance.getMoves().get(1));

        instance.select(1, 1);
        instance.apply(instance.getMoves().get(0));

        boolean result = instance.isOver();
        assertTrue(result);
    }

    // ----- getBoard() -----
    @Test
    public void testGetBoardWhenGameBegin() {
        Game instance = new Game();
        instance.initialize();
        Square[][] expResult = defaultBoard;
        Square[][] result = instance.getBoard();
        assertArrayEquals(expResult, result);
    }

    // ----- getCurrent() -----
    @Test
    public void testGetCurrent() {
        Game instance = new Game();
        instance.initialize();
        Player expResult = new Player(PlayerColor.RED);
        expResult.addPiece(new Flag(PlayerColor.RED));
        expResult.addPiece(new Spy(PlayerColor.RED));
        expResult.addPiece(new Scout(PlayerColor.RED));
        expResult.addPiece(new Miner(PlayerColor.RED));
        expResult.addPiece(new General(PlayerColor.RED));
        expResult.addPiece(new Marshal(PlayerColor.RED));
        expResult.addPiece(new Bomb(PlayerColor.RED));
        Player result = instance.getCurrent();
        assertEquals(expResult, result);
    }

    // ----- select() -----
    // Current est RED quand le jeu est initialisé.
    @Test(expected = IllegalArgumentException.class)
    public void testSelectWhenPositionOutOfBounds() {
        int row = -1;
        int column = 1;
        Game instance = new Game();
        instance.initialize();
        instance.select(row, column);
    }

    @Test(expected = IllegalStateException.class)
    public void testSelectWhenEmptyCase() {
        int row = 0;
        int column = 0;
        Game instance = new Game();
        instance.initialize();
        instance.select(row, column);
    }

    @Test(expected = IllegalStateException.class)
    public void testSelectWhenCaseOccupiedByOpponent() {
        int row = 4;
        int column = 2;
        Game instance = new Game();
        instance.initialize();
        instance.select(row, column);
    }

    @Test
    public void testSelectWhenCaseOccupiedByCurrent() {
        int row = 0;
        int column = 1;
        Game instance = new Game();
        instance.initialize();
        instance.select(row, column);
        assertEquals(instance.getSelected(), new Flag(PlayerColor.RED));
    }

    // ----- getSelected() -----
    @Test(expected = NullPointerException.class)
    public void testGetSelectedWhenNull() {
        Game instance = new Game();
        instance.initialize();
        Piece expResult = null;
        Piece result = instance.getSelected();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetSelectedWhenDefined() {
        Game instance = new Game();
        instance.initialize();
        instance.select(0, 1);
        Piece expResult = new Flag(PlayerColor.RED);
        Piece result = instance.getSelected();
        assertEquals(expResult, result);
    }

    // ----- getMoves() -----
    /*
    @Test
    public void testGetMovesWhenTemplate() {
        Game instance = new Game();
        instance.initialize();
        Position pos = new Position(0, 0);
        
        instance.select(pos.getRow(), pos.getColumn());

        Move UP = new Move(instance.getSelected(), pos, pos.next(Direction.UP));
        Move DOWN = new Move(instance.getSelected(), pos, pos.next(Direction.DOWN));
        Move LEFT = new Move(instance.getSelected(), pos, pos.next(Direction.LEFT));
        Move RIGHT = new Move(instance.getSelected(), pos, pos.next(Direction.RIGHT));
        List<Move> expResult = new ArrayList();

        expResult.add(UP);
        expResult.add(DOWN);
        expResult.add(LEFT);
        expResult.add(RIGHT);

        List<Move> result = instance.getMoves();
        assertEquals(expResult, result);
    } */
    @Test(expected = NullPointerException.class)
    public void testGetMovesWhenSelectedNull() {
        Game instance = new Game();
        instance.initialize();
        List<Move> expResult = null;
        List<Move> result = instance.getMoves();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMovesWhenNextToLake() {
        Game instance = new Game();
        instance.initialize();
        Position pos = new Position(1, 2);

        instance.select(pos.getRow(), pos.getColumn());

        Move UP = new Move(instance.getSelected(), pos, pos.next(Direction.UP));
        Move LEFT = new Move(instance.getSelected(), pos, pos.next(Direction.LEFT));
        Move RIGHT = new Move(instance.getSelected(), pos, pos.next(Direction.RIGHT));
        List<Move> expResult = new ArrayList<Move>();

        expResult.add(UP);
        expResult.add(LEFT);
        expResult.add(RIGHT);

        List<Move> result = instance.getMoves();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMovesWhenNextToOpponent() {
        Game instance = new Game();
        instance.initialize();
        Position pos = new Position(3, 2);

        instance.select(pos.getRow(), pos.getColumn());

        Move DOWN = new Move(instance.getSelected(), pos, pos.next(Direction.DOWN));
        Move LEFT = new Move(instance.getSelected(), pos, pos.next(Direction.LEFT));
        Move RIGHT = new Move(instance.getSelected(), pos, pos.next(Direction.RIGHT));
        List<Move> expResult = new ArrayList<Move>();

        expResult.add(DOWN);
        expResult.add(LEFT);
        expResult.add(RIGHT);

        List<Move> result = instance.getMoves();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMovesWhenNextToCurrent() {
        Game instance = new Game();
        instance.initialize();
        Position pos = new Position(5, 4);

        instance.select(pos.getRow(), pos.getColumn());

        Move LEFT = new Move(instance.getSelected(), pos, pos.next(Direction.LEFT));
        List<Move> expResult = new ArrayList<Move>();

        expResult.add(LEFT);

        List<Move> result = instance.getMoves();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMovesWhenNextToNothing() {
        Game instance = new Game();
        instance.initialize();
        Position pos = new Position(3, 4);

        instance.select(4, 4);
        instance.apply(instance.getMoves().get(0));

        instance.select(2, 0);
        instance.apply(instance.getMoves().get(0));

        instance.select(pos.getRow(), pos.getColumn());

        Move UP = new Move(instance.getSelected(), pos, pos.next(Direction.UP));
        Move DOWN = new Move(instance.getSelected(), pos, pos.next(Direction.DOWN));
        Move LEFT = new Move(instance.getSelected(), pos, pos.next(Direction.LEFT));
        List<Move> expResult = new ArrayList<Move>();

        expResult.add(UP);
        expResult.add(DOWN);
        expResult.add(LEFT);

        List<Move> result = instance.getMoves();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMovesWhenOnEdge() {
        // bottom edge
        Game instance = new Game();
        instance.initialize();
        Position pos = new Position(5, 3);

        instance.select(4, 4);
        instance.apply(instance.getMoves().get(1));

        instance.select(pos.getRow(), pos.getColumn());

        Move UP = new Move(instance.getSelected(), pos, pos.next(Direction.UP));
        Move LEFT = new Move(instance.getSelected(), pos, pos.next(Direction.LEFT));
        Move RIGHT = new Move(instance.getSelected(), pos, pos.next(Direction.RIGHT));
        List<Move> expResult = new ArrayList<Move>();

        expResult.add(UP);
        expResult.add(LEFT);
        expResult.add(RIGHT);

        List<Move> result = instance.getMoves();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMovesWhenOnCorner() {
        // bottom right corner
        Game instance = new Game();
        instance.initialize();
        Position pos = new Position(5, 4);

        instance.select(4, 4);
        instance.apply(instance.getMoves().get(1));

        instance.select(5, 3);
        instance.apply(instance.getMoves().get(1));

        instance.select(pos.getRow(), pos.getColumn());

        Move UP = new Move(instance.getSelected(), pos, pos.next(Direction.UP));
        Move LEFT = new Move(instance.getSelected(), pos, pos.next(Direction.LEFT));
        List<Move> expResult = new ArrayList<Move>();

        expResult.add(UP);
        expResult.add(LEFT);

        List<Move> result = instance.getMoves();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMovesWhenBomb() {
        Game instance = new Game();
        instance.initialize();
        Position pos = new Position(1, 0);

        instance.select(pos.getRow(), pos.getColumn());

        List<Move> expResult = new ArrayList<Move>();
        List<Move> result = instance.getMoves();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMovesWhenFlag() {
        Game instance = new Game();
        instance.initialize();
        Position pos = new Position(0, 1);

        instance.select(pos.getRow(), pos.getColumn());

        List<Move> expResult = new ArrayList<Move>();
        List<Move> result = instance.getMoves();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMovesWhenScout() {
        Game instance = new Game();
        instance.initialize();
        Position pos = new Position(5, 0);

        instance.select(pos.getRow(), pos.getColumn());

        Move UP = new Move(instance.getSelected(), pos, pos.next(Direction.UP));
        Move UP2 = new Move(instance.getSelected(), pos, pos.next(Direction.UP).next(Direction.UP));
        Move RIGHT = new Move(instance.getSelected(), pos, pos.next(Direction.RIGHT));
        Move RIGHT2 = new Move(instance.getSelected(), pos, pos.next(Direction.RIGHT).next(Direction.RIGHT));
        List<Move> expResult = new ArrayList<Move>();

        expResult.add(UP);
        expResult.add(UP2);
        expResult.add(RIGHT);
        expResult.add(RIGHT2);

        List<Move> result = instance.getMoves();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMovesWhenScoutAndLakeInTheWay() {
        Game instance = new Game();
        instance.initialize();
        Position pos = new Position(1, 3);

        instance.select(5, 0);
        instance.apply(instance.getMoves().get(0));

        instance.select(0, 4);
        instance.apply(instance.getMoves().get(2));

        instance.select(1, 2);
        instance.apply(instance.getMoves().get(0));

        instance.select(0, 3);
        instance.apply(instance.getMoves().get(0));

        instance.select(4, 4);
        instance.apply(instance.getMoves().get(1));

        instance.select(pos.getRow(), pos.getColumn());

        // can't cross bottom lake
        Move UP = new Move(instance.getSelected(), pos, pos.next(Direction.UP));
        Move LEFT = new Move(instance.getSelected(), pos, pos.next(Direction.LEFT));
        Move RIGHT = new Move(instance.getSelected(), pos, pos.next(Direction.RIGHT));
        List<Move> expResult = new ArrayList<Move>();

        expResult.add(UP);
        expResult.add(LEFT);
        expResult.add(RIGHT);

        List<Move> result = instance.getMoves();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMovesWhenScoutAndOpponentTwoCellsAway() {
        Game instance = new Game();
        instance.initialize();
        Position pos = new Position(4, 0);

        instance.select(5, 0);
        instance.apply(instance.getMoves().get(0));

        instance.select(4, 3);
        instance.apply(instance.getMoves().get(0));

        instance.select(pos.getRow(), pos.getColumn());

        Move UP = new Move(instance.getSelected(), pos, pos.next(Direction.UP));
        Move UP2 = new Move(instance.getSelected(), pos, pos.next(Direction.UP).next(Direction.UP));
        Move DOWN = new Move(instance.getSelected(), pos, pos.next(Direction.DOWN));
        Move RIGHT = new Move(instance.getSelected(), pos, pos.next(Direction.RIGHT));
        Move RIGHT2 = new Move(instance.getSelected(), pos, pos.next(Direction.RIGHT).next(Direction.RIGHT));

        List<Move> expResult = new ArrayList<Move>();

        expResult.add(UP);
        expResult.add(UP2);
        expResult.add(DOWN);
        expResult.add(RIGHT);
        expResult.add(RIGHT2);

        List<Move> result = instance.getMoves();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMovesWhenScoutAndCurrentTwoCellsAway() {
        Game instance = new Game();
        instance.initialize();
        Position pos = new Position(1, 4);

        instance.select(5, 0);
        instance.apply(instance.getMoves().get(0));

        instance.select(4, 3);
        instance.apply(instance.getMoves().get(0));

        instance.select(4, 4);
        instance.apply(instance.getMoves().get(1));

        instance.select(3, 3);
        instance.apply(instance.getMoves().get(2));

        instance.select(1, 2);
        instance.apply(instance.getMoves().get(0));

        instance.select(0, 4);
        instance.apply(instance.getMoves().get(0));

        instance.select(0, 2);
        instance.apply(instance.getMoves().get(0));

        instance.select(pos.getRow(), pos.getColumn());

        Move UP = new Move(instance.getSelected(), pos, pos.next(Direction.UP));
        Move DOWN = new Move(instance.getSelected(), pos, pos.next(Direction.DOWN));
        Move LEFT = new Move(instance.getSelected(), pos, pos.next(Direction.LEFT));
        Move LEFT2 = new Move(instance.getSelected(), pos, pos.next(Direction.LEFT).next(Direction.LEFT));
        List<Move> expResult = new ArrayList<Move>();

        expResult.add(UP);
        expResult.add(DOWN);
        expResult.add(LEFT);
        expResult.add(LEFT2);

        List<Move> result = instance.getMoves();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMovesWhenScoutAndPieceInTheWay() {
        // can't jump over piece
        Game instance = new Game();
        instance.initialize();
        Position pos = new Position(0, 4);

        instance.select(1, 2);
        instance.apply(instance.getMoves().get(0));

        instance.select(5, 3);
        instance.apply(instance.getMoves().get(0));

        instance.select(0, 2);
        instance.apply(instance.getMoves().get(1));

        instance.select(pos.getRow(), pos.getColumn());

        Move DOWN = new Move(instance.getSelected(), pos, pos.next(Direction.DOWN));
        Move DOWN2 = new Move(instance.getSelected(), pos, pos.next(Direction.DOWN).next(Direction.DOWN));
        Move LEFT = new Move(instance.getSelected(), pos, pos.next(Direction.LEFT));
        List<Move> expResult = new ArrayList<Move>();

        expResult.add(DOWN);
        expResult.add(DOWN2);
        expResult.add(LEFT);

        List<Move> result = instance.getMoves();
        assertEquals(expResult, result);
    }

    // ----- apply() -----
    @Test(expected = NullPointerException.class)
    public void testApplyWhenMoveIsNull() {
        Position pos = new Position(0, 1);

        Game instance = new Game();
        instance.initialize();
        instance.select(pos.getRow(), pos.getColumn());
        Move move = null;
        instance.apply(move);

        Square[][] expResult = defaultBoard;
        assertArrayEquals(instance.getBoard(), expResult);
    }

    @Test(expected = NullPointerException.class)
    public void testApplyWhenPositionIsNull() {
        Position pos = null;
        int index = 0;

        Game instance = new Game();
        instance.initialize();
        instance.select(pos.getRow(), pos.getColumn());
        Move move = instance.getMoves().get(index);
        instance.apply(move);

        Square[][] expResult = defaultBoard;

        assertArrayEquals(instance.getBoard(), expResult);
    }

    @Test
    public void testApplyWhenEndIsFree() {
        Position pos = new Position(1, 2);
        int index = 0;

        Game instance = new Game();
        instance.initialize();
        instance.select(pos.getRow(), pos.getColumn());
        Move move = instance.getMoves().get(index);
        instance.apply(move);

        Square[][] expResult = defaultBoard;
        expResult[1][2] = new Square(SquareType.LAND);
        expResult[0][2].put(new Miner(PlayerColor.RED));

        assertArrayEquals(instance.getBoard(), expResult);
    }

    @Test
    public void testApplyWhenCurrentIsStronger() {
        Position pos = new Position(3, 2);
        int index = 0;

        Game instance = new Game();
        instance.initialize();
        instance.select(pos.getRow(), pos.getColumn());
        Move move = instance.getMoves().get(index);
        instance.apply(move);

        Square[][] expResult = defaultBoard;
        expResult[3][2] = new Square(SquareType.LAND);
        expResult[4][2] = new Square(SquareType.LAND);
        expResult[4][2].put(new General(PlayerColor.RED));

        assertArrayEquals(instance.getBoard(), expResult);
    }

    @Test
    public void testApplyWhenSameRanks() {
        Position pos = new Position(4, 3);
        int index = 1;

        Game instance = new Game();
        instance.initialize();

        instance.select(5, 4);
        instance.apply(instance.getMoves().get(0));

        instance.select(pos.getRow(), pos.getColumn());
        Move move = instance.getMoves().get(index);
        instance.apply(move);

        Square[][] expResult = defaultBoard;
        expResult[5][3] = new Square(SquareType.LAND);
        expResult[4][3] = new Square(SquareType.LAND);
        expResult[5][4] = new Square(SquareType.LAND);

        assertArrayEquals(instance.getBoard(), expResult);
    }

    @Test
    public void testApplyWhenOpponentIsStronger() {
        Position pos = new Position(3, 2);
        int index = 1;

        Game instance = new Game();
        instance.initialize();
        instance.select(pos.getRow(), pos.getColumn());
        Move move = instance.getMoves().get(index);
        instance.apply(move);

        Square[][] expResult = defaultBoard;
        expResult[3][2] = new Square(SquareType.LAND);
        assertArrayEquals(instance.getBoard(), expResult);
    }

    @Test
    public void testApplyWhenMinerGoesOnBomb() {
        Position pos = new Position(2, 0);
        int index = 0;
        Game instance = new Game();
        instance.initialize();

        instance.select(1, 2);
        instance.apply(instance.getMoves().get(0));

        instance.select(pos.getRow(), pos.getColumn());
        Move move = instance.getMoves().get(index);
        instance.apply(move);

        Square[][] expResult = defaultBoard;
        expResult[1][2] = new Square(SquareType.LAND);
        expResult[0][2].put(new Miner(PlayerColor.RED));

        expResult[2][0] = new Square(SquareType.LAND);
        expResult[1][0] = new Square(SquareType.LAND);
        expResult[1][0].put(new Miner(PlayerColor.BLUE));

        assertArrayEquals(instance.getBoard(), expResult);
    }

    @Test
    public void testApplyWhenMarshalGoesOnSpy() {
        Position pos = new Position(4, 4);
        int index = 1;
        Game instance = new Game();
        instance.initialize();

        instance.select(pos.getRow(), pos.getColumn());
        Move move = instance.getMoves().get(index);
        instance.apply(move);

        Square[][] expResult = defaultBoard;

        expResult[4][4] = new Square(SquareType.LAND);
        expResult[4][3] = new Square(SquareType.LAND);
        expResult[4][3].put(new Marshal(PlayerColor.RED));

        assertArrayEquals(instance.getBoard(), expResult);
    }

    @Test
    public void testApplyWhenSpyGoesOnMarshal() {
        Position pos = new Position(5, 4);
        int index = 0;
        Game instance = new Game();
        instance.initialize();

        instance.select(pos.getRow(), pos.getColumn());
        Move move = instance.getMoves().get(index);
        instance.apply(move);

        Square[][] expResult = defaultBoard;

        expResult[5][4] = new Square(SquareType.LAND);
        expResult[5][3] = new Square(SquareType.LAND);
        expResult[5][3].put(new Spy(PlayerColor.RED));

        assertArrayEquals(instance.getBoard(), expResult);
    }

    // ----- swapPlayers() -----
    @Test
    public void testSwapPlayers() {
        Game instance = new Game();
        instance.initialize();
        Player oldCurrent = instance.getCurrent();
        instance.swapPlayers();
        Player newCurrent = instance.getCurrent();
        assertEquals(oldCurrent.getColor(), PlayerColor.RED);
        assertEquals(newCurrent.getColor(), PlayerColor.BLUE);
    }

    @Test
    public void testSwapPlayersWhenGameIsOver() {
        Game instance = new Game();
        instance.initialize();

        // Victoire de bleu, drapeau rouge éliminé
        instance.select(3, 2);
        instance.apply(instance.getMoves().get(0));

        Player oldCurrent = instance.getCurrent();
        instance.swapPlayers();
        Player newCurrent = instance.getCurrent();

        // Le current n'a pas changé
        assertEquals(oldCurrent.getColor(), newCurrent.getColor());
    }

    // ----- hasMoves() -----
    @Test
    public void testHasMovesWhenHasMoves() {
        Player player = new Player(PlayerColor.RED);
        Game instance = new Game();
        instance.initialize();
        boolean result = instance.hasMoves(player);
        assertTrue(result);
    }

    @Test
    public void testHasMovesWhenHasNoMoves() {
        Player player = new Player(PlayerColor.RED);
        Game instance = new Game();
        instance.initialize();

        instance.select(3, 2);
        instance.apply(instance.getMoves().get(1));

        instance.select(4, 3);
        instance.apply(instance.getMoves().get(1));

        instance.select(5, 4);
        instance.apply(instance.getMoves().get(1));

        instance.select(1, 1);
        instance.apply(instance.getMoves().get(2));

        instance.select(5, 0);
        instance.apply(instance.getMoves().get(1));

        instance.select(4, 4);
        instance.apply(instance.getMoves().get(2));

        instance.select(5, 3);
        instance.apply(instance.getMoves().get(0));

        instance.select(2, 0);
        instance.apply(instance.getMoves().get(1));

        // Le joueur rouge n'a plus que sa bombe et son drapeau
        boolean result = instance.hasMoves(player);
        assertFalse(result);
    }

    // ----- getWinners() -----
    @Test(expected = IllegalStateException.class)
    public void testGetWinnersWhenIsNotOver() {
        Game instance = new Game();
        instance.initialize();
        List<Player> expResult = null;
        List<Player> result = instance.getWinners();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWinnersWhenRedWins() {
        Game instance = new Game();
        instance.initialize();

        instance.select(3, 2);
        instance.apply(instance.getMoves().get(0));

        List<Player> expResult = new ArrayList<Player>();
        expResult.add(instance.getCurrent());
        List<Player> result = instance.getWinners();
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalStateException.class)
    public void testGetWinnersWhenGameNotOver() {
        Game instance = new Game();
        instance.initialize();
        List<Player> expResult = null;
        List<Player> result = instance.getWinners();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWinnersWhenRedWonAndCurrentIsRed() {
        Game instance = new Game();
        instance.initialize();

        instance.select(3, 2);
        instance.apply(instance.getMoves().get(0));

        List<Player> expResult = new ArrayList<Player>();
        expResult.add(new Player(PlayerColor.RED));
        List<Player> result = instance.getWinners();
        assertEquals(expResult.get(0).getColor(), result.get(0).getColor());
    }

    @Test
    public void testGetWinnersWhenBlueWonAndCurrentIsBlue() {
        Game instance = new Game();
        instance.initialize();

        instance.select(3, 2);
        instance.apply(instance.getMoves().get(1));

        instance.select(1, 1);
        instance.apply(instance.getMoves().get(0));

        List<Player> expResult = new ArrayList<Player>();
        expResult.add(new Player(PlayerColor.BLUE));
        List<Player> result = instance.getWinners();
        assertEquals(expResult.get(0).getColor(), result.get(0).getColor());
    }

    @Test
    public void testGetWinnersWhenExAequo() {
        Game instance = new Game();
        instance.initialize();

        instance.select(1, 2);
        instance.apply(instance.getMoves().get(1));

        instance.select(1, 1);
        instance.apply(instance.getMoves().get(1));

        instance.select(3, 2);
        instance.apply(instance.getMoves().get(1));

        instance.select(5, 3);
        instance.apply(instance.getMoves().get(1));

        instance.select(5, 0);
        instance.apply(instance.getMoves().get(1));

        instance.select(2, 0);
        instance.apply(instance.getMoves().get(1));

        instance.select(4, 4);
        instance.apply(instance.getMoves().get(2));

        instance.select(3, 0);
        instance.apply(instance.getMoves().get(1));

        instance.select(4, 3);
        instance.apply(instance.getMoves().get(1));

        instance.select(4, 0);
        instance.apply(instance.getMoves().get(1));

        instance.select(5, 3);
        instance.apply(instance.getMoves().get(1));

        instance.select(5, 0);
        instance.apply(instance.getMoves().get(1));

        instance.select(5, 2);
        instance.apply(instance.getMoves().get(1));

        instance.select(0, 4);
        instance.apply(instance.getMoves().get(3));

        instance.select(5, 1);
        instance.apply(instance.getMoves().get(2));

        instance.select(0, 2);
        instance.apply(instance.getMoves().get(0));

        instance.select(5, 2);
        instance.apply(instance.getMoves().get(2));

        instance.select(1, 2);
        instance.apply(instance.getMoves().get(2));

        instance.select(5, 3);
        instance.apply(instance.getMoves().get(2));

        List<Player> expResult = new ArrayList<Player>();
        expResult.add(new Player(PlayerColor.RED));
        expResult.add(new Player(PlayerColor.BLUE));
        List<Player> result = instance.getWinners();
        assertEquals(expResult.get(0).getColor(), result.get(0).getColor());
        assertEquals(expResult.get(1).getColor(), result.get(1).getColor());
    }

}
