package nl.fhict.s3.restclient;


import nl.fhict.s3.restshared.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestClient {

    private static final Logger log = LoggerFactory.getLogger(RestClient.class);

    public static void main(String[] args) {

        PlayerRestClient client = new PlayerRestClient();

        // Post new greeting
        User user = client.authenticateUser(new User("Louis", "Gopher"));
//
//        // Get a greeting
//        greeting = client.getGreeting(key);
        logGreeting(user);
    }

    private static void logGreeting(User user) {
        if (user != null) {
            log.info("{} {}", user.getUsername(), user.getPassword());
        } else {
            log.info("No greeting found.");
        }
    }
}

