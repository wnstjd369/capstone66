package com.example.capstone3;

public class ImageUpload {
    private String date;
    private String url;

    public ImageUpload(){}
    public ImageUpload(String date, String url) {
        this.date = date;
        this.url = url;
    }

    public String getdate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}
