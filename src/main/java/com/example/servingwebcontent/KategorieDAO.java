package com.example.servingwebcontent;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


/*Eine Kategorie ist eine Liste von Artikeln.
*
*Diese Klasse stellt eine Liste mit Artikeln zur Verfügung.
*Die Artikel gehören einer Kategorie an.
* Malin Schultz*/

@Repository
public class KategorieDAO {

    private static final List<Kategorie> kat=new ArrayList<Kategorie>();
    private static final Kategorie kat0 = new Kategorie(null, "zero");


    static {
        initData();
    }

    private static void initData(){
        Article eins = new Article(01,"erster Titel", 0.345, "linkzumoriginal.de");
        Article zwei = new Article(02,"zweiter Titel", 0.567, "linkwoandershin.de");
        Article drei = new Article(03,"dritter Titel", 0.345, "linkzumoriginal.de");
        Article vier = new Article(04,"vierter Titel", 0.567, "linkwoandershin.de");

        List<Article> kat1 = new ArrayList<Article>();
        List<Article> kat2 = new ArrayList<Article>();

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
