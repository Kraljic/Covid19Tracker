package hr.kraljic.corona.data.parsers;

import hr.kraljic.corona.data.CountryInfo;

import java.util.ArrayList;
import java.util.List;

public class CountryInfoParser {

    public static List<CountryInfo> parseCountryStats(List<String> headers, List<String> dataFields) {
        int fieldsPerCountry = headers.size();
        int numberOfCountries = dataFields.size() / fieldsPerCountry;

        List<CountryInfo> countryInfoList = new ArrayList<>(numberOfCountries);

        CountryInfo countryInfoTmp = null;
        for (int i = 0; i < dataFields.size(); i++) {
            if (i % headers.size() == 0) {
                if (countryInfoTmp != null)
                    countryInfoList.add(countryInfoTmp);
                countryInfoTmp = new CountryInfo();
            }

            switch (headers.get(i % headers.size())) {
                case "Country, Other":
                    countryInfoTmp.setCountry(dataFields.get(i));
                    break;
                case "Total Cases":
                    countryInfoTmp.setTotalCases(Integer.valueOf(dataFields.get(i).replaceAll(",", "")));
                    break;
                case "New Cases":
                    countryInfoTmp.setNewCases(Integer.valueOf(dataFields.get(i).replaceAll(",", "")));
                    break;
                case "Total Deaths":
                    countryInfoTmp.setTotalDeaths(Integer.valueOf(dataFields.get(i).replaceAll(",", "")));
                    break;
                case "New Deaths":
                    countryInfoTmp.setNewDeaths(Integer.valueOf(dataFields.get(i).replaceAll(",", "")));
                    break;
                case "Total Recovered":
                    countryInfoTmp.setTotalRecovered(Integer.valueOf(dataFields.get(i).replaceAll(",", "")));
                    break;
                case "Active Cases":
                    countryInfoTmp.setActiveCases(Integer.valueOf(dataFields.get(i).replaceAll(",", "")));
                    break;
                case "Serious, Critical":
                    countryInfoTmp.setSeriousCases(Integer.valueOf(dataFields.get(i).replaceAll(",", "")));
                    break;
                case "Tot Cases/ 1M pop":
                    countryInfoTmp.setTotalCasesPer1MPopulation(Double.valueOf(dataFields.get(i).replaceAll(",", "")));
                    break;
            }
        }

        if (countryInfoTmp != null)
            countryInfoList.add(countryInfoTmp);

        return countryInfoList;
    }
}
