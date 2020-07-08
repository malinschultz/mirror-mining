package com.example.servingwebcontent;

/*Diese Klasse definiert, was ein User ist.
 *
 * Jan Willruth*/

public class User {

    private String name;
    private Double anger;
    private Double joy;
    private Double sadness;

    public User() {

    }

    public User(String name, Double anger, Double joy, Double sadness) {
        this.name = name;
        this.anger = anger;
        this.joy = joy;
        this.sadness = sadness;
    }

    public String getName() { return name; }

    public Double getAnger() { return anger; }

    public Double getJoy() { return joy; }

    public Double getSadness() { return sadness; }
}
