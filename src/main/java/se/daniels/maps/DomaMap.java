package se.daniels.maps;

import java.net.URL;
import java.util.Date;

public class DomaMap {

    public final String name;

    public final String url;

    public final String localId;

    public final DomaOwner owner;

    public final Date date;

    public final Date updateDate;

    public DomaMap(String name, String url, String localId, DomaOwner owner, Date date, Date updateDate) {
        this.name = name;
        this.url = url;
        this.localId = localId;
        this.owner = owner;
        this.date = date;
        this.updateDate = updateDate;
    }
}
