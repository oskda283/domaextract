package se.daniels.domaextract.domain.map;

import se.daniels.domaextract.domain.gpslocation.GPSLocation;
import se.daniels.domaextract.domain.mapowner.MapOwner;

import java.util.Date;

public class OMap {

    public final String name;

    public final String imageUrl;

    public final String mapUrl;

    public final String localId;

    public final MapOwner owner;

    public final Date date;

    public final Date updateDate;

    public final GPSLocation gpsLocation;

    public OMap(String name, String imageUrl, String mapUrl, String localId, MapOwner owner, Date date, Date updateDate, GPSLocation gpsLocation) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.mapUrl = mapUrl;
        this.localId = localId;
        this.owner = owner;
        this.date = date;
        this.updateDate = updateDate;
        this.gpsLocation = gpsLocation;
    }
}
