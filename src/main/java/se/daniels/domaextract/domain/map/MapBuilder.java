package se.daniels.domaextract.domain.map;

import se.daniels.domaextract.domain.gpslocation.GPSLocation;
import se.daniels.domaextract.domain.mapowner.MapOwner;

import java.util.Date;

public class MapBuilder {

    private String name;

    private MapOwner owner;

    private String localId;

    private Date updateDate;

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

    public MapBuilder setUpdateDate(Date date) {
        this.updateDate = date;
        return this;
    }

    public MapBuilder setGpsLocation(GPSLocation gpsLocation) {
        this.gpsLocation = gpsLocation;
        return this;
    }

    public OMap build(){
        return new OMap(name, localId, owner, updateDate, gpsLocation);
    }
}
