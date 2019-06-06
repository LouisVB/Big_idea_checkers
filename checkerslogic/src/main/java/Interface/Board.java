package Interface;

import Model.GameBoard.Move;
import Model.GameBoard.Piece;
import Model.GameBoard.Type.PieceColor;

import java.util.List;

public interface Board {
    /**
     *
     */
    void createBoard();

    /**
     *
     */
    List<Move> getPieceMoves(Piece piece);

    /**
     *
     */
    List<Move> getAllKillMoves(PieceColor color);

    /**
     *
     */
    void doMove(Move nextMove);

}
