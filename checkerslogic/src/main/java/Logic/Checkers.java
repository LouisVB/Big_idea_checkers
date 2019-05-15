package Logic;

import Model.GameBoard.Move;
import Model.GameBoard.Type.PieceColor;

import java.util.List;

public class Checkers implements Interface.Checkers {

    @Override
    public void registerPlayer(String name, String password) {

    }

    @Override
    public void startGame(int playerNumber) {

    }

    @Override
    public PieceColor gameEnded() {
        return null;
    }


    @Override
    public void surrender(int playerNumber) {

    }

    @Override
    public void rematch() {

    }

    @Override
    public List<Move> getMovePiece() {
        return null;
    }


    @Override
    public void useMove() {

    }

    @Override
    public boolean isKillMovePossible(PieceColor color) {
        return false;
    }
}
