module websocketsclient {

    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;

    requires checkerslogic;
    requires slf4j.api;
    requires gson;
    requires javax.websocket.client.api;

    exports nl.fhict.s3.websocketclient.Controller;
    exports nl.fhict.s3.websocketclient;

}