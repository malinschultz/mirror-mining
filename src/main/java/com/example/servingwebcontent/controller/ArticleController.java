package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.Kategorie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/*Diese Klasse setzt die html Artikel Seite.
*
* Malin Schultz*/

@Controller
public class ArticleController {

    @PostMapping("/chooseKat")
    public String submissionResult(@ModelAttribute("KatForm") Kategorie kategorie) {
        return "article";
    }
}
