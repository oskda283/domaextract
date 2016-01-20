package se.daniels.domaextract;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import se.daniels.domaextract.application.DomaextractApplication;
import se.daniels.domaextract.application.doma.DomaSourceExtractor;
import se.daniels.domaextract.domain.map.OMap;

import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DomaextractApplication.class)
public class DomaExtractorTests {

    @Test
    public void shouldExtractOmaps() throws IOException {
        DomaSourceExtractor domaSourceExtractor = new DomaSourceExtractor("http://kartarkiv.turebergsif.se/");
        List<OMap> oMaps = domaSourceExtractor.extract();
        assert !oMaps.isEmpty();
    }
}
