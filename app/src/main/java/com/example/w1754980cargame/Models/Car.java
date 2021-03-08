package com.example.w1754980cargame.Models;

public class Car {

    private long id;
    private String name;
    private String image;

    public Car(String name, String image) {
        this.image = image;
        this.name = name;
    }

    // Public methods

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
