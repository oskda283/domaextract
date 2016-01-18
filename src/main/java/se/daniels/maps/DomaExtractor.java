package se.daniels.maps;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class DomaExtractor {

    private final String baseUrl;

    private final static String ALL_MAPS_URL = "users.php?lastMaps=all&lang=sv";

    public DomaExtractor(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<DomaMap> extract() throws IOException {
        LocalTime start = LocalTime.now();

        Document doc = Jsoup.connect(baseUrl + ALL_MAPS_URL).get();
        List<DomaMap> domaMaps = DomaParser.parse(doc, baseUrl);

        Duration duration = Duration.between(start, LocalTime.now());
        System.out.print("Extracted " + domaMaps.size() + " maps in " + duration.getSeconds() + " seconds\n");

        return domaMaps;
    }

}
