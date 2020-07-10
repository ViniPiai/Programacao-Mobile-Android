package com.univali.programacao.jurneyapp.model;

import android.graphics.Bitmap;

public class Place {

    private String name;
    private String description;
    private String imageUrl;
    private Bitmap imageBitmap;

    public Place(String name, String description, String imageUrl, Bitmap imageBitmap) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.imageBitmap = imageBitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                '}';
    }
}
