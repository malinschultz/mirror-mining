package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.DatabaseConnection;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jcraft.jsch.JSchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UserDetailController {
    @GetMapping(value = "/userDetail")
    public String userDetail(@RequestParam(name = "userDetail", required = false, defaultValue = "Username")String name, Model model) throws JSchException, SQLException, IOException {
        model.addAttribute("username", name);

        // Get user from the DB and create lists from JSON columns.
        DatabaseConnection db = new DatabaseConnection();
        List<Map<String, Object>> users = db.getData("users");
        for (Map<String, Object> user : users) {
            if (user.get("id").toString().equals(name)) {
                List<Double> ctoneList = new ArrayList<>();
                JsonObject ctone = new Gson().fromJson(user.get("comment_tone").toString(), JsonObject.class);
                ctone.keySet().forEach(key -> {
                    Double value = Double.parseDouble(ctone.get(key).toString());
                    ctoneList.add(value);
                });

                List<Double> atoneList = new ArrayList<>();
                JsonObject atone = new Gson().fromJson(user.get("answer_tone").toString(), JsonObject.class);
                atone.keySet().forEach(key -> {
                    Double value = Double.parseDouble(atone.get(key).toString());
                    atoneList.add(value);
                });

                List<Double> persList = new ArrayList<>();
                JsonObject pers = new Gson().fromJson(user.get("personality").toString(), JsonObject.class);
                pers.keySet().forEach(key -> {
                    Double value = Double.parseDouble(pers.get(key).toString());
                    persList.add(value);
                });
                model.addAttribute("user_c", ctoneList);
                model.addAttribute("user_a", atoneList);
                model.addAttribute("user_pi", persList);
            }
        }
        return "userDetail";
    }
}
