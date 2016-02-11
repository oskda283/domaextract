package se.daniels.domaextract.application.doma;

import se.daniels.domaextract.application.gpslocation.GPSLocationExtractor;
import se.daniels.domaextract.domain.DomaRssEntry;
import se.daniels.domaextract.domain.gpslocation.GPSLocation;
import se.daniels.domaextract.domain.map.OMap;
import se.daniels.domaextract.domain.mapowner.MapOwner;
import se.daniels.domaextract.domain.mapowner.MapSource;
import se.daniels.domaextract.domain.mapowner.MapSourceType;

import java.util.Optional;
import java.util.regex.PatternSyntaxException;

import static se.daniels.domaextract.domain.mapowner.MapSourceType.DOMA;

public class DomaRssEntryMapper {

    public static Optional<OMap> toOMap(DomaRssEntry entry){
        try {
            return Optional.of(new OMap(
                    entry.title,
                    id(entry),
                    new MapOwner(new MapSource(DOMA,baseUrl(entry)),userName(entry),name(entry)),
                    entry.date,
                    location(entry),
                    mapUrl(entry)
            ));
        } catch (RuntimeException e){
            System.out.println("Could not create map from: " + entry.toString());
            return Optional.empty();
        }
    }

    private static GPSLocation location(DomaRssEntry entry) {
        return GPSLocationExtractor.extractFromJpgUrl(mapUrl(entry));
    }

    private static String mapUrl(DomaRssEntry entry) { return baseUrl(entry) + "map_images/" + id(entry) + ".jpg";
    }

    private static String name(DomaRssEntry entry) {
        return entry.description.split("<br />")[0];
    }

    private static String baseUrl(DomaRssEntry entry){
        return entry.url.split("show_map.php")[0];
    }

    private static String userName(DomaRssEntry entry){
        return entry.url.split("user=")[1].split("&map")[0];
    }

    private static String id(DomaRssEntry entry){
        return entry.url.split("map=")[1];
    }
}
