package se.daniels.domaextract.domain.mapowner;

public class MapOwner {

    public final MapSource mapSource;

    public final String userName;

    public final String name;

    public MapOwner(MapSource mapSource, String userName, String name) {
        this.mapSource = mapSource;
        this.userName = userName;
        this.name = name;
    }
}
