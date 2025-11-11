package com.PizzaPoint.ui.pizza;

import com.PizzaPoint.core.enums.CrustType;
import com.PizzaPoint.core.enums.PizzaSize;
import com.PizzaPoint.menu.Pizza;
import com.PizzaPoint.menu.topping.ToppingMenu;
import com.PizzaPoint.menu.topping.ToppingOption;
import com.PizzaPoint.orders.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddPizzaScreen {
    private final Order order;
    private final Scanner scanner = new Scanner(System.in);

    public AddPizzaScreen(Order order) {
        this.order = order;
    }

    public void start() {
        System.out.println("🍕 Build your pizza!");
        //choose size and crust
        PizzaSize size = chooseSize();
        CrustType crust = chooseCrust();

        List<ToppingOption> toppings = chooseToppings();

        double basePrice = size == PizzaSize.SMALL ? 8.0 :
                          size == PizzaSize.MEDIUM? 10.0 : 12.0;
        Pizza pizza = new PizzaBuilder(basePrice, size, crust).build();
        new ToppingSelector(pizza).addMultiple(toppings);
        order.addItem(pizza);
        System.out.println("✅ your Pizza is added!");
        System.out.println(pizza.displayCustomization());
    }

    private PizzaSize chooseSize() {
        System.out.println("select size: 1:Small \n 2: Medium \n 3:Large");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return switch (choice) {
            case 1 -> PizzaSize.SMALL;
            case 2 -> PizzaSize.MEDIUM;
            default -> PizzaSize.LARGE;
        };
    }
    private CrustType chooseCrust() {
        System.out.println("select Crust: 1:Stuffed Crust \n 2:Thin Crust \n 3:Thick Crust \n4:Pan Crust \n5:Regular Crust");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return switch (choice) {
            case 1 -> CrustType.STUFFED;
            case 2 -> CrustType.THIN;
            case 3 -> CrustType.THICK;
            case 4 -> CrustType.PAN;
            default -> CrustType.REGULAR;
        };
    }
    private List<ToppingOption> chooseToppings() {
        List<ToppingOption> selected = new ArrayList<>();
        List<ToppingOption> allToppings = new ArrayList<>(ToppingMenu.getAllToppings().values());
        boolean done = false;
        while (!done) {
            System.out.println("Available Toppings");
            for (int i =0; i < allToppings.size(); i++) {
                ToppingOption t = allToppings.get(i);
                System.out.printf("%d %s $.2f $s\n", i + 1, t.getName(), t.getPrice(), t.isBase() ? "included" : "");
            }
            System.out.println("0) Done");

            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0) done = true;
            else if (choice > 0 && choice <= allToppings.size()) {
                selected.add(allToppings.get(choice - 1));
                System.out.println(allToppings.get(choice -1).getName() + " added");
            } else System.out.println("Invalid choice");
        }
        return selected;
    }
}
