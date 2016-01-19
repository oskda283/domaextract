package se.daniels.domaextract.application;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import se.daniels.domaextract.domain.doma.DomaMap;
import se.daniels.domaextract.domain.doma.DomaMapBuilder;
import se.daniels.domaextract.domain.doma.DomaOwner;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DomaParser {

    public static List<DomaMap> parse(Document doc, String baseUrl) throws MalformedURLException {
        List<DomaMap> domaMapList = new ArrayList<>();
        Elements elements = doc.select(".fullWidth").get(1).select("tbody tr");
        for(Element element : elements){
            try {
                domaMapList.add(extractOmap(element, baseUrl));
            } catch (ParseException | NullPointerException e) {
                System.out.print("Could not parse row with data: "+ element.html());
            }
        }
        return domaMapList;
    }

    private static DomaMap extractOmap(Element tableRow, String baseUrl) throws ParseException {
        Elements row = tableRow.select("td");
        String localId = getLocalId(row);
        String mapUrl = getMapUrl(baseUrl,localId);

        return new DomaMapBuilder()
                .setOwner(new DomaOwner(baseUrl,getOwnerUserName(row),getOwnerName(row)))
                .setName(getMapName(row))
                .setDate(getDateString(row))
                .setLocalId(localId)
                .setMapUrl(mapUrl)
                .setDomaUrl(baseUrl + getDomaUrlEnding(row))
                .setUpdateDate(getUpdateDate(row))
                .setGpsLocation(GPSLocationExtractor.extractFromJpgUrl(mapUrl))
                .build();
    }

    private static Date getUpdateDate(Elements row) throws ParseException {
        try {
            return new SimpleDateFormat("YYYY-MM-DD HH:mm").parse(row.get(4).html());
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private static String getDomaUrlEnding(Elements row) {
        try{
            return row.get(1).select("a").get(0).attr("href");
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private static String getLocalId(Elements columns) {
        try {
            String mapUrl = getDomaUrlEnding(columns);
            return mapUrl != null ? mapUrl.split("map=")[1] : null ;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private static Date getDateString(Elements columns) throws ParseException {
        try {
            return new SimpleDateFormat("YYYY-MM-DD").parse(columns.get(2).text());
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private static String getMapName(Elements columns) {
        try {
            return columns.get(1).select("a").get(0).text();
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private static String getOwnerName(Elements columns) {
        try {
            return columns.get(0).select("a").get(0).text();
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private static String getOwnerUserName(Elements columns){
        try {
            return columns.get(0).select("a").get(0).attr("href").split("=")[1];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private static String getMapUrl(String baseUrl, String localId) {
        final String url = baseUrl + "map_images/" + localId + ".jpg";
        return testUrl(url) ? url : null;
    }

    private static boolean testUrl(String url) {
        try {
            final URL testUrl = new URL(url);
            HttpURLConnection huc = (HttpURLConnection) testUrl.openConnection();
            huc.setRequestMethod("HEAD");
            int responseCode = huc.getResponseCode();
            return responseCode == 200;
        } catch (IOException e) {
            return false;
        }
    }


}
