package no.oslobysykkel.sykkelstativer.service;

import no.oslobysykkel.sykkelstativer.data.OsloBySykkelApi;
import no.oslobysykkel.sykkelstativer.model.Station;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OsloBySykkelServiceTest {
    @InjectMocks
    private OsloBySykkelService osloBySykkelService;

    @Mock
    private OsloBySykkelApi osloBySykkelApi;

    @Test
    public void getStations_returnsListOfStations() throws Exception {
        JSONArray stationInfoArray = createStationInfoTestData();
        when(osloBySykkelApi.getStationInfo()).thenReturn(stationInfoArray);

        JSONArray stationStatusArray = createStationStatusTestData();
        when(osloBySykkelApi.getStationStatus()).thenReturn(stationStatusArray);

        List<Station> stations = osloBySykkelService.getStations();
        assertTrue(stations.size() == 2);
    }

    private JSONArray createStationStatusTestData() throws JSONException {
        JSONArray stationStatusArray = new JSONArray();
        JSONObject stationStatus1 = new JSONObject();
        stationStatus1.put("station_id", "627");
        stationStatus1.put("num_bikes_available", 7);
        stationStatus1.put("num_docks_available", 5);
        stationStatusArray.put(stationStatus1);
        JSONObject stationStatus2 = new JSONObject();
        stationStatus2.put("station_id", "1009");
        stationStatus2.put("num_bikes_available", 10);
        stationStatus2.put("num_docks_available", 0);
        stationStatusArray.put(stationStatus2);
        return stationStatusArray;
    }

    private JSONArray createStationInfoTestData() throws JSONException {
        JSONArray stationInfoArray = new JSONArray();
        JSONObject stationInfo1 = new JSONObject();
        stationInfo1.put("station_id", "627");
        stationInfo1.put("name", "Skøyen Stasjon");
        stationInfoArray.put(stationInfo1);
        JSONObject stationInfo2 = new JSONObject();
        stationInfo2.put("station_id", "1009");
        stationInfo2.put("name", "Borgenveien");
        stationInfoArray.put(stationInfo2);
        return stationInfoArray;
    }
}
