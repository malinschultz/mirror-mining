package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/*Diese Klasse setzt die articleDetail.html Seite
*
* Malin Schultz*/
@Controller
public class ArticleDetailController {

    @GetMapping("/articleDetail")
    public String articleDetail(@RequestParam(name = "articleName") String articleName, Model model){

        model.addAttribute("articleName", articleName);

        /*TODO Hier können jetzt echte Daten eingefügt werden, mit der id des Artikels als String.*/
        //Article article = getArticleByName(articleName);
        //String articleTitle = article.getTitle();
        String articleTitle = "Titel des Artikels";
        //URL articleURL = article.getURL();
        //String articleCategory = article.getCategory();
        String articleCategory = "Gesundheit";

        List<String> comments = new ArrayList<String>();
        comments.add("ich finds blöd.");
        comments.add("also meiner meinung nach ist das ja so, dass und überhaupt.");
        comments.add("nee der hat ganz Recht.");
        comments.add("also, is ja so.");

        //String topUser = article.getTopUser();
        String topUser = "195";

        model.addAttribute("topUser", topUser);
        model.addAttribute("articleComments", comments);
        model.addAttribute("articleTitle", articleTitle);
        model.addAttribute("articleCategory", articleCategory);
        return "articleDetail";
    }
}
