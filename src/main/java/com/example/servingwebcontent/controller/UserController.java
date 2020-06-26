package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.DatabaseConnection;
import com.example.servingwebcontent.User;
import com.example.servingwebcontent.UserService;
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
        List<String> usernames = UserService.getUsernames();
        model.addAttribute("userlist", usernames);

        return "user";
    }
}
