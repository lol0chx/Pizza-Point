package com.PizzaPoint.menu;

import com.PizzaPoint.core.Customization;
import com.PizzaPoint.core.enums.CheeseType;
import com.PizzaPoint.core.enums.CrustType;
import com.PizzaPoint.core.enums.PizzaSize;
import com.PizzaPoint.core.enums.SauceType;
import com.PizzaPoint.core.interfaces.Customizable;
import com.PizzaPoint.core.interfaces.Orderable;
import com.PizzaPoint.menu.topping.ToppingOption;
import com.PizzaPoint.orders.PriceCalculator;

import java.util.Map;

public class Pizza extends MenuItem implements Customizable<ToppingOption>, Orderable {

    private final Customization<ToppingOption> toppings = new Customization<>();
    //single choice is true one choice is allowed so it replaces the choice instead
    private  final Customization<PizzaSize> size = new Customization<>(true);
    private final Customization<CrustType> crust = new Customization<>(true);
    private final Customization<CheeseType> cheese = new Customization<>(true);
    private final Customization<SauceType> sauce = new Customization<>(true);

    //all pizza should start with name price and inttialsize and crust
    public Pizza(double price, PizzaSize initialSize, CrustType initialCrust, SauceType initialSauce, CheeseType initialCheese ) {
        this("Custom Pizza ", price, initialSize, initialCrust, initialSauce, initialCheese);
    }
    //for preset pizza
    public Pizza(String name, double price, PizzaSize initialSize, CrustType initialCrust, SauceType initialSauce, CheeseType initialCheese ) {
        super(name, price);
        size.add(initialSize);
        crust.add(initialCrust);
        sauce.add(initialSauce);
        cheese.add(initialCheese);
    }


    public void setSauce(SauceType sauce) {
        this.sauce.clear();
        this.sauce.add(sauce);
    }
    public SauceType getSauce() {
        return this.sauce.getAll().keySet().stream().findFirst().orElse(null);
    }

    public void setCheese(CheeseType cheese) {
        this.cheese.clear();
        this.cheese.add(cheese);
    }
    public CheeseType getCheese() {
        return this.cheese.getAll().keySet().stream().findFirst().orElse(null);
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
    // getAll() returns an unmodifiable copy for receipt
    public Map<ToppingOption, Integer> getToppingsMap() {
        return toppings.getAll();
    }
    // use for calculation
    public Customization<ToppingOption> getToppings() {
        return toppings;
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

        double price = 0.0;
        PizzaSize size = getSize();
        CrustType crust = getCrust();
        if (size != null) {
            price += size.getBasePrice();
        }
        if (crust != null) {
            price += crust.getExtraCost();
        }
        // Add topping costs
        for (Map.Entry<ToppingOption, Integer> entry : getToppingsMap().entrySet()) {
            price += entry.getKey().getPrice() * entry.getValue();
        }
        return price;
    }


}
