package com.example.servingwebcontent;

import com.jcraft.jsch.JSchException;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/*Eine Kategorie ist eine Liste von Artikeln.
*
*Diese Klasse stellt eine Liste mit Artikeln zur Verfügung.
*Die Artikel gehören einer Kategorie an.
* Malin Schultz*/

@Repository
public class KategorieService {

    private static final List<Kategorie> kat= new ArrayList<>();
    private static final Kategorie kat0 = new Kategorie(null, "zero");


    static {
        try {
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initData() throws JSchException, SQLException, IOException {
        // Get articles from the DB.
        DatabaseConnection db = new DatabaseConnection();
        List<Map<String, Object>> documents = db.getData("documents");

        Article doc1 = new Article((Integer) documents.get(0).get("id"), documents.get(0).get("title").toString(),
                documents.get(0).get("comment_tone"), documents.get(0).get("url").toString());
        Article doc2 = new Article((Integer) documents.get(1).get("id"), documents.get(1).get("title").toString(),
                documents.get(1).get("comment_tone"), documents.get(1).get("url").toString());
        Article doc3 = new Article((Integer) documents.get(2).get("id"), documents.get(2).get("title").toString(),
                documents.get(2).get("comment_tone"), documents.get(2).get("url").toString());
        Article doc4 = new Article((Integer) documents.get(3).get("id"), documents.get(3).get("title").toString(),
                documents.get(3).get("comment_tone"), documents.get(3).get("url").toString());
        Article doc5 = new Article(111, "Irgendein Titel", "0.123", "linkzumartikel.de");
        Article doc6 = new Article(222, "Irgendein Titel", "0.456", "linkzumartikel.de");
        Article doc7 = new Article(333, "Irgendein Titel", "0.789", "linkzumartikel.de");
        Article doc8 = new Article(444, "Irgendein Titel", "0.666", "linkzumartikel.de");

        List<Article> kat1 = new ArrayList<>();
        List<Article> kat2 = new ArrayList<>();

        kat1.add(doc1);
        kat1.add(doc2);
        kat2.add(doc3);
        kat2.add(doc4);
        kat1.add(doc5);
        kat1.add(doc6);
        kat2.add(doc7);
        kat2.add(doc8);

        Kategorie langweilig = new Kategorie(kat1, "langweilig");
        Kategorie spannend = new Kategorie(kat2, "spannend");

        kat.add(langweilig);
        kat.add(spannend);
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
        return kat0;
    }
}
