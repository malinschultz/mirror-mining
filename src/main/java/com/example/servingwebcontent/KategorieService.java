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

        Article eins = new Article((Integer) documents.get(0).get("id"), documents.get(0).get("title").toString(),
                documents.get(0).get("comment_tone"), documents.get(0).get("url").toString());
        Article zwei = new Article((Integer) documents.get(1).get("id"), documents.get(1).get("title").toString(),
                documents.get(1).get("comment_tone"), documents.get(1).get("url").toString());
        Article drei = new Article((Integer) documents.get(2).get("id"), documents.get(2).get("title").toString(),
                documents.get(2).get("comment_tone"), documents.get(2).get("url").toString());
        Article vier = new Article((Integer) documents.get(3).get("id"), documents.get(3).get("title").toString(),
                documents.get(3).get("comment_tone"), documents.get(3).get("url").toString());

        List<Article> kat1 = new ArrayList<>();
        List<Article> kat2 = new ArrayList<>();

        kat1.add(eins);
        kat1.add(zwei);
        kat2.add(drei);
        kat2.add(vier);

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
