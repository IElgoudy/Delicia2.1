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

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getExtraCost() {
        return extraCost;
    }

    public void setExtraCost(double extraCost) {
        this.extraCost = extraCost;
    }
}
