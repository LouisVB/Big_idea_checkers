package nl.fhict.s3.websocketserver.Command.Commands;

import Model.GameBoard.Move;
import com.google.gson.Gson;
import nl.fhict.s3.websocketserver.GameSession.GameSession;
import nl.fhict.s3.websocketserver.Interface.Command;
import nl.fhict.s3.websocketserver.SocketMessage.RequestPackager;
import nl.fhict.s3.websocketserver.SocketMessage.SocketMessage;

public class UseMove implements Command {


    @Override
    public void execute(SocketMessage Response) {
        RequestPackager packager = new RequestPackager();
        Gson gson = new Gson();
        Move move = gson.fromJson(Response.getMessage(), Move.class);
        GameSession.getInstance().getGame().useMove(move);
        GameSession.getInstance().broadcastToClients(packager.UseMove(move));
    }

    @Override
    public String getName() {
        return "USEMOVE";
    }
}
