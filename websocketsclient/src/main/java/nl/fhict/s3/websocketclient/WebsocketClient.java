package nl.fhict.s3.websocketclient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Observable;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import nl.fhict.s3.websocketclient.SocketMessage.RequestPackager;
import nl.fhict.s3.websocketclient.SocketMessage.SocketMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;


@ClientEndpoint
public class WebsocketClient extends Observable {

    private static final Logger log = LoggerFactory.getLogger(WebsocketClient.class);
    private static WebsocketClient instance = null;
    private static final String URI = "ws://localhost:8095/checkers/";
    private Session session;
    public boolean isRunning = false;
    private String message;
    private RequestPackager requestPackager;



    public static WebsocketClient getInstance() {
        if (instance == null) {
            instance = new WebsocketClient();
            log.info("GameClientEndPoint singleton instantiated");
        }
        return instance;
    }

    @OnOpen
    public void onWebSocketConnect(Session session) {
        log.info("Client open session {}", session.getRequestURI());
        this.session = session;
    }

    @OnMessage
    public void onWebSocketText(String message) {
        log.info("Client message received {}", message);
        processMessage(message);
    }

    @OnError
    public void onWebSocketError(Session session, Throwable cause) {
        log.info("Client connection error {}", cause.toString());
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason) {
        log.info("Client close session {} for reason {} ", session.getRequestURI(), reason);
        session = null;
    }

    public void sendMessageToServer(String json) {
        // Use asynchronous communication
        session.getAsyncRemote().sendText(json);
    }


    public void connect() {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(URI));
            log.info("Connected to server at {}", URI);

        } catch (IOException | URISyntaxException | DeploymentException ex) {
            log.error("Error in startClient: {}", ex.getMessage());
        }
    }

    private void stopClient() {
        try {
            session.close();
            log.info("Session closed");

        } catch (IOException ex) {
            log.error("Error in stopClient {}", ex.getMessage());
        }
    }

    private void processMessage(String message) {

        SocketMessage responseMessage;
        log.info("Processing message: {}", message);
        try {
          //  responseMessage = gson.fromJson(message, SocketMessage.class);
            this.setChanged();
            this.notifyObservers(message);
        } catch (JsonSyntaxException ex) {
            log.error("Can't process message: {}", ex.getMessage());
        }
    }

}
