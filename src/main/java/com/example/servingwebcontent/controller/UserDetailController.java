package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.DatabaseConnection_local;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class UserDetailController {
    @GetMapping(value = "/userDetail")
    public String userDetail(@RequestParam(name = "userDetail", required = false, defaultValue = "userid") String id, Model model) throws JSchException, SQLException, IOException {
        model.addAttribute("userid", id);
        model.addAttribute("username", String.valueOf(id));

        /* Get user from the DB, create lists of (average) comment/answer tones and personality insights
        from JSON columns, get their comments and add them to the model for chart display */
        DatabaseConnection_local db = new DatabaseConnection_local();
        List<Map<String, Object>> user = db.executeQuery("select * from a_users where id = " + id);
        List<Map<String, Object>> user_avg = db.executeQuery("select * from a_averages where name = 'Average TP Users'");

        List<Double> ctoneList = new ArrayList<>();
        JsonObject ctone = new Gson().fromJson(user.get(0).get("comment_tone").toString(), JsonObject.class);
        List<String> tones = Arrays.asList("Analytical", "Anger", "Confident", "Fear", "Joy", "Sadness", "Tentative");
        for (String tone : tones) {
            if (ctone.has(tone)) {
                Double value = Double.parseDouble(ctone.get(tone).toString());
                ctoneList.add(value);
            }
            else {
                ctoneList.add(0.0);
            }
        }
        model.addAttribute("ctone", ctoneList);

        List<Double> avg_ctoneList = new ArrayList<>();
        JsonObject avg_ctone = new Gson().fromJson(user_avg.get(0).get("comment_tone").toString(), JsonObject.class);
        for (String tone : tones) {
            if (avg_ctone.has(tone)) {
                Double value = Double.parseDouble(avg_ctone.get(tone).toString());
                avg_ctoneList.add(value);
            }
            else {
                avg_ctoneList.add(0.0);
            }
        }
        model.addAttribute("avg_ctone", avg_ctoneList);

        List<Double> atoneList = new ArrayList<>();
        JsonObject atone = new Gson().fromJson(user.get(0).get("answer_tone").toString(), JsonObject.class);
        for (String tone : tones) {
            if (atone.has(tone)) {
                Double value = Double.parseDouble(atone.get(tone).toString());
                atoneList.add(value);
            }
            else {
                atoneList.add(0.0);
            }
        }
        model.addAttribute("atone", atoneList);

        List<Double> avg_atoneList = new ArrayList<>();
        JsonObject avg_atone = new Gson().fromJson(user_avg.get(0).get("answer_tone").toString(), JsonObject.class);
        for (String tone : tones) {
            if (avg_atone.has(tone)) {
                Double value = Double.parseDouble(avg_atone.get(tone).toString());
                avg_atoneList.add(value);
            }
            else {
                avg_atoneList.add(0.0);
            }
        }
        model.addAttribute("avg_atone", avg_atoneList);

        List<Double> piList = new ArrayList<>();
        JsonObject pi = new Gson().fromJson(user.get(0).get("personality").toString(), JsonObject.class);
        pi.keySet().forEach(key -> {
            Double value = Double.parseDouble(pi.get(key).toString());
            piList.add(value);
        });
        model.addAttribute("pi", piList);

        List<Double> avg_piList = new ArrayList<>();
        JsonObject avg_pi = new Gson().fromJson(user_avg.get(0).get("personality").toString(), JsonObject.class);
        pi.keySet().forEach(key -> {
            Double value = Double.parseDouble(avg_pi.get(key).toString());
            avg_piList.add(value);
        });
        model.addAttribute("avg_pi", avg_piList);

        List<Map<String, Object>> comments = db.executeQuery("select text, tone " +
                "from a_comments where user_id = " + id + "order by id asc");
        List<String> ajs = Arrays.asList("Anger", "Joy", "Sadness");
        List<List<String>> commentList = new ArrayList<>();
        for (Map<String, Object> comment : comments) {
            List<String> com = new ArrayList<>();
            com.add(comment.get("text").toString());
            JsonObject ttone = new Gson().fromJson(comment.get("tone").toString(), JsonObject.class);
            for (String t : ajs) {
                if (ttone.has(t)) {
                    double value = Math.round(Double.parseDouble(ttone.get(t).toString()) * 100d) / 100d;
                    com.add(Double.toString(value));
                } else {
                    com.add("0.0");
                }
            }
            commentList.add(com);
        }
        model.addAttribute("commentList", commentList);
        return "userDetail";
    }
}
