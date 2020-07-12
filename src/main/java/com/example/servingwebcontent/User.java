package com.example.servingwebcontent;

/*Diese Klasse definiert, was ein User ist.
 *
 * Jan Willruth*/

public class User {

    private int name;
    private Double anger;
    private Double joy;
    private Double sadness;

    public User() {

    }

    public User(int name, Double anger, Double joy, Double sadness) {
        this.name = name;
        this.anger = anger;
        this.joy = joy;
        this.sadness = sadness;
    }

    public int getName() { return name; }

    public Double getAnger() { return anger; }

    public Double getJoy() { return joy; }

    public Double getSadness() { return sadness; }
}
