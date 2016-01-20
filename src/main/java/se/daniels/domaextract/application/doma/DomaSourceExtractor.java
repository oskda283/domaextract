package se.daniels.domaextract.application.doma;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import se.daniels.domaextract.domain.map.MapSourceExtractor;
import se.daniels.domaextract.domain.mapowner.MapSource;
import se.daniels.domaextract.domain.mapowner.MapSourceType;
import se.daniels.domaextract.domain.map.OMap;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class DomaSourceExtractor implements MapSourceExtractor {

    private final MapSource mapSource;

    private final static String ALL_MAPS_URL = "users.php?lastMaps=all&lang=sv";

    public DomaSourceExtractor(String baseUrl) {
        this.mapSource = new MapSource(MapSourceType.DOMA, baseUrl);
    }

    public List<OMap> extract() throws IOException {
        LocalTime start = LocalTime.now();

        Document doc = Jsoup.connect(mapSource.reference + ALL_MAPS_URL).get();
        List<OMap> domaMaps = DomaParser.parse(doc, mapSource);

        Duration duration = Duration.between(start, LocalTime.now());
        System.out.print("Extracted " + domaMaps.size() + " maps in " + duration.getSeconds() + " seconds\n");

        return domaMaps;
    }

}
