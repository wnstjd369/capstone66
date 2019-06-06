package com.example.capstone3;

public class ImageUpload {
    private String name;
    private String url;

    public ImageUpload(){}
    public ImageUpload(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
