package nl.fhict.s3.websocketserver.GameSession;

import Logic.Game;
import nl.fhict.s3.websocketserver.Interface.Command;
import nl.fhict.s3.websocketserver.SocketMessage.Factory;
import nl.fhict.s3.websocketserver.SocketMessage.RequestPackager;
import nl.fhict.s3.websocketserver.SocketMessage.SocketMessage;
import nl.fhict.s3.websocketserver.endpoint.GameEndPoint;

import javax.websocket.Session;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class GameSession implements Observer {

    private Game game;
    private Session specificClient;
    private GameEndPoint websocket;
    private RequestPackager requestPackager;


    private static GameSession instance;

    public static GameSession getInstance(Observable observable) {
        if (instance == null) instance = new GameSession();
        observable.addObserver(instance);

        return instance;
    }

    private GameSession() {
        game = new Game();
        requestPackager = new RequestPackager();
    }

    public static GameSession getInstance() {
        if (instance == null) instance = new GameSession();

        return instance;
    }

    public Game getGame() {
        return game;
    }

    public String startGame() {

        game.startGame();
        return requestPackager.Connect(game);
    }

    public void broadcastToClients(String message) {
        websocket.sendBroadcast(message);
    }

    @Override
    public void update(Observable o, Object arg) {
        websocket = (GameEndPoint) o;
        SocketMessage receiver = websocket.getlatestMessage();

        Factory factory = new Factory();
        Command cmd = factory.getCommand(receiver.getOperation().toString());
        if (cmd != null) {
            cmd.execute(receiver);
        } else {
            System.out.println("The command " + receiver.getOperation().toString() + " is not found.");

        }
    }

}
