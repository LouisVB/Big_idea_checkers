package nl.fhict.s3.websocketclient;

import Controller.CheckersController;
import Logic.Game;
import Model.Player;
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

    public Client() {

    }

    public void connect() {
        if (!websocketClient.isRunning) {
            websocketClient.addObserver(this);
            websocketClient.connect();
        }
    }

    public void buildUi() {

        Client.getInstance().game = new Game();

        setChanged();
        notifyObservers(game);
    }


    @Override
    public void update(Observable o, Object arg) {

    }
}
