package com.PizzaPoint;

import com.PizzaPoint.orders.Order;
import com.PizzaPoint.orders.Receipt;
import com.PizzaPoint.ui.HomeScreen;
import com.PizzaPoint.ui.pizza.AddPizzaScreen;

public class Main {
    static void main() {
        System.out.println("hello");
        Order order = new Order();
        HomeScreen home = new HomeScreen(order);
        home.startHome();
    }
}
