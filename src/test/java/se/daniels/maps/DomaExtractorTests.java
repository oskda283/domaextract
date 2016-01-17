package se.daniels.maps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DomaextractApplication.class)
public class DomaExtractorTests {

    @Test
    public void shouldExtractOmaps() throws IOException {
        DomaExtractor domaExtractor = new DomaExtractor("http://kartarkiv.turebergsif.se/");
        List<DomaMap> domaMaps = domaExtractor.extract();
        assert !domaMaps.isEmpty();
    }
}
