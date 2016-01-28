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
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class DomaRssParser {

    public Stream<OMap> parse(URL url) throws IOException {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = null;
        try {
            feed = input.build(new XmlReader(url));
            return streamOmaps(feed);
        } catch (FeedException e) {
            return Stream.empty();
        }
    }

    private Stream<OMap> streamOmaps(SyndFeed feed) {
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
        return domaRssEntries.stream().map(DomaRssEntryMapper::toOMap);
    }

    private Date date(SyndEntryImpl entry) {
        DCModuleImpl module = (DCModuleImpl) entry.getModules().get(0);
        return module.getDate();
    }

}
