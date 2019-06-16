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
import javax.websocket.Session;


public class RegisterPlayer implements Command {

    private javax.websocket.Session userSession;
    private RequestPackager packager;
    private GameEndPoint endPoint = GameEndPoint.getInstance();
    private GameSession gameSession = GameSession.getInstance();
    private Gson gson;
    private PlayerRestClient restClient;


    public void setUserSession(Session userSession) {
        this.userSession = userSession;
    }

    public RegisterPlayer() {
        packager = new RequestPackager();
        gson = new Gson();
        restClient = new PlayerRestClient();
    }

    @Override
    public void execute(SocketMessage Response) {
        Player player = gson.fromJson(Response.getMessage(), Player.class);

        if(restClient.authenticateUser(new User(player.getUsername(), player.getPassword())) != null) {
            if(gameSession.getGame().getPlayers().isEmpty()) {
                player.setMyPieces(PieceColor.Red);
                GameSession.getInstance().getGame().setCurrentPlayerAtTurn(player);
            }else  {
                player.setMyPieces(PieceColor.White);
            }
            gameSession.getGame().newPlayer(player);
            endPoint.addPlayer(player, userSession);
            endPoint.sendMessage(userSession, packager.Registerplayer(player));
            if(gameSession.getGame().getPlayers().size() == 2) {
                GameSession.getInstance().getGame().startGame();
                endPoint.sendBroadcast(packager.startGame(GameSession.getInstance().getGame()));
            }
        }else { // if authentication failes, send empty player back.
            endPoint.sendMessage(userSession, packager.Registerplayer(new Player()));
        }
    }

    @Override
    public String getName() {
        return "REGISTERPLAYER";
    }
}
