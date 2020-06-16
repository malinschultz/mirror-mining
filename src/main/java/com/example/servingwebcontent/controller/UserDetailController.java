package com.example.servingwebcontent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserDetailController {
    @GetMapping("/userDetail")
    public String userDetail(@RequestParam(name = "username", required = false, defaultValue = "Username")String name, Model model){
        model.addAttribute("username", name);
        return "userDetail";
    }
}
