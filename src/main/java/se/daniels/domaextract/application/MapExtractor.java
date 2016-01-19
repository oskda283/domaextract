package se.daniels.domaextract.application;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import se.daniels.domaextract.domain.doma.DomaMap;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class MapExtractor {

    private final String baseUrl;

    private final static String DOMA_ALL_MAPS_URL = "users.php?lastMaps=all&lang=sv";

    public MapExtractor(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<DomaMap> extract() throws IOException {
        LocalTime start = LocalTime.now();

        Document doc = Jsoup.connect(baseUrl + DOMA_ALL_MAPS_URL).get();
        List<DomaMap> domaMaps = DomaParser.parse(doc, baseUrl);

        Duration duration = Duration.between(start, LocalTime.now());
        System.out.print("Extracted " + domaMaps.size() + " maps in " + duration.getSeconds() + " seconds\n");

        return domaMaps;
    }

}
