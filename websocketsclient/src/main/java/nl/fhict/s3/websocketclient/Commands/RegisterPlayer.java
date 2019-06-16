package nl.fhict.s3.websocketclient.Commands;


import Model.Player;
import com.google.gson.Gson;
import nl.fhict.s3.websocketclient.Client;

import nl.fhict.s3.websocketclient.Interface.Command;
import nl.fhict.s3.websocketclient.SocketMessage.SocketMessage;

public class RegisterPlayer implements Command {

    Client client = Client.getInstance();

    @Override
    public void execute(SocketMessage Response) {
        Gson gson = new Gson();
        Player player = gson.fromJson(Response.getMessage(), Player.class);
        if(player != null) {
            client.setClientplayer(player);
        } else {
            client.failedLoginMessage();
        }
    }

    @Override
    public String getName() {
        return "REGISTERPLAYER";
    }
}
