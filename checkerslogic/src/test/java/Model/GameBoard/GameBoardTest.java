package Model.GameBoard;

import Model.GameBoard.Type.MoveType;
import Model.GameBoard.Type.PieceColor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

class GameBoardTest {
    private GameBoard testboard1;

    private List<Move> moves;

    private Piece red1;
    private Piece red2;
    private Piece white1;
    private Piece white2;

    @BeforeEach
    void setup() {
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
    void doMoveNormalMoveCheckNewXLocationTest() {
        //create normal move
        Move normalMove = new Move(red2, new Location(5, 1), MoveType.NORMAL);
        //execute move
        testboard1.doMove(normalMove);
        //check new location
        Assertions.assertEquals(5, red2.getLocation().getX(), "new x location red piece");
    }


    @Test
    void doMoveNormalMoveCheckNewYLocationTest() {
        //create normal move
        Move normalMove = new Move(red2, new Location(5, 1), MoveType.NORMAL);
        //execute move
        testboard1.doMove(normalMove);
        //check new location
        Assertions.assertEquals(1, red2.getLocation().getY(), "new y location red piece");
    }


    @Test
    void doMoveNormalMovePieceIsRemovedFromOldLocationTest() {
        //create normal move
        Move normalMove = new Move(red2, new Location(5, 1), MoveType.NORMAL);
        //execute move
        testboard1.doMove(normalMove);
        //check old location
        Assertions.assertFalse(testboard1.getGameBoard()[6][0].HasPiece());
    }

    @Test
    void doMoveNormalMovePieceIsOnNewLocationTest() {
        //create normal move
        Move normalMove = new Move(red2, new Location(5, 1), MoveType.NORMAL);
        //execute move
        testboard1.doMove(normalMove);
        //check new location
        Assertions.assertTrue(testboard1.getGameBoard()[5][1].HasPiece());
    }

    @Test
    void doMoveKillMovePieceIsRemovedTest() {
        //create kill move
        Move killMove = new Move(red1, white1, new Location(0, 2), MoveType.KILL);
        //execute move
        testboard1.doMove(killMove);
        //check new location
        Assertions.assertFalse(testboard1.getGameBoard()[1][1].HasPiece());
    }

    @Test
    void doMoveKillMoveNewLocationXTest() {
        //create kill move
        Move killMove = new Move(red1, white1, new Location(0, 2), MoveType.KILL);
        //execute move
        testboard1.doMove(killMove);
        Assertions.assertEquals(0, red1.getLocation().getX(), "new x location red piece");
    }

    @Test
    void doMoveKillMoveNewLocationYTest() {
        //create kill move
        Move killMove = new Move(red1, white1, new Location(0, 2), MoveType.KILL);
        //execute move
        testboard1.doMove(killMove);
        Assertions.assertEquals(2, red1.getLocation().getY(), "new y location red piece");
    }

    @Test
    void doMoveKillMoveMovedPieceIsLocationPiece() {
        //create kill move
        Move killMove = new Move(red1, white1, new Location(0, 2), MoveType.KILL);
        //execute move
        testboard1.doMove(killMove);
        Assertions.assertEquals(red1, testboard1.getGameBoard()[0][2].getPiece(), "new y location red piece");
    }


}