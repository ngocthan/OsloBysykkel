package no.oslobysykkel.sykkelstativer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
    private final String CONTENT_TYPE_TEXT_PLAIN_UTF8 = "text/plain;charset=UTF-8"; //UTF-8 for norwegian characters Ø Æ Å

    @Autowired
    private MockMvc mvc;

    @Test
    public void getStationStatus_returnsHttpStatus200Ok() throws Exception {
        mvc.perform(get("/api/getStations"))
                .andExpect(status().isOk());
    }

    @Test
    public void getStationStatus_returnsExpectedContentType() throws Exception {
        mvc.perform(get("/api/getStations"))
                .andExpect(content().contentTypeCompatibleWith(CONTENT_TYPE_TEXT_PLAIN_UTF8));
    }

   @Test
    public void getStationStatus_returnsExpectedJsonModel() throws Exception {
        mvc.perform(get("/api/getStations"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").isMap())
                .andExpect(jsonPath("$[0][\"station_name\"]").isNotEmpty())
                .andExpect(jsonPath("$[0][\"num_docks_available\"]").isNotEmpty())
                .andExpect(jsonPath("$[0][\"num_bikes_available\"]").isNotEmpty());
    }
}
