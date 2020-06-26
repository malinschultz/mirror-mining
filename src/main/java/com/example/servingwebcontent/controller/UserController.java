package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.DatabaseConnection;
import com.jcraft.jsch.JSchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@Controller
public class UserController {
    @GetMapping("/user")
    public String user(Model model) throws JSchException, SQLException, IOException {
        // Get users from the DB.
        DatabaseConnection db = new DatabaseConnection();
        List<Map<String, Object>> users = db.getData("users");
        List<Integer> userList = new ArrayList<>();
        for (Map<String, Object> user : users) {
            int id = (Integer) user.get("id");
            userList.add(id);
        }
        Collections.sort(userList);

        List<String> usernames = new ArrayList<String>();
        for (int i: userList
             ) {
            usernames.add(String.valueOf(i));
        }

        model.addAttribute("userlist", usernames);

        List<Double> data = new ArrayList<Double>();
        data.add(0.781239); // Analyi
        data.add(1.0);
        data.add(0.702673);
        data.add(0.583166);
        data.add(0.587752);
        data.add(0.581369);
        data.add(0.760855);

        model.addAttribute("data", data);

        return "user";
    }
}
