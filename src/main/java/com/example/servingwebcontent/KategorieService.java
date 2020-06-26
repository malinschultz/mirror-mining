package com.example.servingwebcontent;

import com.jcraft.jsch.JSchException;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;



/*Eine Kategorie ist eine Liste von Artikeln.
*
*Diese Klasse stellt eine Liste mit Artikeln zur Verfügung.
*Die Artikel gehören einer Kategorie an.
* Malin Schultz*/

@Repository
public class KategorieService {
    private static final List<Kategorie> kat= new ArrayList<>();

    static {
        try {
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initData() throws JSchException, SQLException, IOException {
        // Get categories and documents from the DB.
        DatabaseConnection db = new DatabaseConnection();
        List<Map<String, Object>> categories = db.getData("categories");
        List<Map<String, Object>> documents = db.getData("documents");

        List<Article> articleList = new ArrayList<>();
        for (Map<String, Object> document : documents) {
            Article doc = new Article((Integer) document.get("id"), document.get("url").toString(),
                    document.get("title").toString(), document.get("category").toString(),
                    document.get("comment_tone"), document.get("answer_tone"));
            articleList.add(doc);
        }
        articleList.sort(Comparator.comparing(Article::getId));

        for (Map<String, Object> category : categories) {
            List<Article> cat = new ArrayList<>();
            for (Article doc : articleList) {
                if (doc.getCategory().equals(category.get("name").toString())) {
                    cat.add(doc);
                }
            }
            Kategorie c = new Kategorie(cat, category.get("name").toString());
            kat.add(c);
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
}
