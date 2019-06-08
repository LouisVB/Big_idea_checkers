package Logic;

import Interface.Checkers;
import Model.GameBoard.GameBoard;
import Model.Player;

import java.util.ArrayList;
import java.util.List;

public class Game implements Checkers {

    private List<Player> Players = new ArrayList<>();
    private GameBoard gameBoard;
    private Player CurrentPlayerAtTurn;
    private boolean isGameStarted;


    public Game() {

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

    public void newPlayer(Player player){
       if(Players.size() < 2) {
           Players. add(player);
       }
    }

    public void ChangeTurn () {

    }



}
