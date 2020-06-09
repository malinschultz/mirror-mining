package com.example.servingwebcontent;

import java.util.List;

public class KatForm {

    private String katName;
    private List<Article> katArt;

    public String getKatName() {
        return katName;
    }

    public void setKatName(String katName) {
        this.katName = katName;
    }

    public List<Article> getKatArt() {
        return katArt;
    }
}
