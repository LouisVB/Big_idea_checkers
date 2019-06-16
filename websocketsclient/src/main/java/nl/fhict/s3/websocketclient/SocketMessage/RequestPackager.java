package nl.fhict.s3.websocketclient.SocketMessage;

import Model.GameBoard.Move;
import Model.Player;
import com.google.gson.Gson;

public class RequestPackager {

    private SocketMessage request;

    public RequestPackager()
    {
        request = new SocketMessage();
    }

    public String Registerplayer(Player player)
    {
        request.setOperation(Operation.REGISTERPLAYER);
        request.setMessage((new Gson().toJson(player)));
        return new Gson().toJson(request);
    }

    public String UseMove(Move move)
    {
        request.setOperation(Operation.USEMOVE);
        request.setMessage((new Gson().toJson(move)));
        return new Gson().toJson(request);
    }
}
