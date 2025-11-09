package com.PizzaPoint.menu;

import java.util.ArrayList;
import java.util.List;

public class Customization<T> {
    private List<T> options = new ArrayList<>();

    public Customization(List<T> options) {
        this.options = options;
    }

    // use this for any option topping, size, crust type or any
    public void add(T option) {
        if (!options.contains(option)) {
            options.add(option);
        }
    }
    // get a copy of all the options
    public List<T> getall() {
        return List.copyOf(options);
    }

    //display all the options as a string
    public String display() {
        if (options.isEmpty()) {
            return "None";
        }
        return options.toString();
    }

    //to clear all options applied
    public void clear() {
        options.clear();
    }

    // get first option
}
