package com.ritikgarg12.foodorderapp.Models;

public class MainModel {

    int image;
    String name,prize,description;

    public MainModel(int image, String name, String prize, String description) {
        this.image = image;
        this.name = name;
        this.prize = prize;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
