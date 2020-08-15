package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.Kategorie;
import com.example.servingwebcontent.KategorieService;
import com.jcraft.jsch.JSchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/*Diese Klasse liefert die Artikel Start Seite, auf der alle Kategorien
  miteinander verglichen werden.
  Hier werden das Drop-down-Menü und die Grafik mit Werten gefüllt.

  von Malin Schultz
 */

@Controller
public class ArticleStartController {

    //get request für articleStart.html
    @GetMapping("/articleStart")
    public String articleStart(Model model) throws JSchException, SQLException, IOException {

        //Liste mit Kategorien fürs Drop-down-Menu
        List<Kategorie> katList = KategorieService.getKat();

        //alle notwenigen Zahlenwerte in Listen für die Grafik
        List<Double> geCtone = KategorieService.getKatCtone("Gesundheit");
        List<Double> kuCtone = KategorieService.getKatCtone("Kultur");
        List<Double> neCtone = KategorieService.getKatCtone("Netzwelt");
        List<Double> panCtone = KategorieService.getKatCtone("Panorama");
        List<Double> polCtone = KategorieService.getKatCtone("Politik");
        List<Double> spCtone = KategorieService.getKatCtone("Sport");
        List<Double> wirCtone = KategorieService.getKatCtone("Wirtschaft");
        List<Double> wisCtone = KategorieService.getKatCtone("Wissenschaft");

        //Weitergabe an das View, nur hiermit kann die Seite angezeigt werden
        model.addAttribute("katList", katList);
        model.addAttribute("geCtone", geCtone);
        model.addAttribute("kuCtone", kuCtone);
        model.addAttribute("neCtone", neCtone);
        model.addAttribute("panCtone", panCtone);
        model.addAttribute("polCtone", polCtone);
        model.addAttribute("spCtone", spCtone);
        model.addAttribute("wirCtone", wirCtone);
        model.addAttribute("wisCtone", wisCtone);

        return "articleStart";
    }
}
