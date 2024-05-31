package com.pluralsight;

public class Chip extends Product {
    private String type;

    public Chip(String type, double price) {
        super("Chip", price);
        this.type = type;
    }

    @Override
    public double calculatePrice() {
        return getPrice();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
