package nl.fhict.s3.restclient;

import nl.fhict.s3.restshared.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestClient {

    private static final Logger log = LoggerFactory.getLogger(RestClient.class);

    public static void main(String[] args) {

        SimpleRestClient client = new SimpleRestClient();
        final String key = "Leon";
        final int age = 88;

        // Post new greeting
        Greeting greeting = client.postGreeting(new Greeting(key, age));
        logGreeting(greeting);

        // Get a greeting
        greeting = client.getGreeting(key);
        logGreeting(greeting);
    }

    private static void logGreeting(Greeting greeting) {
        if (greeting != null) {
            log.info("{} {}", greeting.getName(), greeting.getAge());
        } else {
            log.info("No greeting found.");
        }
    }
}

