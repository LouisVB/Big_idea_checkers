package Interfaces;


import SocketMessage.SocketMessage;

public interface Command {
    void execute(SocketMessage Response) ;
                 String getName();
}

