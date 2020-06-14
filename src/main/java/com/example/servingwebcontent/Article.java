package com.example.servingwebcontent;

/*Diese Klasse definiert, was ein Artikel ist.
*
* Malin Schultz*/

public class Article {

    private int id;
    private String title;
    private Object tone;
    private String link;

    public Article(int aid, String atitle, Object atone, String alink){
        this.id=aid;
        this.title=atitle;
        this.tone=atone;
        this.link=alink;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Object getTone() {
        return tone;
    }

    public String getLink() {
        return link;
    }


}
