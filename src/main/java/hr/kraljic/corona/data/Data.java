package hr.kraljic.corona.data;

import hr.kraljic.corona.data.parsers.CountryInfoParser;
import hr.kraljic.corona.data.parsers.DataFieldParser;
import hr.kraljic.corona.data.parsers.HeadersParser;
import hr.kraljic.corona.data.parsers.TableParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * Provides data of number of COVID-19 cases around the world.
 * Source of data: https://www.worldometers.info/coronavirus/
 *
 * @author marko
 * @version 2020-03-21
 */
public class Data {
    private static final String URL = "https://www.worldometers.info/coronavirus/";

    private static final int TODAY_TABLE_INDEX = 0;
    private static final int YESTERDAY_TABLE_INDEX = 1;

    /**
     * Get number of cases from today.
     *
     * @return List of number of cases by country.
     * @throws NoDataException If no data could be fetched.
     */
    public static List<CountryInfo> getStatsFromToday() throws NoDataException {
        return getCountryStatsFromTable(TODAY_TABLE_INDEX);
    }

    /**
     * Get number of cases from yesterday.
     *
     * @return List of number of cases by country.
     * @throws NoDataException If no data could be fetched.
     */
    public static List<CountryInfo> getStatsFromYesterday() throws NoDataException {
        return getCountryStatsFromTable(YESTERDAY_TABLE_INDEX);
    }

    private static String downloadHtmlPage(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36")
                .build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        return httpResponse.body();
    }

    private static List<CountryInfo> getCountryStatsFromTable(int tableLookupIndex) throws NoDataException {
        try {
            String htmlPage = downloadHtmlPage(URL);

            List<String> tables = TableParser.extractTablesFromHtml(htmlPage);

            List<String> headers = HeadersParser.parseHeader(tables.get(tableLookupIndex));
            List<String> data = DataFieldParser.parseData(tables.get(tableLookupIndex));

            List<CountryInfo> countryInfoList = CountryInfoParser.parseCountryStats(headers, data);

            return countryInfoList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NoDataException(e);
        }
    }

}
