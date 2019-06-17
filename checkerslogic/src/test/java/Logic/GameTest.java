package Logic;

import Model.GameBoard.Type.PieceColor;
import Model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;
    private Player louis;
    private Player bas;

    @BeforeEach
    void setup() {
        game = new Game();
        louis = new Player("Louis","lol");
        bas = new Player("player", "bas");
        louis.setMyPieces(PieceColor.Red);
        bas.setMyPieces(PieceColor.White);
    }


    @Test
    void startGameWithNoRegisteredPlayersTest() {
        game.startGame();

        assertFalse(game.isGameStarted());
    }

    @Test
    void startGameWith2RegisteredPlayersTest() {
        game.newPlayer(louis);
        game.newPlayer(bas);
        game.startGame();

        assertTrue(game.isGameStarted());
    }

    @Test
    void startGameRedPieceHasCurrentAtTurnTest() {
        game.newPlayer(louis);
        game.newPlayer(bas);
        game.startGame();

        assertEquals(game.getCurrentPlayerAtTurn(), louis);
    }


    @Test
    void useMoveIsMoveListClearedTest() {
        game.newPlayer(louis);
        game.newPlayer(bas);
        game.startGame();


    }

    @Test
    void isKillMovePossible() {
    }

    @Test
    void changeTurn() {

    }
}