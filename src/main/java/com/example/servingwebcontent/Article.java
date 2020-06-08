package com.example.servingwebcontent;

/*Diese Klasse definiert, was ein Artikel ist.
*
* Malin Schultz*/

public class Article {

    private long id;
    private String title;
    private double tone;
    private String link;

    public Article(long aid, String atitle, double atone, String alink){
        this.id=aid;
        this.title=atitle;
        this.tone=atone;
        this.link=alink;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getTone() {
        return tone;
    }

    public String getLink() {
        return link;
    }


}
