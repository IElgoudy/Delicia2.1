package UI;

import Service.Order;
import com.pluralsight.*;

import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";
    private static Customer customer = new Customer();
    private static Order currentOrder;
    private static Scanner scanner = new Scanner(System.in);

    private static final String[] meatToppings = {"steak", "ham", "salami", "roast beef", "chicken", "bacon"};
    private static final String[] cheeseToppings = {"american", "provolone", "cheddar", "swiss"};
    private static final String[] regularToppings = {"lettuce", "peppers", "onions", "tomatoes", "jalapenos", "cucumbers", "pickles", "guacamole", "mushrooms"};
    private static final String[] sauceToppings = {"Mayo", "Mustard", "Ketchup", "Ranch", "Thousand islands"};

    public static void main(String[] args) {
        homeScreen();
    }

    private static void homeScreen() {
        while (true) {
            System.out.println(BLUE + "#########################################" + RESET);
            System.out.println(BLUE + "#                DELICIA                #" + RESET);
            System.out.println(BLUE + "#########################################" + RESET);
            System.out.println(BLUE + "#              Home Screen              #" + RESET);
            System.out.println(BLUE + "#########################################" + RESET);
            System.out.println(BLUE + "# 1. New Order                          #" + RESET);
            System.out.println(BLUE + "# 0. Exit                               #" + RESET);
            System.out.println(BLUE + "#########################################" + RESET);

            System.out.print(YELLOW + "Enter your choice: " + RESET);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print(YELLOW + "Enter order name: " + RESET);
                String orderId = scanner.nextLine();
                currentOrder = new Order(orderId, new Date());
                orderScreen();
            } else if (choice == 0) {
                break;
            }
        }
    }

    private static void orderScreen() {
        while (true) {
            System.out.println();
            System.out.println(GREEN + "#########################################" + RESET);
            System.out.println(GREEN + "#              Order Screen             #" + RESET);
            System.out.println(GREEN + "#########################################" + RESET);
            System.out.println(GREEN + "# 1. Add Sandwich                       #" + RESET);
            System.out.println(GREEN + "# 2. Add Drink                          #" + RESET);
            System.out.println(GREEN + "# 3. Add Chips                          #" + RESET);
            System.out.println(GREEN + "# 4. Checkout                           #" + RESET);
            System.out.println(GREEN + "# 0. Cancel Order                       #" + RESET);
            System.out.println(GREEN + "#########################################" + RESET);

            System.out.print(YELLOW + "Enter your choice: " + RESET);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                addSandwich();
            } else if (choice == 2) {
                addDrink();
            } else if (choice == 3) {
                addChips();
            } else if (choice == 4) {
                checkout();
                break;
            } else if (choice == 0) {
                cancelOrder();
                break;
            }
        }
    }

    private static void addSandwich() {
        System.out.println(YELLOW + "Enter bread selection" + RESET);
        System.out.println("1). white");
        System.out.println("2). wheat");
        System.out.println("3). rye");
        System.out.println("4). wrap");
        int breadChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        String breadType = switch (breadChoice) {
            case 1 -> "white";
            case 2 -> "wheat";
            case 3 -> "rye";
            case 4 -> "wrap";
            default -> "unknown";
        };

        System.out.print(YELLOW + "Enter sandwich size (4\", 8\", 12\"): " + RESET);
        String size = scanner.nextLine();

        System.out.print(YELLOW + "Would you like your sandwich toasted? (yes/no): " + RESET);
        boolean toasted = scanner.nextLine().equalsIgnoreCase("yes");

        Sandwich sandwich = new Sandwich(size, breadType, toasted);

        System.out.println(YELLOW + "Available meat toppings: " + RESET);
        for (int i = 0; i < meatToppings.length; i++) {
            System.out.println((i + 1) + "). " + meatToppings[i]);
        }
        System.out.print(YELLOW + "Enter meat toppings (comma separated, or 'none'): " + RESET);
        String meatToppingsInput = scanner.nextLine();
        if (!meatToppingsInput.equalsIgnoreCase("none")) {
            for (String topping : meatToppingsInput.split(",")) {
                sandwich.addTopping(new Topping(meatToppings[Integer.parseInt(topping.trim()) - 1], "Premium", 1.00));
            }
        }

        System.out.print(YELLOW + "Would you like extra meat? (yes/no): " + RESET);
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            sandwich.addTopping(new Topping("Extra Meat", "Premium", 0.50));
        }

        System.out.println(YELLOW + "Available cheese toppings: " + RESET);
        for (int i = 0; i < cheeseToppings.length; i++) {
            System.out.println((i + 1) + "). " + cheeseToppings[i]);
        }
        System.out.print(YELLOW + "Enter cheese toppings (comma separated, or 'none'): " + RESET);
        String cheeseToppingsInput = scanner.nextLine();
        if (!cheeseToppingsInput.equalsIgnoreCase("none")) {
            for (String topping : cheeseToppingsInput.split(",")) {
                sandwich.addTopping(new Topping(cheeseToppings[Integer.parseInt(topping.trim()) - 1], "Premium", 0.75));
            }
        }

        System.out.print(YELLOW + "Would you like extra cheese? (yes/no): " + RESET);
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            sandwich.addTopping(new Topping("Extra Cheese", "Premium", 0.30));
        }

        System.out.println(YELLOW + "Available regular toppings: " + RESET);
        for (int i = 0; i < regularToppings.length; i++) {
            System.out.println((i + 1) + "). " + regularToppings[i]);
        }
        System.out.print(YELLOW + "Enter regular toppings (comma separated, or 'none'): " + RESET);
        String regularToppingsInput = scanner.nextLine();
        if (!regularToppingsInput.equalsIgnoreCase("none")) {
            for (String topping : regularToppingsInput.split(",")) {
                sandwich.addTopping(new Topping(regularToppings[Integer.parseInt(topping.trim()) - 1], "Regular", 0.00));
            }
        }

        System.out.println(YELLOW + "Available sauce toppings: " + RESET);
        for (int i = 0; i < sauceToppings.length; i++) {
            System.out.println((i + 1) + "). " + sauceToppings[i]);
        }
        System.out.print(YELLOW + "Enter sauce toppings (comma separated, or 'none'): " + RESET);
        String sauceToppingsInput = scanner.nextLine();
        if (!sauceToppingsInput.equalsIgnoreCase("none")) {
            for (String topping : sauceToppingsInput.split(",")) {
                sandwich.addTopping(new Topping(sauceToppings[Integer.parseInt(topping.trim()) - 1], "Regular", 0.00));
            }
        }

        currentOrder.addProduct(sandwich);
    }

    private static void addDrink() {
        System.out.println();
        System.out.println(YELLOW + "Enter drink selection:" + RESET);
        System.out.println("1). coke");
        System.out.println("2). orange fanta");
        System.out.println("3). Lemonade");
        System.out.println("4). water");
        int drinkChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        String flavor = switch (drinkChoice) {
            case 1 -> "coke";
            case 2 -> "orange fanta";
            case 3 -> "Lemonade";
            case 4 -> "water";
            default -> "unknown";
        };

        System.out.println(YELLOW + "Enter drink size:" + RESET);
        System.out.println("1). Small");
        System.out.println("2). Medium");
        System.out.println("3). Large");
        int sizeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        String size = switch (sizeChoice) {
            case 1 -> "small";
            case 2 -> "medium";
            case 3 -> "large";
            default -> "unknown";
        };

        double price = switch (size.toLowerCase()) {
            case "small" -> 2.00;
            case "medium" -> 2.50;
            case "large" -> 3.00;
            default -> 0.00;
        };

        Drink drink = new Drink(size, flavor, price);
        currentOrder.addProduct(drink);
    }

    private static void addChips() {
        System.out.println();
        System.out.println(YELLOW + "Enter chip selection:" + RESET);
        System.out.println("1). Doritos");
        System.out.println("2). Hot Cheetos");
        System.out.println("3). Lays");
        int chipChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        String type = switch (chipChoice) {
            case 1 -> "Doritos";
            case 2 -> "Hot Cheetos";
            case 3 -> "Lays";
            default -> "unknown";
        };

        Chip chip = new Chip(type, 1.50);
        currentOrder.addProduct(chip);
    }

    private static void checkout() {
        customer.addOrder(currentOrder);
        System.out.println(GREEN + "#########################################" + RESET);
        System.out.println(GREEN + "#           Order placed successfully!  #" + RESET);
        System.out.println(GREEN + "#########################################" + RESET);
        currentOrder.printReceipt();
    }

    private static void cancelOrder() {
        System.out.println(RED + "#########################################" + RESET);
        System.out.println(RED + "#             Order canceled            #" + RESET);
        System.out.println(RED + "#########################################" + RESET);
    }
}
