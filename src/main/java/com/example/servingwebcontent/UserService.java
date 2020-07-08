package com.example.servingwebcontent;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jcraft.jsch.JSchException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/*der UserService zieht die Daten aus der DB*/
public class UserService {
    private static final List<User> usrList = new ArrayList<>();

    static {
        try {
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initData() throws JSchException, SQLException, IOException {
        // Get users from the DB.
        DatabaseConnection db = new DatabaseConnection();
        List<Map<String, Object>> users = db.executeQuery("select id, comment_tone, answer_tone from a_users");
        List<String> tones = Arrays.asList("Anger", "Joy", "Sadness");

        for (Map<String, Object> user : users) {
            List<Double> ctoneList = new ArrayList<>();
            JsonObject ctone = new Gson().fromJson(user.get("comment_tone").toString(), JsonObject.class);
            for (String tone : tones) {
                if (ctone.has(tone)) {
                    Double value = Double.parseDouble(ctone.get(tone).toString());
                    ctoneList.add(value);
                } else {
                    ctoneList.add(0.0);
                }
            }
            List<Double> atoneList = new ArrayList<>();
            JsonObject atone = new Gson().fromJson(user.get("answer_tone").toString(), JsonObject.class);
            for (String tone : tones) {
                if (atone.has(tone)) {
                    Double value = Double.parseDouble(atone.get(tone).toString());
                    atoneList.add(value);
                } else {
                    atoneList.add(0.0);
                }
            }
            List<Double> avgtoneList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                avgtoneList.add((double) Math.round(((ctoneList.get(i) + atoneList.get(i)) / 2) * 100d) / 100d);
            }
            Double anger = avgtoneList.get(0);
            Double joy = avgtoneList.get(1);
            Double sadness = avgtoneList.get(2);

            User usr = new User(user.get("id").toString(), anger, joy, sadness);
            usrList.add(usr);
        }
        usrList.sort(Comparator.comparing(User::getName));
    }

    public static List<User> getUsers() {
        return usrList;
    }
}



