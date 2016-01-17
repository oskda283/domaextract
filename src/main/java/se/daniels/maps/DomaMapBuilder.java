package se.daniels.maps;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DomaMapBuilder {

    private String name;

    private String domaUrl;

    private DomaOwner owner;

    private String localId;

    private Date date;

    private Date updateDate;

    private String mapUrl;

    public DomaMapBuilder(){
    }

    public DomaMapBuilder setName(@Nullable String mapName){
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

    public DomaMapBuilder setDateFromString(String date) throws ParseException {
        this.date = new SimpleDateFormat("YYYY-MM-DD").parse(date);
        return this;
    }

    public DomaMapBuilder setUpdateDateFromString(String date) throws ParseException {
        this.updateDate = new SimpleDateFormat("YYYY-MM-DD HH:mm").parse(date);
        return this;
    }

    public DomaMapBuilder setMapUrlFromLocalId(String baseUrl) {
        final String url = baseUrl + "map_images/" + localId + ".jpg";
        try {
            final URL testUrl = new URL(url);
            HttpURLConnection huc = (HttpURLConnection) testUrl.openConnection();
            huc.setRequestMethod("HEAD");
            int responseCode = huc.getResponseCode();
            if (responseCode == 200){
                this.mapUrl = url;
            }
            return this;
        } catch (IOException e) {
            return this;
        }
    }

    public DomaMap build(){
        return new DomaMap(name, domaUrl, mapUrl, localId, owner,date,updateDate);
    }
}
