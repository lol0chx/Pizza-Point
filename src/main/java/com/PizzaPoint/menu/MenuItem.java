package com.PizzaPoint.menu;


import com.PizzaPoint.core.interfaces.Orderable;

public abstract class MenuItem  implements Orderable {
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
