package se.daniels.domaextract;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import se.daniels.domaextract.application.DomaextractApplication;
import se.daniels.domaextract.application.doma.DomaSourceExtractor;
import se.daniels.domaextract.domain.map.OMap;
import se.daniels.domaextract.domain.mapowner.MapOwner;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DomaextractApplication.class)
public class DomaExtractorTests {

    @Test
    public void shouldExtractNewOmaps() throws IOException {
        DomaSourceExtractor domaSourceExtractor = new DomaSourceExtractor("http://kartarkiv.turebergsif.se/");
        List<OMap> oMaps = domaSourceExtractor.extractAllAfter(Date.from(LocalDateTime.now().minusMonths(2).toInstant(ZoneOffset.UTC)));
        assert !oMaps.isEmpty();
    }

    @Test
    public void shouldExtractAllOmaps() throws IOException {
        DomaSourceExtractor domaSourceExtractor = new DomaSourceExtractor("http://kartarkiv.turebergsif.se/");
        List<OMap> oMaps = domaSourceExtractor.extractAll();
        assert !oMaps.isEmpty();
    }

    @Test
    public void shouldExtractUsers() throws IOException {
        DomaSourceExtractor domaSourceExtractor = new DomaSourceExtractor("http://kartarkiv.turebergsif.se/");
        List<MapOwner> users = domaSourceExtractor.extractAllUsers();
        assert !users.isEmpty();
    }

}
