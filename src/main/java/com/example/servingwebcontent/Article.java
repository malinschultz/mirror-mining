package com.example.servingwebcontent;

/*Diese Klasse definiert, was ein Artikel ist.
*
* Malin Schultz*/

import java.net.MalformedURLException;
import java.net.URL;

public class Article {

    private int id;
    private URL url;
    private String title;
    private String category;
    private Double anger;
    private Double joy;
    private Double sadness;

    public Article() {

    }

    public Article(int id, String url, String title, String category, Double anger, Double joy, Double sadness) throws MalformedURLException {
        this.id=id;
        this.url= new URL(url);
        this.title=title;
        this.category=category;
        this.anger=anger;
        this.joy=joy;
        this.sadness=sadness;
    }

    public int getId() { return id; }

    public String getIdAsString(){
        return String.valueOf(id);
    }

    public URL getUrl() { return url; }

    public String getTitle() { return title; }

    public String getCategory() { return category; }

    public Double getAnger() { return anger; }

    public Double getJoy() { return joy; }

    public Double getSadness() { return sadness; }
}
