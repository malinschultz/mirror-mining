package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.Article;
import com.example.servingwebcontent.Kategorie;
import com.example.servingwebcontent.KategorieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.*;

/*Diese Klasse setzt die html Artikel Seite.
*
* Malin Schultz*/

@Controller
public class ArticleController {

    @GetMapping(value = "/chooseKatSel")
    public String article(@RequestParam(name = "chooseKatSel") String katName, Model model){
        List<Kategorie> katList = KategorieService.getKat();
        model.addAttribute("katList", katList);

        Kategorie kat = KategorieService.getKatByName(katName);
        List<Article> katArt = kat.getKatArt();

        model.addAttribute("katName", katName);
        model.addAttribute("katArt", katArt);

        return "article";
    }

    @GetMapping("/article")
    public String article(Model model){
        List<Kategorie> katList = KategorieService.getKat();
        model.addAttribute("katList", katList);

        String katName = "";
        List<Article> katArt = new ArrayList<Article>();
        Article art = new Article();
        katArt.add(art);

        model.addAttribute("katName", katName);
        model.addAttribute("katArt", katArt);

        /*
         * Im Folgenden sollen die Daten aus der Datenbank gezogen werden.
         * Diese werden schon erfolgreich in die richtige Chart implementiert.
         * Dies sind die Average Comment Tones. */
        List<Double> av_com_tone = new ArrayList<Double>();
        av_com_tone.add(0.53495);  // Analytical
        av_com_tone.add(0.89323);  // Anger
        av_com_tone.add(0.69058);  // Confident
        av_com_tone.add(0.54758);  // Fear
        av_com_tone.add(0.31234);  // Joy
        av_com_tone.add(0.781923); // Sadness
        av_com_tone.add(0.82012);  // Tentative

        model.addAttribute("av_com_tone", av_com_tone);


        /* Dies sind die Comment Tones für den ausgewählten Artikel.
         *  muss noch an die richtige Stelle integriert werden. */
        List<Double> com_tone = new ArrayList<Double>();
        com_tone.add(0.781239);  // Analytical
        com_tone.add(0.0);       // Anger
        com_tone.add(0.702673);  // Confident
        com_tone.add(0.583166);  // Fear
        com_tone.add(0.587752);  // Joy
        com_tone.add(0.581369);  // Sadness
        com_tone.add(0.760855);  // Tentative

        model.addAttribute("com_tone", com_tone);

        return "article";
    }
}
