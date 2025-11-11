package com.PizzaPoint.core.interfaces;

public interface Customizable<T> {
    void add(T option);
    void remove(T option);
    String displayCustomization();
}
