package com.PizzaPoint.menu.pizza.side;

import java.util.LinkedHashMap;
import java.util.Map;

public class SideMenu {
    private static final Map<String, Side> ALL_SIDES = new LinkedHashMap<>();

    static {

        ALL_SIDES.put("Parmesan Packets", new Side("Parmesan Packets"));
        ALL_SIDES.put("Pepper Flakes", new Side("red pepper"));
    }

    public static Map<String, Side> getAllSides() {
        return ALL_SIDES;
    }
}
