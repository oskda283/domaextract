package se.daniels.maps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DomaMapBuilder {

    private String name;

    private String url;

    private DomaOwner owner;

    private String localId;

    private Date date;

    private Date updateDate;

    public DomaMapBuilder(){
    }

    public DomaMapBuilder setName(String name){
        this.name = name;
        return this;
    }

    public DomaMapBuilder setUrl(String url) {
        this.url = url;
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

    public DomaMapBuilder setDateFromString(String date) throws ParseException {
        this.date = new SimpleDateFormat("YYYY-MM-DD").parse(date);
        return this;
    }

    public DomaMapBuilder setUpdateDateFromString(String date) throws ParseException {
        this.updateDate = new SimpleDateFormat("YYYY-MM-DD HH:mm").parse(date);
        return this;
    }

    public DomaMap build(){
        return new DomaMap(name,url, localId, owner,date,updateDate);
    }
}
