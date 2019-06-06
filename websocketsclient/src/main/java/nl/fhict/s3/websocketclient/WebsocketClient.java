package nl.fhict.s3.websocketclient;

import java.util.Observable;
import java.util.Observer;
import nl.fhict.s3.websocketclient.endpoint.GameClientEndPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebsocketClient implements Observer {

    private static final Logger log = LoggerFactory.getLogger(WebsocketClient.class);

    void start() {
        try {
            GameClientEndPoint gameClientEndPoint = GameClientEndPoint.getInstance();
            gameClientEndPoint.addObserver(this);
            gameClientEndPoint.start();
            log.info("Websocket client started");

           // gameClientEndPoint.sendMessageToServer(new Greeting("Whoohoo", 50));

            gameClientEndPoint.stop();
            log.info("Websocket client stopped");
        } catch (Exception ex) {
            log.error("Client couldn't start.");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        log.info("Update called. {}", arg);
    }
}
