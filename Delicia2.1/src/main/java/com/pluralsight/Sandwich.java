package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Sandwich extends Product {
    private String size;
    private String breadType;
    private List<Topping> toppings;
    private boolean toasted;

    public Sandwich(String size, String breadType, boolean toasted) {
        super("Sandwich", 0);
        this.size = size;
        this.breadType = breadType;
        this.toasted = toasted;
        this.toppings = new ArrayList<>();
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    @Override
    public double calculatePrice() {
        double basePrice;
        switch (size) {
            case "4":
                basePrice = 4.00;
                break;
            case "8":
                basePrice = 6.00;
                break;
            case "12":
                basePrice = 8.00;
                break;
            default:
                basePrice = 0;
                break;
        }

        for (Topping topping : toppings) {
            basePrice += topping.getExtraCost();
        }

        setPrice(basePrice);
        return basePrice;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBreadType() {
        return breadType;
    }

    public void setBreadType(String breadType) {
        this.breadType = breadType;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }
}
