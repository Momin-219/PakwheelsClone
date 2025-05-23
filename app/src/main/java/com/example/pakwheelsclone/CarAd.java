package com.example.pakwheelsclone;

public class CarAd {
    private String title;
    private String price;
    private String imageUrl;

    public CarAd() {
        // Default constructor required for Firestore
    }

    public CarAd(String title, String price, String imageUrl) {
        this.title = title;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getTitle() { return title; }
    public String getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }
}