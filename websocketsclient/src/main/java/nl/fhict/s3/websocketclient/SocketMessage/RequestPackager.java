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

    public SocketMessage Registerplayer(Player player)
    {
        request.setOperation(Operation.REGISTERPLAYER);
        request.setMessage((new Gson().toJson(player)));
        return request;
    }

    public SocketMessage UseMove(Move move)
    {
        request.setOperation(Operation.USEMOVE);

        return request;
    }
//
//    public SocketMessage shoot()
//    {
//        request.setOperation(Operation.SHOOT);
//        request.addParameter(new String[] {MoveType.SHOOT.toString()});
//        return request;
//    }
}
