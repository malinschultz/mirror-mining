package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserDetailController {
    @GetMapping(value = "/userDetail")
    public String userDetail(@RequestParam(name = "userDetail", required = false, defaultValue = "Username")String name, Model model){
        model.addAttribute("username", name);

        /*Hier werden die Daten für die Charts vom user an das Template übergeben
         * TODO userData passend zu userName */
        User person = new User();
        List<Double> data = person.getUserData();
        model.addAttribute("data", data);

        return "userDetail";
    }
}
