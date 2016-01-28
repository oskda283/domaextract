package se.daniels.domaextract.domain.map;

import se.daniels.domaextract.domain.gpslocation.GPSLocation;
import se.daniels.domaextract.domain.mapowner.MapOwner;

import java.util.Date;

public class OMap {

    public final String name;

    public final String localId;

    public final MapOwner owner;

    public final Date updateDate;

    public final GPSLocation gpsLocation;

    public OMap(String name, String localId, MapOwner owner, Date updateDate, GPSLocation gpsLocation) {
        this.name = name;
        this.localId = localId;
        this.owner = owner;
        this.updateDate = updateDate;
        this.gpsLocation = gpsLocation;
    }
}
