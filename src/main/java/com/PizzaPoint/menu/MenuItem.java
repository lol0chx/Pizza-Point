package com.PizzaPoint.menu;


public abstract class MenuItem {
    private String Name;
    private double basePrice;

    public MenuItem(String name, double price) {
        Name = name;
        this.basePrice = price;
    }

    public String getName() {
        return Name;
    }


    public double getBasePrice() {
        return basePrice;
    }


}
