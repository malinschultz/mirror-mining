package com.example.servingwebcontent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @GetMapping("/user")
    public String user(Model model){
        List<String> user = new ArrayList<>();
        user.add("Toma Te");
        user.add("Sell Erie");
        user.add("Peter Silie");
        user.add("Kar Toffel");
        user.add("Rühr Ei");
        user.add("Nudel Suppe");
        model.addAttribute("userlist", user);
        return "user";
    }
}
