package com.example.servingwebcontent;

import java.util.ArrayList;
import java.util.List;

/*das ist ein user, er besteht aus name und liste von Daten*/
public class User {
    private static String username;
    private static final List<Double> userData =new ArrayList<Double>();

    public User(){
        /**hier werden die Daten für die Charts übergeben
         * müssen als Double aus der DB gezogen werden**/
        userData.add(0.781239); //Analytical
        userData.add(1.0);      //Anger
        userData.add(0.702673); //Confident
        userData.add(0.583166); //Fear
        userData.add(0.587752); //Joy
        userData.add(0.581369); //Sadness
        userData.add(0.0);      //Tentative
    }

    public List<Double> getUserData() {
        return userData;
    }
}
