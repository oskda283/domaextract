package se.daniels.domaextract.domain;

import java.util.Date;

public class DomaRssEntry {

    public final String url;

    public final String title;

    public final Date date;

    public final String description;


    public DomaRssEntry(String url, String title, Date date, String description) {
        this.url = url;
        this.title = title;
        this.date = date;
        this.description = description;
    }
}
