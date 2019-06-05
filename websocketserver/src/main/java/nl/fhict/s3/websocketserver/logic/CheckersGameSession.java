package nl.fhict.s3.websocketserver.logic;

import Interface.Checkers;
import Model.GameBoard.GameBoard;
import Model.GameBoard.Move;
import Model.GameBoard.Type.PieceColor;
import Model.Player;

import java.util.List;


public class CheckersGameSession implements Checkers {

    private List<Player> Players;
    private GameBoard gameBoard;
    private Player Currentplayer;


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
    // to start game
    private boolean canStartGame() {
        if(Players.size() == 2){
            return true;
        }
        return false;
    }
    //check if player can surrender
    private boolean canSurrender() {
        return false;
    }

}
