package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.Article;
import com.example.servingwebcontent.Kategorie;
import com.example.servingwebcontent.KategorieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

        return "article";
    }
}
