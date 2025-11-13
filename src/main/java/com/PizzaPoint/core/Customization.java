package com.PizzaPoint.core;

import com.PizzaPoint.core.interfaces.Customizable;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Customization<T> implements Customizable<T> {
    private Map<T, Integer> options = new HashMap<>(); // map to track count of option
    private boolean singleChoice;


    // default is false for toppings or other like topping
    public Customization() {

        this(false);
    }

    //allow specifying single choice for things like crust and size
    public Customization( boolean singleChoice) {

        this.singleChoice = singleChoice;
    }

    // use this for any option topping, size, crust type or any
    @Override
    public void add(T option) {
        if (singleChoice) {
            options.clear();    //replace option if only one is allowed
            options.put(option, 1);
        } else {
            options.put(option, options.getOrDefault(option, 0) + 1);
        }

    }
    //to remove any option
    @Override
    public void remove(T option) {
        if (!options.containsKey(option)) return; // if option doesnt exist just leave
        int count = options.get(option); //get count of option
        if (count <= 1) options.remove(option); //if there is only 1 option remove that
        else options.put(option, count -1);  //if option is multiple(cheese x3) remove 1 cheese at a time
    }

    // Get count of a specific option
    public int getCount(T option) {
        return options.getOrDefault(option, 0);
    }

    // Get all options and their counts
    public Map<T, Integer> getAll() {
        return Map.copyOf(options);
    }
    @Override
    public String displayCustomization() {
        StringBuilder result = new StringBuilder();

        if (options.isEmpty()) {
            result.append("None\n");
        } else {
            options.forEach((opt, count) ->
                    result.append(opt)
                            .append(count > 1 ? " x" + count : "")
                            .append("\n")
            );
        }

        return result.toString();
    }

    // Display options in readable form
    public String display() {
        if (options.isEmpty()) return "None";

        return options.entrySet().stream()
                .map(e -> e.getKey() + (e.getValue() > 1 ? " x" + e.getValue() : ""))
                .collect(Collectors.joining(", "));
    }

    //to clear all options applied
    public void clear() {
        options.clear();
    }


}
