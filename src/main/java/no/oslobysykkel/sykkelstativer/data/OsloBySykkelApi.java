package no.oslobysykkel.sykkelstativer.data;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OsloBySykkelApi {
    private HttpClient httpClient;
    private static final String STATION_STATUS_ENDPOINT = "https://gbfs.urbansharing.com/oslobysykkel.no/station_status.json";
    private static final String STATION_INFORMATION_ENDPOINT = "https://gbfs.urbansharing.com/oslobysykkel.no/station_information.json";
    private static final String CLIENT_IDENTIFIER = "testoppgave-sykkelstativer";

    public JSONArray getStationInfo() throws IOException {
        HttpClient httpClient = getHttpClient();
        HttpGet getRequest = new HttpGet(STATION_INFORMATION_ENDPOINT);
        getRequest.addHeader("Client-Identifier", CLIENT_IDENTIFIER);
        HttpResponse response = httpClient.execute(getRequest);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
        }
        HttpEntity httpEntity = response.getEntity();
        JSONObject jsonObject = new JSONObject(EntityUtils.toString(httpEntity));
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("stations");
        return jsonArray;
    }

    public JSONArray getStationStatus() throws IOException {
        HttpClient httpClient = getHttpClient();
        HttpGet getRequest = new HttpGet(STATION_STATUS_ENDPOINT);
        getRequest.addHeader("Client-Identifier", CLIENT_IDENTIFIER);
        HttpResponse response = httpClient.execute(getRequest);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
        }
        HttpEntity httpEntity = response.getEntity();
        JSONObject jsonObject = new JSONObject(EntityUtils.toString(httpEntity));
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("stations");
        return jsonArray;
    }

    private HttpClient getHttpClient(){
        if(httpClient == null){
            httpClient = HttpClientBuilder.create().build();
        }
        return httpClient;
    }
}
