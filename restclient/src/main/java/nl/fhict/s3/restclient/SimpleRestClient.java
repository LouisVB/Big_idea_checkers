package nl.fhict.s3.restclient;

import com.google.gson.Gson;
import nl.fhict.s3.restshared.Player;
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

class SimpleRestClient {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SimpleRestClient.class);
    private final String url = "http://localhost:8080/greeting/"; // TODO Config file
    private final Gson gson = new Gson();

    SimpleRestClient() {
    }

    Player getGreeting(String key) {
        final String query = url + key;
        log.info("GET: " + query);

        HttpGet httpGetQuery = new HttpGet(query);

        return executeQuery(httpGetQuery);
    }

    Player postGreeting(Player Player) {
        final String query = url + "add";
        log.info("POST: " + query);

        HttpPost httpPostQuery = new HttpPost(query);
        httpPostQuery.addHeader("content-type", "application/json");

        StringEntity params;

        try {
            params = new StringEntity(gson.toJson(Player));
            httpPostQuery.setEntity(params);
        } catch (Exception e) {
            log.error(e.toString());
        }

        return executeQuery(httpPostQuery);
    }

    private Player executeQuery(HttpRequestBase requestBaseQuery) {
        Player player = null;

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(requestBaseQuery)) {
            log.info("Status: " + response.getStatusLine());

            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            log.info("JSON entity: " + entityString);

            player = gson.fromJson(entityString, Player.class);

        } catch (Exception e) {
            log.error(e.toString());
        }

        return player;
    }
}
