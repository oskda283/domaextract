package se.daniels.domaextract.domain.doma;

import se.daniels.domaextract.domain.gpslocation.GPSLocation;

import java.util.Date;

public class DomaMap {

    public final String name;

    public final String domaUrl;

    public final String mapUrl;

    public final String localId;

    public final DomaOwner owner;

    public final Date date;

    public final Date updateDate;

    public final GPSLocation gpsLocation;

    public DomaMap(String name, String domaUrl, String mapUrl, String localId, DomaOwner owner, Date date, Date updateDate, GPSLocation gpsLocation) {
        this.name = name;
        this.domaUrl = domaUrl;
        this.mapUrl = mapUrl;
        this.localId = localId;
        this.owner = owner;
        this.date = date;
        this.updateDate = updateDate;
        this.gpsLocation = gpsLocation;
    }
}