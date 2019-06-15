package nl.fhict.s3.websocketserver.Command.Commands;

import Model.GameBoard.Type.PieceColor;
import Model.Player;
import com.google.gson.Gson;
import nl.fhict.s3.restclient.PlayerRestClient;
import nl.fhict.s3.restshared.User;
import nl.fhict.s3.websocketserver.GameSession.GameSession;
import nl.fhict.s3.websocketserver.Interface.Command;
import nl.fhict.s3.websocketserver.SocketMessage.RequestPackager;
import nl.fhict.s3.websocketserver.SocketMessage.SocketMessage;
import nl.fhict.s3.websocketserver.endpoint.GameEndPoint;

import javax.websocket.CloseReason;


public class RegisterPlayer implements Command {

    private javax.websocket.Session userSession;

    public void setUserSession(javax.websocket.Session userSession) {
        this.userSession = userSession;
    }

    @Override
    public void execute(SocketMessage Response) {

        RequestPackager packager = new RequestPackager();
        GameEndPoint endPoint = GameEndPoint.getInstance();
        Gson gson = new Gson();
        PlayerRestClient restClient = new PlayerRestClient();

        Player player = gson.fromJson(Response.getMessage(), Player.class);

        if(restClient.authenticateUser(new User(player.getUsername(), player.getPassword())) != null) {
            if(endPoint.getPlayerSession().size() == 0) {
                player.setMyPieces(PieceColor.Red);
            }else  {
                player.setMyPieces(PieceColor.Red);
            }
            GameSession gameSession = GameSession.getInstance();
            gameSession.getGame().newPlayer(player);
            endPoint.addPlayer(player, userSession);
            endPoint.sendMessage(userSession, packager.Registerplayer(player));
        }else {
            endPoint.sendMessage(userSession, packager.Registerplayer(new Player()));
        }

    }

    @Override
    public String getName() {
        return "REGISTERPLAYER";
    }
}
