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

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class ArticleStartController {
    @GetMapping("/articleStart")
    public String articleStart(Model model) throws JSchException, SQLException, IOException {

        List<Kategorie> katList = KategorieService.getKat();
        model.addAttribute("katList", katList);

        List<Double> panCtone = KategorieService.getKatCtone("Panorama");
        model.addAttribute("panCtone", panCtone);

        List<Double> polCtone = KategorieService.getKatCtone("Politik");
        model.addAttribute("polCtone", polCtone);

        List<Double> spCtone = KategorieService.getKatCtone("Sport");
        model.addAttribute("spCtone", spCtone);

        List<Double> geCtone = KategorieService.getKatCtone("Gesundheit");
        model.addAttribute("geCtone", geCtone);

        List<Double> kuCtone = KategorieService.getKatCtone("Kultur");
        model.addAttribute("kuCtone", kuCtone);

        List<Double> neCtone = KategorieService.getKatCtone("Netzwelt");
        model.addAttribute("neCtone", neCtone);

        List<Double> wirCtone = KategorieService.getKatCtone("Wirtschaft");
        model.addAttribute("wirCtone", wirCtone);

        List<Double> wisCtone = KategorieService.getKatCtone("Wissenschaft");
        model.addAttribute("wisCtone", wisCtone);

        return "articleStart";
    }
}
