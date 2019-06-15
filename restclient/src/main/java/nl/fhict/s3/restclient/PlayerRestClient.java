package nl.fhict.s3.restclient;

import com.google.gson.Gson;
import nl.fhict.s3.restshared.User;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

public class PlayerRestClient {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PlayerRestClient.class);
    private final String url = "http://localhost:8080/User/"; // TODO Config file
    private final Gson gson = new Gson();

    public PlayerRestClient() {
    }

    public User getUser(String key) {
        final String query = url + key;
        log.info("GET: " + query);

        HttpGet httpGetQuery = new HttpGet(query);

        return executeQuery(httpGetQuery);
    }

    public User authenticateUser(User User) {
        final String query = url + "login";
        log.info("POST: " + query);

        HttpPost httpPostQuery = new HttpPost(query);
        httpPostQuery.addHeader("content-type", "application/json");

        StringEntity params;

        try {
            params = new StringEntity(gson.toJson(User));
            httpPostQuery.setEntity(params);
        } catch (Exception e) {
            log.error(e.toString());
        }

        return executeQuery(httpPostQuery);
    }

    private User executeQuery(HttpRequestBase requestBaseQuery) {
        User user = null;

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(requestBaseQuery)) {
            log.info("Status: " + response.getStatusLine());

            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            log.info("JSON entity: " + entityString);

            user = gson.fromJson(entityString, User.class);

        } catch (Exception e) {
            log.error(e.toString());
        }

        return user;
    }
}
