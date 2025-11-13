# 🍕 PizzaPoint - Pizza Ordering System

A Java-based console application for managing pizza orders with customizable pizzas, signature pizzas, drinks, and checkout functionality.

## 📋 Table of Contents
- [Features](#features)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [How to Use](#how-to-use)
- [Menu Options](#menu-options)
- [Payment Processing](#payment-processing)
- [Architecture Highlights](#architecture-highlights)

## ✨ Features

### 🎯 Core Functionality
- **Custom Pizza Builder**: Create your own pizza with customizable size, crust, sauce, cheese, and toppings
- **Signature Pizzas**: Pre-designed pizza templates (Meat Lovers, Veggie Supreme, Hawaiian, BBQ Chicken, Margherita)
- **Customizable Signature Pizzas**: Add or remove toppings from signature pizzas
- **Drink Orders**: Add drinks with various sizes
- **Free Sides**: Add complimentary sides (Parmesan packets, pepper flakes) to pizzas
- **Order Management**: View, modify, and manage orders before checkout
- **Payment Processing**: Support for both cash and card payments
- **Receipt Generation**: Detailed receipts with customer name, timestamp, and itemized pricing
- **Receipt Persistence**: Automatically saves receipts to `receipts/` directory

### 🔧 Technical Features
- Generic-based customization system for flexible item configuration
- Enum-driven menu items with pricing logic
- Dynamic pricing based on pizza size (topping multipliers)
- Input validation for all user interactions
- Factory pattern for signature pizza creation
- Composition-based design for pizza accessories (sides)

## 📁 Project Structure

```
PizzaPoint/
├── src/main/java/com/PizzaPoint/
│   ├── Main.java                          # Application entry point
│   ├── core/
│   │   ├── Customization.java             # Generic customization container
│   │   ├── enums/
│   │   │   ├── PizzaSize.java            # SMALL, MEDIUM, LARGE with pricing
│   │   │   ├── CrustType.java            # REGULAR, THIN, THICK, etc.
│   │   │   ├── SauceType.java            # MARINARA, ALFREDO, BBQ, etc.
│   │   │   ├── CheeseType.java           # MOZZARELLA, CHEDDAR, etc.
│   │   │   ├── DrinkSize.java            # SMALL, MEDIUM, LARGE
│   │   │   ├── OrderStatus.java          # Order state management
│   │   │   └── ToppingCategory.java      # VEG, MEAT, EXTRA
│   │   └── interfaces/
│   │       ├── Customizable.java         # For items with add/remove options
│   │       ├── Orderable.java            # Items that can be ordered
│   │       ├── PricedItem.java           # Items with pricing logic
│   │       └── Saveable.java             # Persistable entities
│   ├── menu/
│   │   ├── MenuItem.java                  # Abstract base for menu items
│   │   ├── pizza/
│   │   │   ├── Pizza.java                # Pizza entity with customization
│   │   │   ├── SignaturePizza.java       # Predefined pizza templates
│   │   │   ├── SignaturePizzaMenu.java   # Factory for signature pizzas
│   │   │   ├── side/
│   │   │   │   ├── Side.java             # Side item (napkins, packets, etc.)
│   │   │   │   └── SideMenu.java         # Available sides catalog
│   │   │   └── toppings/
│   │   │       ├── Topping.java          # Base topping class
│   │   │       ├── ToppingOption.java    # Topping with category
│   │   │       ├── ToppingMenu.java      # All available toppings
│   │   │       └── ToppingSelector.java  # Helper for adding toppings
│   │   └── drink/
│   │       ├── Drink.java                # Drink entity
│   │       └── DrinkMenu.java            # Available drinks catalog
│   ├── orders/
│   │   ├── Order.java                     # Order container with customer info
│   │   ├── Receipt.java                   # Receipt generator and formatter
│   │   └── PriceCalculator.java          # Pricing utilities
│   ├── ui/
│   │   ├── HomeScreen.java                # Main menu
│   │   ├── NewOrderScreen.java            # Order management screen
│   │   ├── CheckOutScreen.java            # Payment processing
│   │   ├── AddSideScreen.java             # Side selection UI
│   │   ├── AddDrinkScreen.java            # Drink selection UI
│   │   └── pizza/
│   │       ├── AddPizzaScreen.java       # Custom pizza builder UI
│   │       └── AddSignaturePizzaScreen.java # Signature pizza UI
│   └── util/
│       ├── InputHandler.java              # User input validation
│       └── PriceCalculator.java           # Total calculation logic
└── receipts/                               # Saved receipt files

```

## 🚀 Getting Started

### Prerequisites
- Java 25 or higher
- Maven

### Installation & Running

1. **Clone the repository**
```bash
git clone https://github.com/lol0chx/Pizza-Point.git
cd Pizza-Point
```

2. **Build the project**
```bash
mvn clean compile
```

3. **Run the application**
```bash
mvn exec:java -Dexec.mainClass="com.PizzaPoint.Main"
```

## 🎮 How to Use

### Starting an Order

1. Launch the application
2. Select "New order" from the home screen
3. Enter customer name
4. Choose from the following options:
   - Add Custom Pizza
   - Add Signature Pizza
   - Add Drink
   - View Order
   - Checkout
   - Start New Order
   - Back to Home

### Building a Custom Pizza

1. Select "Add Custom Pizza"
2. Choose pizza size (Small $10.99, Medium $14.99, Large $18.99)
3. Select crust type (Regular, Thin, Thick, Stuffed, Gluten-Free)
4. Choose base sauce (Marinara, Alfredo, BBQ, Pesto, Ranch, White Garlic)
5. Add extra sauce if desired
6. Select cheese type (Mozzarella, Cheddar, Parmesan, Provolone, Ricotta, Vegan)
7. Add extra cheese if desired
8. Add toppings by category:
   - **Vegetables**: Mushrooms, Bell Peppers, Onions, Olives, Tomatoes, Spinach, Jalapeños
   - **Meats**: Pepperoni, Italian Sausage, Bacon, Ham, Beef, Chicken, Anchovies
   - **Extras**: Extra Cheese ($2.00), Extra Sauce ($1.00)
9. Add free sides (Parmesan packets, Pepper flakes)
10. Pizza is added to your order!

### Ordering Signature Pizzas

1. Select "Add Signature Pizza"
2. Choose from 5 signature pizzas:
   - **Meat Lovers**: Pepperoni, Beef, Bacon, Italian Sausage
   - **Veggie Supreme**: Mushrooms, Bell Peppers, Onions, Olives
   - **Hawaiian**: Ham and Pineapple
   - **BBQ Chicken**: Grilled Chicken with BBQ sauce, Onions, Bell Peppers
   - **Margherita**: Fresh Basil, Tomatoes (simple and classic)
3. Select pizza size
4. Optionally customize by adding or removing toppings
5. Add free sides
6. Pizza is added to your order!

## 💳 Payment Processing

### Cash Payment
1. System displays total amount
2. Enter cash amount tendered
3. Must be >= total amount
4. System calculates and displays change
5. Payment details added to receipt

### Card Payment
1. System displays total amount
2. Enter 16-digit card number (spaces/dashes allowed)
3. Enter 3-digit CVV
4. Enter expiration month (1-12)
5. Enter expiration year (current year to +15 years)
6. All inputs are validated
7. Payment confirmation added to receipt

## 🧾 Receipt Details

Receipts include:
- Customer name
- Order date and timestamp
- Itemized list with:
  - Pizza details (size, crust, sauce, cheese)
  - Toppings with adjusted prices based on size
  - Topping quantities (e.g., "Pepperoni x2")
  - Free sides attached to each pizza
  - Drinks with sizes
- Subtotal
- Payment method and details (cash tendered/change)
- Unique receipt ID with timestamp format: `yyyyMMdd-HHmmss`

Receipts are automatically saved to the `receipts/` directory.

## 🏗️ Architecture Highlights

### Design Patterns
- **Factory Pattern**: `SignaturePizzaMenu` creates predefined pizzas
- **Builder Pattern**: Custom pizza construction through `AddPizzaScreen`
- **Strategy Pattern**: Different pricing strategies for sizes and toppings
- **Composition Over Inheritance**: Sides stored in Pizza objects rather than extending MenuItem

### Key Design Decisions
1. **Generic Customization System**: `Customization<T>` allows type-safe option tracking with counts
2. **Enum-Driven Pricing**: Pizza sizes, crusts, and drink sizes encapsulate their own pricing logic
3. **Single-Choice Customizations**: Size, crust, sauce, and cheese use single-choice mode (replaces on change)
4. **Multi-Choice Toppings**: Toppings use count-based tracking (can add multiple portions)
5. **Dynamic Pricing**: Topping prices multiply based on pizza size (Small x1.0, Medium x1.5, Large x2.0)
6. **Sides as Accessories**: Sides are free items attached to pizzas, not independently orderable

### Input Validation
All user inputs are validated through `InputHandler`:
- Integer ranges (menu choices)
- Double amounts (cash payments)
- String patterns (customer names: letters and spaces only)
- Card numbers (16 digits, strips formatting)
- CVV (3 digits)
- Expiration dates (valid month/year ranges)

## 📝 Future Enhancements
- Dessert menu items
- Delivery address management
- Order history and favorites
- Promotional codes and discounts
- Multiple payment splitting
- GUI interface

## 👤 Author
**Enoch** (lol0chx)

## 📄 License
This project is part of a Pluralsight learning exercise.

---
*Built with ☕ and 🍕*
