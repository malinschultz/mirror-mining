package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.Kategorie;
import com.example.servingwebcontent.KategorieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChooseKatController {

    @GetMapping("/chooseKat")
    public String chooseKat(Model model){
        List<Kategorie> katList = KategorieService.getKat();
        model.addAttribute("katList", katList);
        return "chooseKat";
    }

}
