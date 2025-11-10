package com.PizzaPoint.menu;

import com.PizzaPoint.core.enums.CrustType;
import com.PizzaPoint.core.enums.PizzaSize;
import com.PizzaPoint.core.interfaces.Customizable;
import com.PizzaPoint.core.interfaces.Orderable;
import com.PizzaPoint.menu.toppings.Topping;
import com.PizzaPoint.orders.PriceCalculator;

public class Pizza extends MenuItem implements Orderable, Customizable {

    private PizzaCustomization pizzaCustomization;

    public Pizza(String name, double price, PizzaCustomization customization) {
        super(name, price);
        this.pizzaCustomization = new PizzaCustomization();
    }
    //get the customization object for this pizza.
    public PizzaCustomization getCustomization() {
        return pizzaCustomization;
    }

   //will track using the map
    public void addTopping(Topping topping) {
        pizzaCustomization.addTopping(topping);
    }

    public void removeTopping(Topping topping) {
        pizzaCustomization.removeTopping(topping);
    }


     // Get all current toppings on this pizza
    public Customization<Topping> getToppings() {
        return pizzaCustomization.getToppings();
    }



    public void setSize(PizzaSize size) {
        pizzaCustomization.setSize(size);
    }
    public void setCrust(CrustType crust) {
        pizzaCustomization.setCrust(crust);
    }

    //Get the pizza's current size.
    public PizzaSize getSize() {
        return pizzaCustomization.getSize();
    }
    //get the crust type
    public CrustType getCrust() {
        return pizzaCustomization.getCrust();
    }


    @Override
    public double calculatePrice() {
        return PriceCalculator.calculatePrice(this);
    }

    @Override
    public void displayCustomization() {
       pizzaCustomization.display();
    }

     //Print the details for reciept
    @Override
    public String toString() {
        return getName() + " - " + pizzaCustomization.display() + " | Total: $" + calculatePrice();
    }
}
