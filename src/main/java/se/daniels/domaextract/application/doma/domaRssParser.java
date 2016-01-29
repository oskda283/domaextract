package se.daniels.domaextract.application.doma;

import com.sun.syndication.feed.module.DCModuleImpl;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import se.daniels.domaextract.domain.DomaRssEntry;
import se.daniels.domaextract.domain.map.OMap;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class DomaRssParser {

    public static Stream<DomaRssEntry> parseRss(String urlString) throws IOException {
        URL url = new URL(urlString);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = null;
        try {
            feed = input.build(new XmlReader(url));
            return streamEntries(feed);
        } catch (FeedException e) {
            return Stream.empty();
        }
    }

    public static Stream<OMap> parse(String urlString) throws IOException {
        return parseRss(urlString).map(DomaRssEntryMapper::toOMap)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(DomaRssParser::imageExist);
    }

    public static Stream<OMap> parseAfter(String rssUrl, Date latestDate) throws IOException {
        return parseRss(rssUrl)
                .filter(entry -> entry.date.after(latestDate))
                .map(DomaRssEntryMapper::toOMap)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(DomaRssParser::imageExist);
    }

    private static Stream<DomaRssEntry> streamEntries(SyndFeed feed) {
        List<DomaRssEntry> domaRssEntries = new ArrayList<DomaRssEntry>();
        List<SyndEntryImpl> entries = (List<SyndEntryImpl>) feed.getEntries();
        for(SyndEntryImpl entry : entries){
            domaRssEntries.add(new DomaRssEntry(
                    entry.getUri(),
                    entry.getTitle(),
                    date(entry),
                    entry.getDescription().getValue()
            ));
        }
        return domaRssEntries.stream();
    }

    private static boolean imageExist(OMap oMap) {
        return exists(oMap.getImageUrl());
    }

    private static boolean exists(String url) {
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

    private static Date date(SyndEntryImpl entry) {
        DCModuleImpl module = (DCModuleImpl) entry.getModules().get(0);
        return module.getDate();
    }
}
