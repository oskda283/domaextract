package se.daniels.domaextract.application.doma;

import se.daniels.domaextract.domain.DomaRssEntry;
import se.daniels.domaextract.domain.map.OMap;
import se.daniels.domaextract.domain.mapowner.MapOwner;
import se.daniels.domaextract.domain.mapowner.MapSource;
import se.daniels.domaextract.domain.mapowner.MapSourceType;

import static se.daniels.domaextract.domain.mapowner.MapSourceType.DOMA;

public class DomaRssEntryMapper {

    public static OMap toOMap(DomaRssEntry entry){
        return new OMap(
                entry.title,
                id(entry),
                new MapOwner(new MapSource(DOMA,baseUrl(entry)),userName(entry),name(entry)),
                entry.date,
                null
        );
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
