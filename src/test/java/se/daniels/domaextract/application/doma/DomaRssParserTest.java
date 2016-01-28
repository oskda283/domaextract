package se.daniels.domaextract.application.doma;

import org.junit.Test;
import se.daniels.domaextract.domain.map.OMap;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class DomaRssParserTest {

    private DomaRssParser domaRssParser = new DomaRssParser();

    @Test
    public void testParse() throws Exception {
        Stream<OMap> maps = domaRssParser.parse(new URL("http://kartarkiv.turebergsif.se/rss.php"));
        List<OMap> mapList = maps.collect(Collectors.toList());
        assert mapList.size() > 0;
    }
}