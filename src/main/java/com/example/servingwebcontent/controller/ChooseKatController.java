package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.Kategorie;
import com.example.servingwebcontent.KategorieDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChooseKatController {
    /*@RequestMapping(value = { "/chooseKat" }, method = RequestMethod.GET)
    public String selectOptionExample1Page(Model model) {

        KatForm form = new KatForm();
        model.addAttribute("KatForm", form);

        List<Kategorie> list = KategorieDAO.getKat();
        model.addAttribute("kategorien", list);

        return "chooseKat";
    }*/

    @GetMapping("/chooseKat")
    public String chooseKat(Model model){
        List<Kategorie> katList = KategorieDAO.getKat();
        model.addAttribute("katList", katList);
        return "chooseKat";
    }

}
