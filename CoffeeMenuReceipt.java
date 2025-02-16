import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class CoffeeMenuReceipt {
    static String[] coffeeTypes = {"Espresso", "Latte", "Cappuccino", "Mocha"};
    static double[] coffeePrices = {50.0, 70.0, 65.0, 80.0};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String menu = "~~~ Welcome to the Coffee Shop ~~~\n" +
                "Here's our coffee menu:\n" +
                "1. Espresso - 50.0 PHP\n" +
                "2. Latte - 70.0 PHP\n" +
                "3. Cappuccino - 65.0 PHP\n" +
                "4. Mocha - 80.0 PHP\n" +
                "0. Complete Order\n" +
                "Please choose a coffee (1-4) or press 0 to complete your order: ";

        // 2D array to store [quantity, price, total price] for each coffee type
        double[][] orderDetails = new double[coffeeTypes.length][3];

        while (true) {
            System.out.print(menu);
            int choice = getValidChoice(scanner);

            // Exit if the user chooses 0 to complete the order
            if (choice == 0) {
                break;
            }

            // Ask the user for the quantity they want to order
            int quantity = getValidQuantity(scanner);

            // Add order details to the array
            int index = choice - 1;
            orderDetails[index][0] += quantity; // Store quantity
            orderDetails[index][1] = coffeePrices[index]; // Store price per coffee
            orderDetails[index][2] = orderDetails[index][0] * coffeePrices[index]; // Store total cost
        }

        // Calculate totals
        double totalAmount = calculateTotalAmount(orderDetails);
        double vatAmount = totalAmount * 0.12;
        double finalTotal = totalAmount + vatAmount;

        // Print receipt to console
        printReceipt(orderDetails);
        System.out.printf("Total before VAT: %.2f PHP\n", totalAmount);
        System.out.printf("VAT (12%%): %.2f PHP\n", vatAmount);
        System.out.printf("Total amount due: %.2f PHP\n", finalTotal);
        System.out.println("------------------------------");

        // Save the receipt to a file
        saveReceiptToFile(orderDetails, totalAmount, vatAmount, finalTotal);
        System.out.println("Your receipt has been saved to 'CoffeeReceipt.txt'. Thank you for visiting!");
    }

    // Method to get a valid coffee choice from the user
    public static int getValidChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Oops! Please enter a valid number between 1 and 4, or press 0 to finish.");
            scanner.next(); // Clear invalid input
        }
        int choice = scanner.nextInt();
        if (choice < 0 || choice > 4) {
            System.out.println("Invalid choice. Please choose a valid option.");
            return getValidChoice(scanner);
        }
        return choice;
    }

    // Method to get a valid quantity from the user
    public static int getValidQuantity(Scanner scanner) {
        System.out.println("How many would you like to order?");
        while (!scanner.hasNextInt()) {
            System.out.println("Oops! Please enter a positive number for the quantity.");
            scanner.next(); // Clear invalid input
        }
        int quantity = scanner.nextInt();
        while (quantity <= 0) {
            System.out.println("Quantity must be a positive number. Please try again.");
            quantity = scanner.nextInt();
        }
        return quantity;
    }

    // Method to calculate the total amount before VAT
    public static double calculateTotalAmount(double[][] orderDetails) {
        double total = 0;
        for (double[] order : orderDetails) {
            total += order[2]; // Add up total prices of all ordered items
        }
        return total;
    }

    // Method to print the receipt to the console
    public static void printReceipt(double[][] orderDetails) {
        System.out.println("\n~~~~~~~~ Receipt ~~~~~~~~");
        System.out.printf("%-12s %-10s %-10s %-10s\n", "Item", "Qty", "Price", "Total");
        for (int i = 0; i < orderDetails.length; i++) {
            if (orderDetails[i][0] > 0) {
                System.out.printf("%-12s %-10.0f %-10.2f %-10.2f\n",
                        coffeeTypes[i], orderDetails[i][0], orderDetails[i][1], orderDetails[i][2]);
            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    // Method to save the receipt to a file
    public static void saveReceiptToFile(double[][] orderDetails, double totalAmount, double vatAmount, double finalTotal) {
        try (FileWriter fileWriter = new FileWriter("CoffeeReceipt.txt")) {
            fileWriter.write("~~~~~~~~ Receipt ~~~~~~~~\n");
            fileWriter.write(String.format("%-12s %-10s %-10s %-10s\n", "Item", "Qty", "Price", "Total"));
            for (int i = 0; i < orderDetails.length; i++) {
                if (orderDetails[i][0] > 0) {
                    fileWriter.write(String.format("%-12s %-10.0f %-10.2f %-10.2f\n",
                            coffeeTypes[i], orderDetails[i][0], orderDetails[i][1], orderDetails[i][2]));
                }
            }
            fileWriter.write("~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            fileWriter.write(String.format("Total before VAT: %.2f PHP\n", totalAmount));
            fileWriter.write(String.format("VAT (12%%): %.2f PHP\n", vatAmount));
            fileWriter.write(String.format("Total amount due: %.2f PHP\n", finalTotal));
            fileWriter.write("------------------------------\n");
        } catch (IOException e) {
            System.out.println("Oops! There was an error saving the receipt. Please try again.");
        }
    }
}
