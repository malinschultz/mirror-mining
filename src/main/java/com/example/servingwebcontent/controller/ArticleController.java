package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.Article;
import com.example.servingwebcontent.DatabaseConnection;
import com.example.servingwebcontent.Kategorie;
import com.example.servingwebcontent.KategorieService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jcraft.jsch.JSchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/*Diese Klasse setzt die html Artikel Seite.
*
* Malin Schultz*/

@Controller
public class ArticleController {
    @GetMapping(value = "/chooseKatSel")
    public String article(@RequestParam(name = "chooseKatSel") String katName, Model model) throws JSchException, SQLException, IOException {
        List<Kategorie> katList = KategorieService.getKat();
        Kategorie kat = KategorieService.getKatByName(katName);
        assert kat != null;
        List<Article> katArt = kat.getKatArt();
        model.addAttribute("katList", katList);
        model.addAttribute("katName", katName);
        model.addAttribute("katArt", katArt);

        // Get category from the DB and create lists from JSON columns.
        DatabaseConnection db = new DatabaseConnection();
        List<Map<String, Object>> category = db.executeQuery("select comment_tone, answer_tone from a_categories c where c.name = " + "'" + katName + "'");
        List<Map<String, Object>> cat_avg = db.executeQuery("select * from a_averages where name = 'Average Tone Overall'");
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
        model.addAttribute("ctone", ctoneList);

        List<Double> avg_ctoneList = new ArrayList<>();
        JsonObject avg_ctone = new Gson().fromJson(cat_avg.get(0).get("comment_tone").toString(), JsonObject.class);
        for (String tone : tones) {
            if (avg_ctone.has(tone)) {
                Double value = Double.parseDouble(avg_ctone.get(tone).toString());
                avg_ctoneList.add(value);
            }
            else {
                avg_ctoneList.add(0.0);
            }
        }
        model.addAttribute("avg_ctone", avg_ctoneList);

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
        model.addAttribute("atone", atoneList);

        List<Double> avg_atoneList = new ArrayList<>();
        JsonObject avg_atone = new Gson().fromJson(cat_avg.get(0).get("answer_tone").toString(), JsonObject.class);
        for (String tone : tones) {
            if (avg_atone.has(tone)) {
                Double value = Double.parseDouble(avg_atone.get(tone).toString());
                avg_atoneList.add(value);
            }
            else {
                avg_atoneList.add(0.0);
            }
        }
        model.addAttribute("avg_atone", avg_atoneList);
        return "article";
    }
    /* @GetMapping("/article")
    public String article(Model model) {
        List<Kategorie> katList = KategorieService.getKat();
        model.addAttribute("katList", katList);

        String katName = "wähle hier eine Kategorie aus";
        List<Article> katArt = new ArrayList<>();
        Article art = new Article();
        katArt.add(art);

        model.addAttribute("katName", katName);
        model.addAttribute("katArt", katArt);
        return "article";
    } */
}
