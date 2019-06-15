package nl.fhict.s3.restserver.endpoint;

import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.fhict.s3.restserver.data.PlayerStorage;
import nl.fhict.s3.restshared.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/Player")
public class SimpleRestEndpoint {

    private static final Logger log = LoggerFactory.getLogger(SimpleRestEndpoint.class);
    private static PlayerStorage playerStore = PlayerStorage.getInstance();
    private final Gson gson;

    public SimpleRestEndpoint() {
        gson = new Gson();
    }

    @Path("/{Player}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getGreeting(@PathParam("Player") String username) {
        log.info("GET greeting called for key: {}", username);
        Player myResponse = playerStore.getPlayer(username);

        return Response.status(200).entity(gson.toJson(myResponse)).build();
    }

    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getAllGreetings() {
        log.info("GET all called");

        return Response.status(200).entity(gson.toJson(playerStore.getAllPlayer())).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGreeting(Player Player) {
        log.info("POST add called for key: {}", Player.getUsername());

        playerStore.addPlayer(Player);

        return Response.status(200).entity(gson.toJson(Player)).build();
    }
}
