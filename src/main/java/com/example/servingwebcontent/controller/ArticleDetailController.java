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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/*Diese Klasse setzt die articleDetail.html Seite
 *
 * Malin Schultz*/
@Controller
public class ArticleDetailController {
    @GetMapping("/articleDetail")
    public String articleDetail(@RequestParam(name = "articleDetail") String id, Model model) throws JSchException, SQLException, IOException {
        model.addAttribute("articleId", id);

        // Get user from the DB and create lists from JSON columns.
        DatabaseConnection db = new DatabaseConnection();
        List<Map<String, Object>> article = db.executeQuery("select * from a_documents where id = " + id);
        List<Double> ctoneList = new ArrayList<>();
        JsonObject ctone = new Gson().fromJson(article.get(0).get("comment_tone").toString(), JsonObject.class);
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

        List<Double> atoneList = new ArrayList<>();
        JsonObject atone = new Gson().fromJson(article.get(0).get("answer_tone").toString(), JsonObject.class);
        for (String tone : tones) {
            if (atone.has(tone)) {
                Double value = Double.parseDouble(atone.get(tone).toString());
                atoneList.add(value);
            }
            else {
                atoneList.add(0.0);
            }
        }
        model.addAttribute("atone", atoneList);


        // Get doc title, doc category and doc comments from the DB.
        List<Map<String, Object>> articleTitle = db.executeQuery("select title from a_documents where id = " + id);
        model.addAttribute("articleTitle", articleTitle.get(0).get("title").toString());

        List<Map<String, Object>> articleCategory = db.executeQuery("select category from a_documents where id = " + id);
        model.addAttribute("articleCategory", articleCategory.get(0).get("category").toString());

        List<Map<String, Object>> category = db.executeQuery("select comment_tone, answer_tone from a_categories c where c.name = " + "'" + articleCategory.get(0).get("category").toString() + "'");
        List<Double> av_ctoneList = new ArrayList<>();
        JsonObject av_ctone = new Gson().fromJson(category.get(0).get("comment_tone").toString(), JsonObject.class);
        for (String tone : tones) {
            if (av_ctone.has(tone)) {
                Double value = Double.parseDouble(av_ctone.get(tone).toString());
                av_ctoneList.add(value);
            } else {
                av_ctoneList.add(0.0);
            }
        }
        model.addAttribute("av_ctone", av_ctoneList);

        List<Double> av_atoneList = new ArrayList<>();
        JsonObject av_atone = new Gson().fromJson(category.get(0).get("answer_tone").toString(), JsonObject.class);
        for (String tone : tones) {
            if (av_atone.has(tone)) {
                Double value = Double.parseDouble(av_atone.get(tone).toString());
                av_atoneList.add(value);
            } else {
                av_atoneList.add(0.0);
            }
        }
        model.addAttribute("av_atone", av_atoneList);

        List<Map<String, Object>> comments = db.executeQuery("select id, user_id, text from a_comments where doc_id = " + id + "order by id asc");
        List<String> commentList = new ArrayList<>();
        for (Map<String, Object> comment : comments) {
            commentList.add(comment.get("text").toString());
        }
        model.addAttribute("articleComments", commentList);
        return "articleDetail";
    }
}