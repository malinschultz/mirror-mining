package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.Article;
import com.example.servingwebcontent.KatForm;
import com.example.servingwebcontent.Kategorie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*Diese Klasse setzt die html Artikel Seite.
*
* Malin Schultz*/

@Controller
public class ArticleController {

   @RequestMapping("/chooseKat")
    public String submissionResult(@ModelAttribute("Kat") Kategorie kategorie, Model model) {

       return "article";
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
