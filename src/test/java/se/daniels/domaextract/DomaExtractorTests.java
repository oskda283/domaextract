package se.daniels.domaextract;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import se.daniels.domaextract.application.DomaextractApplication;
import se.daniels.domaextract.application.MapExtractor;
import se.daniels.domaextract.domain.doma.DomaMap;

import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DomaextractApplication.class)
public class DomaExtractorTests {

    @Test
    public void shouldExtractOmaps() throws IOException {
        MapExtractor mapExtractor = new MapExtractor("http://kartarkiv.turebergsif.se/");
        List<DomaMap> domaMaps = mapExtractor.extract();
        assert !domaMaps.isEmpty();
    }
}
