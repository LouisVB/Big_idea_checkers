package Interface;

import Model.GameBoard.Move;
import Model.GameBoard.Type.PieceColor;

import java.util.List;

public interface Checkers {

    void registerPlayer(String name, String password);

    void startGame(int playerNumber);

    PieceColor gameEnded();

    void surrender(int playerNumber);

    void rematch();

    List<Move> getMovePiece();

    void useMove();

    boolean isKillMovePossible(PieceColor color);

}