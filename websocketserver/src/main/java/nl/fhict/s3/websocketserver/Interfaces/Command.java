package nl.fhict.s3.websocketserver.Interfaces;

import nl.fhict.s3.websocketserver.SocketMessage.SocketMessage;

public interface Command {
    void execute(SocketMessage Response) ;
                 String getName();
}

