package com.example.servingwebcontent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserDetailController {
    @GetMapping(value = "/userDetail")
    public String userDetail(@RequestParam(name = "userDetail", required = false, defaultValue = "Username")String name, Model model){
        model.addAttribute("username", name);

        List<Double> user_c = new ArrayList<Double>();
        user_c.add(0.781239); // Analytical
        user_c.add(1.0);      // Anger
        user_c.add(0.702673); // Confident
        user_c.add(0.583166); // Fear
        user_c.add(0.587752); // Joy
        user_c.add(0.581369); // Sadness
        user_c.add(0.760855); // Tentative

        model.addAttribute("user_c", user_c);

        List<Double> user_c_av = new ArrayList<Double>();
        user_c_av.add(0.53495);  // Analytical
        user_c_av.add(0.89323);  // Anger
        user_c_av.add(0.69058);  // Confident
        user_c_av.add(0.54758);  // Fear
        user_c_av.add(0.31234);  // Joy
        user_c_av.add(0.781923); // Sadness
        user_c_av.add(0.82012);  // Tentative

        model.addAttribute("user_c_av", user_c_av);

        List<Double> user_pi = new ArrayList<Double>();
        user_pi.add(0.54245); // Openness
        user_pi.add(0.0);     // Conscientiousness
        user_pi.add(0.45234); // Extraversion
        user_pi.add(0.89712); // Agreeableness
        user_pi.add(0.67323); // Neuroticism

        model.addAttribute("user_pi", user_pi);

        List<Double> user_pi_av = new ArrayList<Double>();
        user_pi_av.add(0.79823); // Openness
        user_pi_av.add(0.21345); // Conscientiousness
        user_pi_av.add(0.92300); // Extraversion
        user_pi_av.add(0.31234); // Agreeableness
        user_pi_av.add(0.45632); // Neuroticism

        model.addAttribute("user_pi_av", user_pi_av);
        return "userDetail";
    }
}
