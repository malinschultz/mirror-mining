package com.example.servingwebcontent;

import java.util.List;

public class Kategorie {

    private String katName;
    private List<Article> katArt;

    public Kategorie() {

    }

    public Kategorie(List<Article> katArt, String katName) {
        super();
        this.katArt = katArt;
        this.katName = katName;
    }


    public String getKatName() {
        return katName;
    }

    public void setKatName(String katName) {
        this.katName = katName;
    }

    public List<Article> getKatArt() {
        return katArt;
    }

    public void setKatArt(List<Article> katArt) {
        this.katArt = katArt;
    }

}
