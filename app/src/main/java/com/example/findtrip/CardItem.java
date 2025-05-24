package com.example.findtrip;

public class CardItem {
    private String imageUrl;
    private String title;
    private String price;

    public CardItem(String imageUrl, String title, String price) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }
}
