package nl.fhict.s3.websocketclient.Interface;


import nl.fhict.s3.websocketclient.SocketMessage.SocketMessage;

public interface Command {
    void execute(SocketMessage Response) ;
                 String getName();
}

