package com.PizzaPoint.menu;

import com.PizzaPoint.core.enums.CrustType;
import com.PizzaPoint.core.enums.PizzaSize;
import com.PizzaPoint.menu.toppings.Topping;

import java.util.ArrayList;
import java.util.List;

public class PizzaCustomization {
    // this handles all the customization for pizza

    // Use generic Customization class for reusable logic
    private Customization<Topping> toppings = new Customization<>();
    //single choice is true so it replaces the choice instead
    private Customization<PizzaSize> size = new Customization<>(true);
    private Customization<CrustType> crust = new Customization<>(true);

    // Constructor
    public PizzaCustomization() {
        // Nothing else needed; toppings/size/crust already initialized
    }

    // counts extra using map in customization
    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    public Customization<Topping> getToppings() {
        return toppings;
    }


    public void setSize(PizzaSize pizzaSize) {
        size.add(pizzaSize);
    }

    public PizzaSize getSize() {
        return size.getAll().keySet().stream().findFirst().orElse(null);
    }

    // -
    public void setCrust(CrustType crustType) {
        crust.add(crustType); // Only one crust should be added
    }

    public CrustType getCrust() {
        return crust.getAll().keySet().stream().findFirst().orElse(null);
    }

    // Display current customization

    public String display() {

        return "Size: " + getSize() +
                ", Crust: " + getCrust() +
                ", Toppings: " + toppings.display();
    }
}
