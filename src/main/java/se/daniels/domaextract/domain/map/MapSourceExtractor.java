package se.daniels.domaextract.domain.map;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface MapSourceExtractor {
    List<OMap> extractAll() throws IOException;
    List<OMap> extractAllAfter(Date latestDate) throws IOException;
}
