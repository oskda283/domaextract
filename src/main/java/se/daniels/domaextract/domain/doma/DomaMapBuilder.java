package se.daniels.domaextract.domain.doma;

import se.daniels.domaextract.domain.gpslocation.GPSLocation;

import java.util.Date;

public class DomaMapBuilder {

    private String name;

    private String domaUrl;

    private DomaOwner owner;

    private String localId;

    private Date date;

    private Date updateDate;

    private String mapUrl;

    private GPSLocation gpsLocation;

    public DomaMapBuilder(){
    }

    public DomaMapBuilder setName(String mapName){
        return this;
    }

    public DomaMapBuilder setDomaUrl(String domaUrl) {
        this.domaUrl = domaUrl;
        return this;
    }

    public DomaMapBuilder setOwner(DomaOwner owner){
        this.owner = owner;
        return this;
    }

    public DomaMapBuilder setLocalId(String localId){
        this.localId = localId;
        return this;
    }

    public DomaMapBuilder setDate(Date date)  {
        this.date = date;
        return this;
    }

    public DomaMapBuilder setUpdateDate(Date date) {
        this.updateDate = date;
        return this;
    }

    public DomaMapBuilder setMapUrl(String url) {
        this.mapUrl = url;
        return this;
    }

    public DomaMapBuilder setGpsLocation(GPSLocation gpsLocation) {
        this.gpsLocation = gpsLocation;
        return this;
    }

    public DomaMap build(){
        return new DomaMap(name, domaUrl, mapUrl, localId, owner, date, updateDate, gpsLocation);
    }
}
