package no.oslobysykkel.sykkelstativer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class OsloBysykkelService {
    private Logger logger = LoggerFactory.getLogger(OsloBysykkelService.class);
    private HttpClient httpClient;
    private static final String STATION_STATUS_ENDPOINT = "https://gbfs.urbansharing.com/oslobysykkel.no/station_status.json";
    private static final String STATION_INFORMATION_ENDPOINT = "https://gbfs.urbansharing.com/oslobysykkel.no/station_information.json";
    private static final String CLIENT_IDENTIFIER = "testoppgave-sykkelstativer";

    public String getStations() throws IOException {
        JSONArray stations = new JSONArray();
        JSONArray stationInfoArray = getStationInfo();
        JSONArray stationStatusArray = getStationStatus();
        for (int i = 0; i < stationStatusArray.length(); i++) {
            JSONObject station = stationStatusArray.getJSONObject(i);
            JSONObject tempStation = new JSONObject();
            tempStation.put("station_name", getStationName(stationInfoArray, station.get("station_id").toString()));
            tempStation.put("num_bikes_available", station.get("num_bikes_available").toString());
            tempStation.put("num_docks_available", station.get("num_docks_available").toString());
            stations.put(tempStation);
        }
        return stations.toString();
    }

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

    private String getStationName(JSONArray stationInfoArray, String stationId){
        for (int i = 0; i < stationInfoArray.length(); i++) {
            JSONObject station = stationInfoArray.getJSONObject(i);
            if(station.get("station_id").toString().equals(stationId)){
                return station.get("name").toString();
            }
        }
        return "NO_NAME";
    }

    private HttpClient getHttpClient(){
        if(httpClient == null){
            httpClient = HttpClientBuilder.create().build();
        }
        return httpClient;
    }
}
