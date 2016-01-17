package se.daniels.maps;

import java.util.Date;

public class DomaMap {

    public final String name;

    public final String domaUrl;

    public final String mapUrl;

    public final String localId;

    public final DomaOwner owner;

    public final Date date;

    public final Date updateDate;

    public DomaMap(String name, String domaUrl, String mapUrl, String localId, DomaOwner owner, Date date, Date updateDate) {
        this.name = name;
        this.domaUrl = domaUrl;
        this.mapUrl = mapUrl;
        this.localId = localId;
        this.owner = owner;
        this.date = date;
        this.updateDate = updateDate;
    }
}
