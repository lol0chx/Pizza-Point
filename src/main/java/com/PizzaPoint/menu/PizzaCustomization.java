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
    private Customization<PizzaSize> size = new Customization<>();
    private Customization<CrustType> crust = new Customization<>();

    // Constructor
    public PizzaCustomization() {
        // Nothing else needed; toppings/size/crust already initialized
    }

    // --- Toppings ---
    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    public Customization<Topping> getToppings() {
        return toppings;
    }

    // --- Size ---
    public void setSize(PizzaSize pizzaSize) {
        size.add(pizzaSize); // Only one size should be added in practice
    }

    public Customization<PizzaSize> getSize() {
        return size;
    }

    // --- Crust ---
    public void setCrust(CrustType crustType) {
        crust.add(crustType); // Only one crust should be added in practice
    }

    public Customization<CrustType> getCrust() {
        return crust;
    }

    // Display current customization
    public String display() {
        String sizeDisplay = size.getAll().isEmpty() ? "Not set" : size.getAll().get(0).toString();
        String crustDisplay = crust.getAll().isEmpty() ? "Not set" : crust.getAll().get(0).toString();

        return "Size: " + sizeDisplay
                + ", Crust: " + crustDisplay
                + ", Toppings: " + toppings.display();
    }
}
