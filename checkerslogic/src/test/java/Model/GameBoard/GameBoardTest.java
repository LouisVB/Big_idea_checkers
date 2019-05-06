package Model.GameBoard;

import Model.GameBoard.Type.PieceColor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

class GameBoardTest {
    private static GameBoard testboard1;

    private List<Move> moves;

    private Piece red1;
    private Piece white1;
    private Piece white2;

    @BeforeAll
    static void setup(){
         testboard1 = new GameBoard();
    }

    @Test
    void createBoard() {
        //preperation
        GameBoard board = new GameBoard();
        board.createBoard();

        //Testing
        Assertions.assertEquals(24, board.getPieces().size(), "Total size of pieces on created board");
    }

    @Test
    void getPieceMoves() {
        red1 = new Piece(PieceColor.Red, new Location(2,0));
        testboard1.getGameBoard()[2][0].setPiece(red1);

        List<Move> moves = testboard1.getPieceMoves(red1);

        Assertions.assertEquals(2, moves.size(), "total possible moves of certain piece");
    }

    @Test
    void getAllKillMoves() {
        red1 = new Piece(PieceColor.Red, new Location(2,0));
        white1 = new Piece(PieceColor.White, new Location(1,1));
        white2 = new Piece(PieceColor.White, new Location(3,1));
        testboard1.getGameBoard()[2][0].setPiece(red1);
        testboard1.getGameBoard()[1][1].setPiece(white1);
        testboard1.getGameBoard()[3][1].setPiece(white2);

        List<Move> moves = testboard1.getAllKillMoves(PieceColor.Red);

        Assertions.assertEquals(2, moves.size(), "total possible moves of certain piece");
    }

    @Test
    void isKillMovePossibleTrueTest() {
        red1 = new Piece(PieceColor.Red, new Location(2,0));
        white1 = new Piece(PieceColor.White, new Location(1,1));

        testboard1.getGameBoard()[2][0].setPiece(red1);
        testboard1.getGameBoard()[1][1].setPiece(white1);

        boolean isKillMove = testboard1.isKillMovePossible(PieceColor.Red);

        Assertions.assertEquals(true, isKillMove, "total possible moves of certain piece");
    }

    @Test
    void doMove() {
        //insert Move
        //check for new piece location

    }

    @Test
    void removePiece() {


    }
}