package nl.fhict.s3.websocketclient.Commands;


import Logic.Game;
import com.google.gson.Gson;
import nl.fhict.s3.websocketclient.Client;
import nl.fhict.s3.websocketclient.Interface.Command;
import nl.fhict.s3.websocketclient.SocketMessage.SocketMessage;

public class StartGame implements Command {


    @Override
    public void execute(SocketMessage Response) {
        Gson gson = new Gson();
        Game serverGame = gson.fromJson(Response.getMessage(), Game.class);
        Client.getInstance().setGame(serverGame);
        Client.getInstance().buildUi();
    }

    @Override
    public String getName() {
        return "STARTGAME";
    }

}
