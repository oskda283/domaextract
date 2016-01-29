package se.daniels.domaextract.application.doma;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import se.daniels.domaextract.domain.mapowner.MapOwner;
import se.daniels.domaextract.domain.mapowner.MapSource;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class DomaParser {

    public static List<MapOwner> parseUsers(Document doc, MapSource mapSource) {
        List<MapOwner> userList = new ArrayList<>();
        Elements elements = doc.select(".fullWidth").get(0).select("tbody tr");
        for(Element element : elements){
            Elements columns = element.select("td");
            try {
                userList.add(new MapOwner(mapSource, getOwnerUserName(columns), getOwnerName(columns)));
            } catch (NullPointerException e) {
                System.out.print("Could not parse row with data: "+ element.html());
            }
        }
        return userList;
    }

    private static String getDomaUrlEnding(Elements row) {
        try{
            return row.get(1).select("a").get(0).attr("href");
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
