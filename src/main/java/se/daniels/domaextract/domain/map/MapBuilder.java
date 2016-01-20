package se.daniels.domaextract.domain.map;

import se.daniels.domaextract.domain.gpslocation.GPSLocation;
import se.daniels.domaextract.domain.mapowner.MapOwner;

import java.util.Date;

public class MapBuilder {

    private String name;

    private MapOwner owner;

    private String localId;

    private Date date;

    private Date updateDate;

    private String imageUrl;

    private String mapUrl;

    private GPSLocation gpsLocation;

    public MapBuilder(){
    }

    public MapBuilder setName(String mapName){
        return this;
    }

    public MapBuilder setOwner(MapOwner owner){
        this.owner = owner;
        return this;
    }

    public MapBuilder setLocalId(String localId){
        this.localId = localId;
        return this;
    }

    public MapBuilder setDate(Date date)  {
        this.date = date;
        return this;
    }

    public MapBuilder setUpdateDate(Date date) {
        this.updateDate = date;
        return this;
    }

    public MapBuilder setImageUrl(String url) {
        this.imageUrl = url;
        return this;
    }

    public MapBuilder setMapUrl(String url){
        this.mapUrl = url;
        return this;
    }

    public MapBuilder setGpsLocation(GPSLocation gpsLocation) {
        this.gpsLocation = gpsLocation;
        return this;
    }

    public OMap build(){
        return new OMap(name, imageUrl, mapUrl, localId, owner, date, updateDate, gpsLocation);
    }
}
