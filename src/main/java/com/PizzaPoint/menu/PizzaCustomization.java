package com.PizzaPoint.menu;

import com.PizzaPoint.core.enums.CrustType;
import com.PizzaPoint.core.enums.PizzaSize;
import com.PizzaPoint.menu.toppings.Topping;

import java.util.ArrayList;
import java.util.List;

public class PizzaCustomization {
    // this handles all the customization for pizza
    private PizzaSize size;
    private CrustType crust;
    private List<Topping> toppings = new ArrayList<>();


    //we set size and crust on pizza not here
    public PizzaCustomization() {
        this.toppings = toppings;
    }

    public PizzaSize getSize() {
        return size;
    }

    public void setSize(PizzaSize size) {
        this.size = size;
    }

    public CrustType getCrust() {
        return crust;
    }

    public void setCrust(CrustType crust) {
        this.crust = crust;
    }
    public void addTopping(Topping topping) {
        if (!toppings.contains(topping)) {
            toppings.add(topping);
        }
    }
    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }
    // Display current customization
    public String display() {
        return "Size: " + size + ", Crust: " + crust + ", Toppings: " + toppings;
    }

}
