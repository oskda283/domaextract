package se.daniels.domaextract.application.doma;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import se.daniels.domaextract.domain.map.MapSourceExtractor;
import se.daniels.domaextract.domain.mapowner.MapOwner;
import se.daniels.domaextract.domain.mapowner.MapSource;
import se.daniels.domaextract.domain.mapowner.MapSourceType;
import se.daniels.domaextract.domain.map.OMap;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DomaSourceExtractor implements MapSourceExtractor {

    private final MapSource mapSource;

    private final static String ALL_MAPS_URL = "users.php?lastMaps=all&lang=sv";
    private final static String ALL_USERS = "users.php";

    public DomaSourceExtractor(String baseUrl) {
        this.mapSource = new MapSource(MapSourceType.DOMA, baseUrl);
    }

    @Override
    public List<OMap> extractAll() throws IOException {
        LocalTime start = LocalTime.now();

        List<OMap> domaMaps = new ArrayList<>();
        extractAllUsers().forEach(mapOwner -> domaMaps.addAll(extractAllFromUser(mapOwner)));

        Duration duration = Duration.between(start, LocalTime.now());
        System.out.print("Extracted " + domaMaps.size() + " maps in " + duration.getSeconds() + " seconds\n");

        return domaMaps;
    }

    @Override
    public List<OMap> extractAllAfter(Date latestDate) throws IOException {
        try {
            return DomaRssParser.parseAfter(getRssUrl(), latestDate).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Could not get maps from: " + mapSource.reference);
            return new ArrayList<>();
        }
    }

    public List<MapOwner> extractAllUsers() throws IOException {
        Document doc = Jsoup.connect(mapSource.reference + ALL_USERS).get();
        return DomaParser.parseUsers(doc, mapSource);
    }

    private List<OMap> extractAllFromUser(MapOwner mapOwner){
        try {
            return DomaRssParser.parse(getRssUrl(mapOwner)).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Could nod get maps from user: " + mapOwner.userName);
            return new ArrayList<>();
        }
    }

    private String getRssUrl(MapOwner mapOwner) {
        return getRssUrl()+ "?user=" + mapOwner.userName;
    }

    private String getRssUrl() {
        return mapSource.reference + "rss.php";
    }


}
