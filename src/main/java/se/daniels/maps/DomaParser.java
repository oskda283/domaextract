package se.daniels.maps;

import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class DomaParser {

    public static List<DomaMap> parse(Document doc, String baseUrl) throws MalformedURLException {
        List<DomaMap> domaMapList = new ArrayList<>();
        Elements elements = doc.select(".fullWidth").get(1).select("tbody tr");
        for(Element element : elements){
            try {
                domaMapList.add(extractOmap(element, baseUrl));
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (NullPointerException e){
                System.out.print("Could not parse row with data: "+ element.html());
            }
        }
        return domaMapList;
    }

    private static DomaMap extractOmap(Element tableRow, String baseUrl) throws ParseException {
        Elements row = tableRow.select("td");

        return new DomaMapBuilder()
                .setOwner(new DomaOwner(baseUrl,getOwnerUserName(row),getOwnerName(row)))
                .setName(getMapName(row))
                .setDateFromString(getDateString(row))
                .setLocalId(getLocalId(row))
                .setMapUrlFromLocalId(baseUrl)
                .setDomaUrl(baseUrl + getMapUrl(row))
                .setUpdateDateFromString(getUpdatedate(row))
                .build();
    }

    @Nullable
    private static String getUpdatedate(Elements row) {
        try {
            return row.get(4).text();
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Nullable
    private static String getMapUrl(Elements row) {
        try{
            return row.get(1).select("a").get(0).attr("href");
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Nullable
    private static String getLocalId(Elements columns) {
        try {
            String mapUrl = getMapUrl(columns);
            return mapUrl != null ? mapUrl.split("map=")[1] : null ;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Nullable
    private static String getDateString(Elements columns) {
        try {
            return columns.get(2).text();
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Nullable
    private static String getMapName(Elements columns) {
        try {
            return columns.get(1).select("a").get(0).text();
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Nullable
    private static String getOwnerName(Elements columns) {
        try {
            return columns.get(0).select("a").get(0).text();
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Nullable
    private static String getOwnerUserName(Elements columns){
        try {
            return columns.get(0).select("a").get(0).attr("href").split("=")[1];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
