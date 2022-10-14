package hr.kraljic.corona.data.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataFieldParser {
    private static final String DATA_REGEX = "<td.*?>((<[^\\/].*?>)*)(?<field>.*?)((<\\/.*?>)*)<\\/td>";

    public static List<String> parseData(String htmlTable) {
        List<String> data = new ArrayList<>();

        final Pattern pattern = Pattern.compile(DATA_REGEX, Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(htmlTable);

        while (matcher.find()) {
            String dataField = matcher.group("field").strip();
            data.add(dataField.isEmpty() ? "0" : dataField);
        }

        return data;
    }
}
