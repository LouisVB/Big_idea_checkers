package Model.GameBoard;

import Model.GameBoard.Type.MoveType;
import Model.GameBoard.Type.PieceColor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

class GameBoardTest {
    private static GameBoard testboard1;

    private static List<Move> moves;

    private static Piece red1;
    private static Piece red2;
    private static Piece white1;
    private static Piece white2;

    @BeforeAll
    static void setup() {
        //Empty GameBoard
        testboard1 = new GameBoard();
        testboard1.createBoardWithoutPieces();
        //Pieces
        red1 = new Piece(PieceColor.Red, new Location(2, 0));
        red2 = new Piece(PieceColor.Red, new Location(6, 0));
        white1 = new Piece(PieceColor.White, new Location(1, 1));
        white2 = new Piece(PieceColor.White, new Location(3, 1));
        //Add pieces in LIST
        testboard1.getPieces().add(red1);
        testboard1.getPieces().add(red2);
        testboard1.getPieces().add(white1);
        testboard1.getPieces().add(white2);
        //Place pieces on tile
        testboard1.getGameBoard()[2][0].setPiece(red1);
        testboard1.getGameBoard()[6][0].setPiece(red2);
        testboard1.getGameBoard()[1][1].setPiece(white1);
        testboard1.getGameBoard()[3][1].setPiece(white2);
        //Move list
        moves = new ArrayList<>();

    }

    @Test
    void getAllKillMovesTest() {
        moves = testboard1.getAllKillMoves(PieceColor.Red);
        Assertions.assertEquals(2, moves.size(), "total possible moves of certain piece");
        //check move type
    }

    @Test
    void createBoardTest() {
        //Preparation
        GameBoard board = new GameBoard();
        board.createBoard();
        //Testing
        Assertions.assertEquals(24, board.getPieces().size(), "Total size of pieces on created board");
    }

    @Test
    void getPieceMovesTest() {
        moves = testboard1.getPieceMoves(red2);
        //check list size
        Assertions.assertEquals(2, moves.size(), "total possible moves of certain piece");
        //check move type
    }

    @Test
    void doMoveNormalMoveTest() {
        //create normal move
        Move normalMove = new Move(red2, new Location(5, 1), MoveType.NORMAL);
        //execute move
        testboard1.doMove(normalMove);
        //check new location
        Assertions.assertEquals(5, red2.getLocation().getX(), "new x location red piece");
        Assertions.assertEquals(1, red2.getLocation().getY(), "new y location red piece");
        Assertions.assertFalse(testboard1.getGameBoard()[6][0].HasPiece());
        Assertions.assertTrue(testboard1.getGameBoard()[5][1].HasPiece());

    }

    @Test
    void doMoveKillMoveTest() {
        //create kill move
        Move killMove = new Move(red1, white1, new Location(0, 2), MoveType.KILL);
        //execute move
        testboard1.doMove(killMove);
        //check new location
        Assertions.assertEquals(0, red1.getLocation().getX(), "new x location red piece");
        Assertions.assertEquals(2, red1.getLocation().getY(), "new y location red piece");
        Assertions.assertEquals(red1, testboard1.getGameBoard()[0][2].getPiece(), "new y location red piece");
        //check if white piece is removed
        Assertions.assertFalse(testboard1.getGameBoard()[1][1].HasPiece());
    }

}