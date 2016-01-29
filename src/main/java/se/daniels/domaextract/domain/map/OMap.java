package se.daniels.domaextract.domain.map;

import se.daniels.domaextract.domain.gpslocation.GPSLocation;
import se.daniels.domaextract.domain.mapowner.MapOwner;

import java.util.Date;

public class OMap {

    private String name;

    private String localId;

    private MapOwner owner;

    private Date updateDate;

    private GPSLocation gpsLocation;

    private String imageUrl;

    public OMap(String name, String localId, MapOwner owner, Date updateDate, GPSLocation gpsLocation, String imageUrl) {
        this.name = name;
        this.localId = localId;
        this.owner = owner;
        this.updateDate = updateDate;
        this.gpsLocation = gpsLocation;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getLocalId() {
        return localId;
    }

    public MapOwner getOwner() {
        return owner;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public GPSLocation getGpsLocation() {
        return gpsLocation;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setGpsLocation(GPSLocation gpsLocation) {
        this.gpsLocation = gpsLocation;
    }
}
