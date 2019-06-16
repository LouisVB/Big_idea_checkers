package nl.fhict.s3.websocketserver.GameSession;

import Logic.Game;
import nl.fhict.s3.websocketserver.Interface.Command;
import nl.fhict.s3.websocketserver.SocketMessage.Factory;
import nl.fhict.s3.websocketserver.SocketMessage.Operation;
import nl.fhict.s3.websocketserver.SocketMessage.RequestPackager;
import nl.fhict.s3.websocketserver.SocketMessage.SocketMessage;
import nl.fhict.s3.websocketserver.endpoint.GameEndPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Observable;
import java.util.Observer;

public class GameSession implements Observer {
    private static final Logger log = LoggerFactory.getLogger(GameSession.class);
    private Game game;
    private GameEndPoint websocket = GameEndPoint.getInstance();
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
        websocket.addObserver(this);
    }

    public static GameSession getInstance() {
        if (instance == null) instance = new GameSession();

        return instance;
    }

    public Game getGame() {
        return game;
    }

//    public void broadcastToClients(String message) {
//        websocket.sendBroadcast(message);
//    }

    @Override
    public void update(Observable o, Object arg) {

        SocketMessage receiver = (SocketMessage) arg;

        // Process message based on operation
        Factory factory = new Factory();
        Command cmd = factory.getCommand(receiver.getOperation().toString());
        if (cmd != null) {
            cmd.execute(receiver);
        } else {
            log.info("The command is not found.");
        }
    }

}
