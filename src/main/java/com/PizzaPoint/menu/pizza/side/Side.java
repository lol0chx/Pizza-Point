package com.PizzaPoint.menu.pizza.side;

import com.PizzaPoint.menu.MenuItem;

public class Side extends MenuItem {

        public Side(String name) {
            super(name, 0.0); // No price, just name
        }

        @Override
        public double calculatePrice() {
            return 0.0; // sides are included
        }
    }

