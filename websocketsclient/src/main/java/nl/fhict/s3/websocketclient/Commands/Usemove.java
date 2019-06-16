package nl.fhict.s3.websocketclient.Commands;

import Logic.Game;
import Model.GameBoard.Move;
import com.google.gson.Gson;
import nl.fhict.s3.websocketclient.Client;
import nl.fhict.s3.websocketclient.Interface.Command;
import nl.fhict.s3.websocketclient.SocketMessage.SocketMessage;

public class Usemove implements Command {
    @Override
    public void execute(SocketMessage Response) {
        Gson gson = new Gson();
        Move move = gson.fromJson(Response.getMessage(), Move.class);
        Client.getInstance().getGame().useMove(move);
        Client.getInstance().buildUi();
    }

    @Override
    public String getName() {
        return "USEMOVE";
    }
}
