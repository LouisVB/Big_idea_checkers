package Logic;

import Interface.Checkers;
import Model.GameBoard.GameBoard;
import Model.GameBoard.Move;
import Model.GameBoard.Piece;
import Model.GameBoard.Type.MoveType;
import Model.GameBoard.Type.PieceColor;
import Model.Player;

import java.util.ArrayList;
import java.util.List;

public class Game implements Checkers {

    private List<Player> Players = new ArrayList<>();
    private GameBoard gameBoard;
    private Player CurrentPlayerAtTurn;
    private boolean isGameStarted;
    private List<Move> moves = new ArrayList<>();


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

    public List<Move> getMoves() {
        return moves;
    }

    public void newPlayer(Player player){
       if(Players.size() < 2) {
           Players. add(player);
       }
    }

    public void setPlayers(List<Player> players) {
        Players = players;
    }

    public void setCurrentPlayerAtTurn(Player currentPlayerAtTurn) {
        CurrentPlayerAtTurn = currentPlayerAtTurn;
    }

    @Override
    public void startGame() {
        if(getPlayers().size() ==  2 ) {
            gameBoard = new GameBoard();
            gameBoard.createBoard();
            isGameStarted = true;
            setCurrentPlayerAtTurn(hasredpiece());
        }
    }

    @Override
    public List<Move> getMovePiece(Piece piece) {
        return gameBoard.getPieceMoves(piece);
    }

    @Override
    public void useMove(Move move) {
        if(CurrentPlayerAtTurn.getMyPieces() == move.getMyPiece().getColor()){
            gameBoard.doMove(move);
            moves.clear();
            changeTurn();
            moves = gameBoard.getAllKillMoves(getCurrentPlayerAtTurn().getMyPieces());
        }
    }


    @Override
    public boolean isKillMovePossible(PieceColor color) {
       for(Move move : moves) {
           if(move.getType().equals(MoveType.KILL)){
               return true;
           }
       }
       return false;
    }

    @Override
    public Player gameEnded() {
        return null;
    }

    public void changeTurn() {
        for (Player player : Players) {
            if(player != CurrentPlayerAtTurn){
                CurrentPlayerAtTurn = player;
            }
        }
    }

    private Player hasredpiece(){
        Player start;
        start = new Player();
        for(Player player : Players) {
            if (player.getMyPieces() != PieceColor.Red) {
                continue;
            }
            start = player;
        }
        return start;
    }




}
