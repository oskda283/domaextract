package se.daniels.domaextract.domain.map;

import java.io.IOException;
import java.util.List;

public interface MapSourceExtractor {
    List<OMap> extract() throws IOException;
}
