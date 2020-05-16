package no.oslobysykkel.sykkelstativer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
public class Controller {
    private Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private OsloBysykkelService osloBysykkelService;

    @GetMapping("/api/getStations")
    public String getStationStatus() throws IOException {
        return osloBysykkelService.getStations();
    }
}
