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
import nl.fhict.s3.restshared.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/User")
public class SimpleRestEndpoint {

    private static final Logger log = LoggerFactory.getLogger(SimpleRestEndpoint.class);
    private static PlayerStorage playerStore = PlayerStorage.getInstance();
    private final Gson gson;

    public SimpleRestEndpoint() {
        gson = new Gson();
    }

    @Path("/{User}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getGreeting(@PathParam("User") String username) {
        log.info("GET greeting called for key: {}", username);
        User myResponse = playerStore.getPlayer(username);

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
    public Response addGreeting(User user) {
        log.info("POST add called for key: {}", user.getUsername());

        playerStore.addPlayer(user);

        return Response.status(200).entity(gson.toJson(user)).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response CheckUser(User user) {
        log.info("POST login called for key: {}", user.getUsername());
        User checkeduser = playerStore.getPlayer(user.getUsername());
        if (user.getUsername().equals(checkeduser.getUsername()) && user.getPassword().equals(checkeduser.getPassword())) {

            log.info("User succesfully authtenticated: {}", user.getUsername());
            return Response.status(200).entity(gson.toJson(checkeduser)).build();
        }else {
            log.info("user does not exist: {}", user.getUsername());
            return Response.status(401).build();
        }





    }
}
