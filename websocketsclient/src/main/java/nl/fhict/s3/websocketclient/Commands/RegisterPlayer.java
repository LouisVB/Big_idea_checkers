package nl.fhict.s3.websocketclient.Commands;

import nl.fhict.s3.websocketclient.Interface.Command;
import nl.fhict.s3.websocketclient.SocketMessage.SocketMessage;

public class RegisterPlayer implements Command {

    @Override
    public void execute(SocketMessage Response) {

    }

    @Override
    public String getName() {
        return "REGISTERPLAYER";
    }
}
