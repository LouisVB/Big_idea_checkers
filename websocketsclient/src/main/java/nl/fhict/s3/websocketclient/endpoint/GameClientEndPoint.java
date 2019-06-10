//package nl.fhict.s3.websocketclient.endpoint;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonSyntaxException;
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.Observable;
//import javax.websocket.ClientEndpoint;
//import javax.websocket.CloseReason;
//import javax.websocket.ContainerProvider;
//import javax.websocket.DeploymentException;
//import javax.websocket.OnClose;
//import javax.websocket.OnError;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.WebSocketContainer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@ClientEndpoint
//public class GameClientEndPoint extends Observable {
//
//    private static final Logger log = LoggerFactory.getLogger(GameClientEndPoint.class);
//    private static GameClientEndPoint instance = null;
//    private static final String URI = "ws://localhost:8095/checkers/"; // TODO Config file
//    private Session session;
//    private Gson gson;
//    public boolean isRunning = false;
//    private String message;
//
//    private GameClientEndPoint() {
//        gson = new Gson();
//    }
//
//    public static GameClientEndPoint getInstance() {
//        if (instance == null) {
//            instance = new GameClientEndPoint();
//            log.info("GameClientEndPoint singleton instantiated");
//        }
//        return instance;
//    }
//
//    public void start() {
//        if (!isRunning) {
//            startClient();
//            isRunning = true;
//        }
//    }
//
//    public void stop() {
//        if (isRunning) {
//            stopClient();
//            isRunning = false;
//        }
//    }
//
//    @OnOpen
//    public void onWebSocketConnect(Session session) {
//        log.info("Client open session {}", session.getRequestURI());
//        this.session = session;
//    }
//
//    @OnMessage
//    public void onWebSocketText(String message, Session session) {
//        this.message = message;
//        log.info("Client message received {}", message);
//        processMessage(message);
//    }
//
//    @OnError
//    public void onWebSocketError(Session session, Throwable cause) {
//        log.info("Client connection error {}", cause.toString());
//    }
//
//    @OnClose
//    public void onWebSocketClose(CloseReason reason) {
//        log.info("Client close session {} for reason {} ", session.getRequestURI(), reason);
//        session = null;
//    }
//
//
//
//    private void stopClient() {
//        try {
//            session.close();
//            log.info("Session closed");
//
//        } catch (IOException ex) {
//            log.error("Error in stopClient {}", ex.getMessage());
//        }
//    }
//
//    private void processMessage(String jsonMessage) {
////        Greeting greeting;
////        log.info("Processing message: {}", jsonMessage);
////
////        try {
////            greeting = gson.fromJson(jsonMessage, Greeting.class);
////            log.info("Message processed: {}", greeting);
////        } catch (JsonSyntaxException ex) {
////            log.error("Can't process message: {}", ex.getMessage());
////            return;
////        }
////
////        String content = greeting.getName();
////        if (content == null || "".equals(content)) {
////            log.error("Message is empty");
////            return;
////        }
////
////        Greeting commMessage = new Greeting();
////        commMessage.setName(content);
////
////        this.setChanged();
////        this.notifyObservers(commMessage);
//    }
//}