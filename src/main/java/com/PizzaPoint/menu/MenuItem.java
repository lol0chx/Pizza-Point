package com.PizzaPoint.menu;


public abstract class MenuItem {
    private String name;
    private double basePrice;

    public MenuItem (String name ,double basePrice) {
        this.name = name;
        this.basePrice = basePrice;

    }

    public String getName() {
        return name;
    }


    public double getBasePrice() {
        return basePrice;
    }


}
