package hr.kraljic.corona.data.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeadersParser {
    private static final String HEADERS_REGEX = "<th .+?>(?<headerName>.*?)<\\/th>";

    public static List<String> parseHeader(String htmlTable) {
        List<String> headers = new ArrayList<>();

        final Pattern pattern = Pattern.compile(HEADERS_REGEX, Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(htmlTable);

        while (matcher.find()) {
            String headerName = matcher.group("headerName");
            headers.add(headerName);
        }

        return headers;
    }
}
