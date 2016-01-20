package se.daniels.domaextract.domain.mapowner;

public class MapSource {

    public final MapSourceType type;

    public final String reference;

    public MapSource(MapSourceType type, String reference) {
        this.type = type;
        this.reference = reference;
    }

}
