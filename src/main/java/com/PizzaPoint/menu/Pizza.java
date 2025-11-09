package com.PizzaPoint.menu;

import com.PizzaPoint.core.enums.CrustType;
import com.PizzaPoint.core.enums.PizzaSize;
import com.PizzaPoint.menu.toppings.Topping;

public class Pizza extends MenuItem {
    private PizzaCustomization customization;

    public Pizza(String name, double price, PizzaCustomization customization) {
        super(name, price);
        this.customization = customization;
    }

    public void setSize(PizzaSize size) {
        customization.setSize(size);
    }

    public void setCrust(CrustType crust) {
        customization.setCrust(crust);
    }

    public void addTopping(Topping topping) {
        customization.addTopping(topping);
    }

    public void removeTopping(Topping topping) {
        customization.removeTopping(topping);
    }

    public void displayCustomization() {
        System.out.println(customization.display());
    }
}
