package nl.fhict.s3.websocketserver.Command.Commands;


import nl.fhict.s3.websocketserver.Interface.Command;
import nl.fhict.s3.websocketserver.SocketMessage.SocketMessage;

public class Connect implements Command {
    @Override
    public void execute(SocketMessage Response) {

    }

    @Override
    public String getName() {
        return "CONNECT";
    }
}
