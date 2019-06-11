package nl.fhict.s3.websocketclient.Commands;

import nl.fhict.s3.websocketclient.Client;
import nl.fhict.s3.websocketclient.Interface.Command;
import nl.fhict.s3.websocketclient.SocketMessage.SocketMessage;

public class Connect implements Command {
    @Override
    public void execute(SocketMessage Response) {
        Client.getInstance().buildUi();
    }

    @Override
    public String getName() {
        return "CONNECT";
    }
}
