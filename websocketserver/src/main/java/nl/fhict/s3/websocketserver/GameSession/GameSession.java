package nl.fhict.s3.websocketserver.GameSession;

import Logic.Game;
import nl.fhict.s3.websocketserver.Interface.Command;
import nl.fhict.s3.websocketserver.SocketMessage.Factory;
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


    private static GameSession instance;

    public static GameSession getInstance(Observable observable) {
        if (instance == null) instance = new GameSession();
        observable.addObserver(instance);

        return instance;
    }

    public static GameSession getInstance() {
        if (instance == null) instance = new GameSession();

        return instance;
    }

    @Override
    public void update(Observable o, Object arg) {
        websocket = (GameEndPoint) o;
        String message = websocket.getlatestMessage();
        SocketMessage receiver = new SocketMessage();
        if (arg.getClass() == SocketMessage.class) {
            SocketMessage response = (SocketMessage) arg;
            Factory factory = new Factory();
            Command cmd = factory.getCommand(response.getOperation().toString());
            if (cmd != null) {
                cmd.execute(response);
            } else {
                System.out.println("The command " + response.getOperation().toString() + " is not found.");
            }
        }
    }
}
