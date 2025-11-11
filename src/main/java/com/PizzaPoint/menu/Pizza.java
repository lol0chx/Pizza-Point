package com.PizzaPoint.menu;

import com.PizzaPoint.core.Customization;
import com.PizzaPoint.core.enums.CrustType;
import com.PizzaPoint.core.enums.PizzaSize;
import com.PizzaPoint.core.interfaces.Customizable;
import com.PizzaPoint.core.interfaces.Orderable;
import com.PizzaPoint.menu.topping.ToppingOption;
import com.PizzaPoint.orders.PriceCalculator;

public class Pizza extends MenuItem implements Customizable<ToppingOption>, Orderable {

    private final Customization<ToppingOption> toppings = new Customization<>();
    //single choice is true one choice is allowed so it replaces the choice instead
    private  final Customization<PizzaSize> size = new Customization<>(true);
    private final Customization<CrustType> crust = new Customization<>(true);

    //all pizza should start with name price and inttialsize and crust
    public Pizza(double price, PizzaSize initialSize, CrustType initialCrust ) {
        this("Custom Pizza ", price, initialSize,initialCrust);
    }
    //for preset pizza
    public Pizza(String name, double price, PizzaSize initialSize, CrustType initialCrust ) {
        super(name, price);
        size.add(initialSize);
        crust.add(initialCrust);
    }


    //clears old size because we can only have one size and crust
    public void setSize(PizzaSize newSize) {
        size.clear();
        size.add(newSize);
    }
    public void setCrust(CrustType newCrust) {
        crust.clear();
        crust.add(newCrust);
    }
    public CrustType getCrust() {
        return crust.getAll().keySet().stream().findFirst().orElse(null);
    }
    public PizzaSize getSize() {
        return size.getAll().keySet().stream().findFirst().orElse(null);
    }
    @Override
    public void add(ToppingOption topping) {
        toppings.add(topping);
    }
    @Override
    public void remove(ToppingOption topping) {
        toppings.remove(topping);
    }
    @Override
    public String displayCustomization() {
        return "🍕 Pizza Customization:\n" +
                " - Size: " + size.display() + "\n" +
                " - Crust: " + crust.display() + "\n" +
                " - Toppings: " + toppings.display();
    }

    @Override
    public double calculatePrice() {
        return PriceCalculator.calculatePrice(this);
    }


}
