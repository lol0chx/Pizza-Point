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

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return basePrice;
    }

    public void setPrice(double price) {
        this.basePrice = price;
    }

}
