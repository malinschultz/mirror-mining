package com.example.servingwebcontent;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jcraft.jsch.JSchException;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import java.lang.Math;



/*Eine Kategorie ist eine Liste von Artikeln.
*
*Diese Klasse stellt eine Liste mit Artikeln zur Verfügung.
*Die Artikel gehören einer Kategorie an.
* Malin Schultz*/

@Repository
public class KategorieService {
    private static final List<Kategorie> kat= new ArrayList<>();
    private static final DatabaseConnection db = new DatabaseConnection();

    static {
        try {
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initData() throws JSchException, SQLException, IOException {
        /* Get categories and documents from the DB, create lists of articles
        and add categories to matching categories */
        DatabaseConnection db = new DatabaseConnection();
        List<Map<String, Object>> categories = db.executeQuery("select * from a_categories");
        List<Map<String, Object>> documents = db.executeQuery("select * from a_documents");
        List<String> tones = Arrays.asList("Anger", "Joy", "Sadness");

        List<Article> articleList = new ArrayList<>();
        for (Map<String, Object> document : documents) {
            List<Double> ctoneList = new ArrayList<>();
            JsonObject ctone = new Gson().fromJson(document.get("comment_tone").toString(), JsonObject.class);
            for (String tone : tones) {
                if (ctone.has(tone)) {
                    Double value = Double.parseDouble(ctone.get(tone).toString());
                    ctoneList.add(value);
                } else {
                    ctoneList.add(0.0);
                }
            }
            List<Double> atoneList = new ArrayList<>();
            JsonObject atone = new Gson().fromJson(document.get("answer_tone").toString(), JsonObject.class);
            for (String tone : tones) {
                if (atone.has(tone)) {
                    Double value = Double.parseDouble(atone.get(tone).toString());
                    atoneList.add(value);
                } else {
                    atoneList.add(0.0);
                }
            }
            List<Double> avgtoneList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                avgtoneList.add((double) Math.round(((ctoneList.get(i) + atoneList.get(i)) / 2) * 100d) / 100d);
            }
            Article doc = new Article((Integer) document.get("id"), document.get("url").toString(),
                    document.get("title").toString(), document.get("category").toString(),
                    avgtoneList.get(0), avgtoneList.get(1), avgtoneList.get(2));
            articleList.add(doc);
        }
        articleList.sort(Comparator.comparing(Article::getId).reversed());

        for (Map<String, Object> category : categories) {
            List<Article> cat = new ArrayList<>();
            for (Article doc : articleList) {
                if (doc.getCategory().equals(category.get("name").toString())) {
                    cat.add(doc);
                }
            }
            Kategorie c = new Kategorie(cat, category.get("name").toString());
            kat.add(c);
            kat.sort(Comparator.comparing(Kategorie::getKatName));
        }
    }

    public static List<Kategorie> getKat() {
        return kat;
    }

    public static Kategorie getKatByName(String name){
        for (Kategorie k: kat) {
            if (k.getKatName().equals(name)){
                int i = kat.indexOf(k);
                return kat.get(i);
            }
        }
        return null;
    }

    public static List<Double> getKatCtone(String katName) throws JSchException, SQLException, IOException {
        // Get category from the DB and create lists from JSON columns.
        List<Map<String, Object>> category = db.executeQuery("select comment_tone, answer_tone from a_categories c where c.name = " + "'" + katName + "'");

        List<Double> ctoneList = new ArrayList<>();
        JsonObject ctone = new Gson().fromJson(category.get(0).get("comment_tone").toString(), JsonObject.class);
        List<String> tones = Arrays.asList("Analytical", "Anger", "Confident", "Fear", "Joy", "Sadness", "Tentative");
        for (String tone : tones) {
            if (ctone.has(tone)) {
                Double value = Double.parseDouble(ctone.get(tone).toString());
                ctoneList.add(value);
            } else {
                ctoneList.add(0.0);
            }
        }
        return ctoneList;
    }

    public static List<Double> getKatAtone(String katName) throws JSchException, SQLException, IOException {
        DatabaseConnection db = new DatabaseConnection();
        List<Map<String, Object>> category = db.executeQuery("select comment_tone, answer_tone from a_categories c where c.name = " + "'" + katName + "'");
        List<String> tones = Arrays.asList("Analytical", "Anger", "Confident", "Fear", "Joy", "Sadness", "Tentative");

        List<Double> atoneList = new ArrayList<>();
        JsonObject atone = new Gson().fromJson(category.get(0).get("answer_tone").toString(), JsonObject.class);
        for (String tone : tones) {
            if (atone.has(tone)) {
                Double value = Double.parseDouble(atone.get(tone).toString());
                atoneList.add(value);
            } else {
                atoneList.add(0.0);
            }
        }
        return atoneList;
    }
}
