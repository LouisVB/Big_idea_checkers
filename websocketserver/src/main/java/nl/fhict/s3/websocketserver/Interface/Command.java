package nl.fhict.s3.websocketserver.Interface;


import nl.fhict.s3.websocketserver.SocketMessage.SocketMessage;

public interface Command {
    void execute(SocketMessage Response) ;
                 String getName();
}

