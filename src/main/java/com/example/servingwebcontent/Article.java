package com.example.servingwebcontent;

/*Diese Klasse definiert, was ein Artikel ist.
*
* Malin Schultz*/

public class Article {

    private int id;
    private String url;
    private String title;
    private String category;
    private Object comment_tone;
    private Object answer_tone;

    public Article(int id, String url, String title, String category, Object ctone, Object atone) {
        this.id=id;
        this.url=url;
        this.title=title;
        this.category=category;
        this.comment_tone=ctone;
        this.answer_tone=atone;
    }

    public int getId() {
        return id;
    }

    public String getUrl() { return url; }

    public String getTitle() {
        return title;
    }

    public String getCategory() { return category; }

    public Object getCTone() { return comment_tone; }

    public Object getATone() {
        return answer_tone;
    }
}
