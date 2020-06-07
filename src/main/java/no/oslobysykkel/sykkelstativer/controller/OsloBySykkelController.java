package no.oslobysykkel.sykkelstativer.controller;

import no.oslobysykkel.sykkelstativer.model.Station;
import no.oslobysykkel.sykkelstativer.service.OsloBySykkelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class OsloBySykkelController {
    @Autowired
    private OsloBySykkelService osloBySykkelService;

    @GetMapping(value = "/api/getStations",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Station> getStationStatus() throws IOException {
        return osloBySykkelService.getStations();
    }
}
