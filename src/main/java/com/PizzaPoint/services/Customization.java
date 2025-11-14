package com.PizzaPoint.services;

import com.PizzaPoint.core.interfaces.Customizable;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Customization<T> implements Customizable<T> {
    private Map<T, Integer> options = new HashMap<>(); // map to track count of option
    private boolean singleChoice;


    // Default constructor allows multiple items (for toppings)
    public Customization() {
        this(false);
    }

    // Constructor with single-choice mode for unique attributes like size or crust
    public Customization(boolean singleChoice) {
        this.singleChoice = singleChoice;
    }

    // Adds option: replaces if single-choice, increments count if multiple allowed
    @Override
    public void add(T option) {
        if (singleChoice) {
            options.clear();
            options.put(option, 1);
        } else {
            options.put(option, options.getOrDefault(option, 0) + 1);
        }
    }
    // Decrements option count or removes entirely if count is 1
    @Override
    public void remove(T option) {
        if (!options.containsKey(option)) return;
        int count = options.get(option);
        if (count <= 1) options.remove(option);
        else options.put(option, count - 1);
    }

    // Returns quantity of specific option
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
