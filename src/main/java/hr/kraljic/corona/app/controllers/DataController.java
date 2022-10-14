package hr.kraljic.corona.app.controllers;

import hr.kraljic.corona.data.CountryInfo;
import hr.kraljic.corona.app.services.CoronaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {

    private CoronaService coronaService;

    public DataController(CoronaService coronaService) {
        this.coronaService = coronaService;
    }

    @GetMapping("today")
    public List<CountryInfo> getDataToday() {
        return coronaService.getDataToday();
    }

    @GetMapping("yesterday")
    public List<CountryInfo> getDataYesterday() {
        return coronaService.getDataYesterday();
    }
}
