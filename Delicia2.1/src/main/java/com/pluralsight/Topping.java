package com.pluralsight;

public class Topping {
    private String name;
    private String type;
    private double extraCost;

    public Topping(String name, String type, double extraCost) {
        this.name = name;
        this.type = type;
        this.extraCost = extraCost;
    }

    public String getName() {
        return name;
    }

    public double getExtraCost() {
        return extraCost;
    }

}
