package no.oslobysykkel.sykkelstativer.data;

import org.json.JSONArray;
import org.json.JSONObject;

public final class OsloBySykkelApiUtil {
    public static String getStationName(JSONArray stationInfoArray, String stationId){
        for (int i = 0; i < stationInfoArray.length(); i++) {
            JSONObject station = stationInfoArray.getJSONObject(i);
            if(station.get("station_id").toString().equals(stationId)){
                return station.get("name").toString();
            }
        }
        return "NO_NAME";
    }
}
