package se.daniels.maps;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public class DomaExtractor {

    private final String baseUrl;

    private final static String ALL_MAPS_URL = "users.php?lastMaps=all&lang=sv";

    public DomaExtractor(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<DomaMap> extract() throws IOException {
        Document doc = Jsoup.connect(baseUrl + ALL_MAPS_URL).get();
        return DomaParser.parse(doc, baseUrl);
    }

}
