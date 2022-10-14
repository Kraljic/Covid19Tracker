package hr.kraljic.corona.app.services;

import hr.kraljic.corona.data.CountryInfo;
import hr.kraljic.corona.data.Data;
import hr.kraljic.corona.data.NoDataException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaService {
    private List<CountryInfo> covid19DataToday = new ArrayList<>();
    private List<CountryInfo> covid19DataYesterday = new ArrayList<>();

    // Refresh data everyday in 3AM
//    @PostConstruct
//    @Scheduled(cron = "* * 3 * * *")
    private void fetchCoronaData() {
        try {
            this.covid19DataToday = Data.getStatsFromToday();
            this.covid19DataYesterday = Data.getStatsFromYesterday();
            System.out.println("Initialized COVID-19 data.");
        } catch (NoDataException e) {
            e.printStackTrace();
        }
    }

    // Get new data every 10 minutes
//    @Scheduled(cron = "1 */10 * * * *")
    private void fetchNewData() {
        try {
            var newDataToday = Data.getStatsFromToday();
            this.covid19DataToday = newDataToday;
            System.out.println("Refreshed COVID-19 data.");
        } catch (NoDataException e) {
            e.printStackTrace();
        }
    }

    public List<CountryInfo> getDataToday() {
        return this.covid19DataToday;
    }

    public List<CountryInfo> getDataYesterday() {
        return this.covid19DataYesterday;
    }
}
