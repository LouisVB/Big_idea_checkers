package Logic;

import Interface.Checkers;
import Model.GameBoard.GameBoard;
import Model.GameBoard.Move;
import Model.GameBoard.Piece;
import Model.GameBoard.Type.PieceColor;
import Model.Player;

import java.util.ArrayList;
import java.util.List;

public class Game implements Checkers {

    private List<Player> Players = new ArrayList<>();
    private GameBoard gameBoard;
    private Player CurrentPlayerAtTurn;
    private boolean isGameStarted;
    private List<Move> killMove = new ArrayList<>();


    public Game() {
        gameBoard = new GameBoard();
        gameBoard.createBoardWithoutPieces();
        CurrentPlayerAtTurn = null;
        isGameStarted = false;

    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    public List<Player> getPlayers() {
        return Players;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public Player getCurrentPlayerAtTurn() {
        return CurrentPlayerAtTurn;
    }

    public List<Move> getKillMove() {
        return killMove;
    }

    public void newPlayer(Player player){
       if(Players.size() < 2) {
           Players. add(player);
       }
    }

    @Override
    public void startGame() {
        gameBoard = new GameBoard();
        gameBoard.createBoard();
        isGameStarted = true;

    }

    @Override
    public List<Move> getMovePiece(Piece piece) {
        return gameBoard.getPieceMoves(piece);
    }

    @Override
    public void useMove(Move move) {
        gameBoard.doMove(move);
    }


    @Override
    public boolean isKillMovePossible(PieceColor color) {
        if(gameBoard.getAllKillMoves(color).isEmpty()){
            return false;
        }
        killMove = gameBoard.getAllKillMoves(color);
        return true;
    }

    @Override
    public Player gameEnded() {
        return null;
    }

    public void ChangeTurn () {
        for (Player player : Players) {
            if(player != CurrentPlayerAtTurn){
                CurrentPlayerAtTurn = player;
            }
        }
    }





}
