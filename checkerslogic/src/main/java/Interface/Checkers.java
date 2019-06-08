package Interface;

import Model.GameBoard.Move;
import Model.GameBoard.Type.PieceColor;
import Model.Player;

import java.util.List;

public interface Checkers {

    void newPlayer(Player player);

    void startGame();

    List<Move> getMovePiece();

    void useMove( Move move );

    boolean isKillMovePossible(PieceColor color);

    Player gameEnded();

}