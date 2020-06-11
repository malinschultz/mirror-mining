package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.Article;
import com.example.servingwebcontent.Kategorie;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*Diese Klasse setzt die html Artikel Seite.
*
* Malin Schultz*/

@Controller
public class ArticleController {

   /*@RequestMapping("/chooseKat")
    public String submissionResult(@ModelAttribute("Kat") Kategorie kategorie, Model model) {

       return "article";
    }*/

    @GetMapping(value = "/chooseKatSel")
    /*TODO Übergabe funktioniert noch nicht */
    public String article(Kategorie chooseKatSel, Model model){
       String katName = chooseKatSel.getKatName();
        List<Article> katArt = chooseKatSel.getKatArt();
        model.addAttribute("katName", katName);
        model.addAttribute("katArt", katArt);
        return "/article";
    }


    /*@PostMapping(value = "/katID")
    public String showTab(@RequestParam Kategorie kat, Model model){
        model.addAttribute("Kat", kat);
        return "article";
    }*/

    /*@PostMapping(value="/add")
    public String addTable(@Valid final Kategorie kategorie, Model model){
        model.addAttribute("Kat", kategorie);
        return "article";
    }*/

}
