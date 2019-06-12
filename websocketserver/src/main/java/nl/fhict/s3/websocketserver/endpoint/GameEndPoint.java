package nl.fhict.s3.websocketserver.endpoint;

import Model.Player;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.*;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import nl.fhict.s3.websocketserver.GameSession.GameSession;
import nl.fhict.s3.websocketserver.SocketMessage.SocketMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint(value = "/checkers/")
public class GameEndPoint extends Observable {

    private static final Logger log = LoggerFactory.getLogger(GameEndPoint.class);
    private static final List<Session> sessions = new ArrayList<>();
    protected static final Map<Session, Player> playerSession = new HashMap<>();

    private Session session;
    private SocketMessage latestMessage;



    public SocketMessage getlatestMessage() {
        return latestMessage;
    }

    public Session getSession() {
        return session;
    }

    @OnOpen
    public void onConnect(Session session) {
        log.info("Connected SessionID: {}", session.getId());

        sessions.add(session);
        log.info("Session added. Session count is {}", sessions.size());

        if(sessions.size() == 2) {
            sendBroadcast(GameSession.getInstance().startGame());
        }
    }

    @OnMessage
    public void onText(String message, Session session) {
        Gson gson = new Gson();
        log.info("Session ID: {} Received: {}", session.getId(), message);
        latestMessage = gson.fromJson(message, SocketMessage.class);
        this.session = session;
        setChanged();
        notifyObservers();
     //   handleMessageFromClient(message, session);
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        log.info("Session ID: {} Closed. Reason: {}", session.getId(), reason);
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        log.error("Session ID: {} Error: {}", session.getId(), cause.getMessage());
    }

    public void sendBroadcast(String message) {
        for (Session s : sessions) {
            s.getAsyncRemote().sendText(message);
        }
    }
}
