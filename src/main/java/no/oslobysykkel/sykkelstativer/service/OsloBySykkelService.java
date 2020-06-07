package no.oslobysykkel.sykkelstativer.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import no.oslobysykkel.sykkelstativer.data.OsloBySykkelApi;
import no.oslobysykkel.sykkelstativer.data.OsloBySykkelApiUtil;
import no.oslobysykkel.sykkelstativer.model.Station;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class OsloBySykkelService {
    @Autowired
    private OsloBySykkelApi osloBySykkelApi;

    public List<Station> getStations() throws IOException {
        JSONArray stations = new JSONArray();
        JSONArray stationInfoArray = osloBySykkelApi.getStationInfo();
        JSONArray stationStatusArray = osloBySykkelApi.getStationStatus();
        for (int i = 0; i < stationStatusArray.length(); i++) {
            JSONObject station = stationStatusArray.getJSONObject(i);
            JSONObject tempStation = new JSONObject();
            tempStation.put("station_name", OsloBySykkelApiUtil.getStationName(stationInfoArray, station.get("station_id").toString()));
            tempStation.put("num_bikes_available", station.get("num_bikes_available").toString());
            tempStation.put("num_docks_available", station.get("num_docks_available").toString());
            stations.put(tempStation);
        }
        Type collectionType = new TypeToken<ArrayList<Station>>(){}.getType();
        ArrayList<Station> stationList = new Gson().fromJson(stations.toString(), collectionType);
        return stationList;
    }

}
