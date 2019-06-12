package nl.fhict.s3.websocketclient;

import Model.GameBoard.Move;
import com.google.gson.Gson;
import nl.fhict.s3.websocketclient.Controller.CheckersController;
import Logic.Game;
import Model.Player;
import nl.fhict.s3.websocketclient.Interface.Command;
import nl.fhict.s3.websocketclient.SocketMessage.Factory;
import nl.fhict.s3.websocketclient.SocketMessage.RequestPackager;
import nl.fhict.s3.websocketclient.SocketMessage.SocketMessage;


import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Client extends Observable implements Observer {

    private static Client instance;

    public static Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }
        return instance;
    }

    //
    private RequestPackager requestPackager;
    private WebsocketClient websocketClient = WebsocketClient.getInstance();
    private CheckersController CheckersUIController;
    private Game game;

    public Player getClientplayer() {
        return clientplayer;
    }

    public void setClientplayer(Player clientplayer) {
        this.clientplayer = clientplayer;
    }

    private Player clientplayer;

    public CheckersController getViewController() {
        return CheckersUIController;
    }

    public void setViewController(CheckersController viewController) {
        this.CheckersUIController = viewController;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Client() {
        requestPackager = new RequestPackager();
        game = new Game();
    }

    public void buildUi() {
        setChanged();
        notifyObservers(game);
    }

    public void setPlayers(List<Player> players) {
        game.setPlayers(players);
    }

    public void connect() {
        if (!websocketClient.isRunning) {
            websocketClient.addObserver(this);
            websocketClient.connect();
        }
    }

    public void SubmitMove(Move move) {
        websocketClient.sendMessageToServer(requestPackager.UseMove(move));
    }


    @Override
    public void update(Observable o, Object arg) {
            Gson gson = new Gson();
            String message =(String) arg;
             SocketMessage response = gson.fromJson( message , SocketMessage.class);
            Factory factory = new Factory();
            Command cmd = factory.getCommand(response.getOperation().toString());
            if (cmd != null) {
                cmd.execute(response);
            } else {
                System.out.println("The command " + response.getOperation().toString() + " is not found.");
            }

    }

}
