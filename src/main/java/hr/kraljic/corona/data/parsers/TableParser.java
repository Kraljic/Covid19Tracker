package hr.kraljic.corona.data.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableParser {
    private static final String TABLE_REGEX = "(?<table><table.*?>.*?<\\/table>)";

    public static List<String> extractTablesFromHtml(String htmlPage) {
        List<String> tables = new ArrayList<>();

        final Pattern pattern = Pattern.compile(TABLE_REGEX, Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(htmlPage);

        while (matcher.find()) {
            String table = matcher.group("table")
                    .replaceAll("(\\n)|(<br \\/>)", " ")
                    .replaceAll("&nbsp;", " ")
                    .replaceAll("&.+?;", "?");
            tables.add(table);
        }

        return tables;
    }
}
