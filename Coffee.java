import java.util.ArrayList;
import java.util.List;

public class Coffee {

    // Properties (Attributes)
    private String name;
    private String type;
    private String size;
    private double price;
    private String roastLevel;
    private String origin;
    private boolean isDecaf;
    private int stock;
    private List<String> flavorNotes;
    private String brewMethod;

    // Constructor
    public Coffee(String name, String type, String size, double price, String roastLevel, String origin, boolean isDecaf, int stock, String brewMethod) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.price = price;
        this.roastLevel = roastLevel;
        this.origin = origin;
        this.isDecaf = isDecaf;
        this.stock = stock;
        this.flavorNotes = new ArrayList<>();
        this.brewMethod = brewMethod;
    }

    // Method to calculate price based on size
    public double calculatePrice(String size) {
        double sizeMultiplier = 1.0;
        switch (size.toLowerCase()) {
            case "small":
                sizeMultiplier = 1.0;
                break;
            case "medium":
                sizeMultiplier = 1.2;
                break;
            case "large":
                sizeMultiplier = 1.5;
                break;
            default:
                System.out.println("Invalid size! Defaulting to small.");
                break;
        }
        return this.price * sizeMultiplier;
    }

    // Method to check if coffee is in stock
    public boolean checkStock() {
        return this.stock > 0;
    }

    // Method to add a flavor note to the coffee
    public void addFlavor(String note) {
        this.flavorNotes.add(note);
    }

    // Method to update the stock quantity of the coffee
    public void updateStock(int quantity) {
        this.stock += quantity;
    }

    // Method to return a description of the coffee
    public String describe() {
        String description = "A " + roastLevel + " roast coffee from " + origin + " brewed by " + brewMethod + " with flavor notes of ";
        if (flavorNotes.isEmpty()) {
            description += "no specific flavors.";
        } else {
            description += String.join(", ", flavorNotes) + ".";
        }
        return description;
    }

    // Method to set whether the coffee is decaffeinated
    public void setDecaf(boolean isDecaf) {
        this.isDecaf = isDecaf;
    }

    // Method to change the roast level of the coffee
    public void changeRoastLevel(String newRoastLevel) {
        this.roastLevel = newRoastLevel;
    }

    // Method to apply a discount to the price
    public void discount(double percentage) {
        if (percentage > 0 && percentage <= 100) {
            this.price -= this.price * (percentage / 100);
        } else {
            System.out.println("Invalid discount percentage!");
        }
    }

    // Getters and Setters for other properties
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRoastLevel() {
        return roastLevel;
    }

    public void setRoastLevel(String roastLevel) {
        this.roastLevel = roastLevel;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean isDecaf() {
        return isDecaf;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getBrewMethod() {
        return brewMethod;
    }

    public void setBrewMethod(String brewMethod) {
        this.brewMethod = brewMethod;
    }
}

class CoffeeShop {
    public static void main(String[] args) {
        // Creating a Coffee object
        Coffee coffee1 = new Coffee("Espresso", "Arabica", "Medium", 3.50, "Dark", "Colombia", false, 20, "Espresso Machine");

        // Display coffee description
        System.out.println("Coffee Description: " + coffee1.describe());

        // Calculate price based on size
        double price = coffee1.calculatePrice("Large");
        System.out.println("Price for Large size: Php" + price);

        // Check if coffee is in stock
        System.out.println("Is the coffee in stock? " + coffee1.checkStock());

        // Add some flavor notes
        coffee1.addFlavor("Chocolate");
        coffee1.addFlavor("Nutty");

        // Display updated description
        System.out.println("Updated Coffee Description: " + coffee1.describe());

        // Update stock
        coffee1.updateStock(10);
        System.out.println("Updated Stock: " + coffee1.getStock());

        // Apply a discount
        coffee1.discount(10);
        System.out.println("Price after 10% discount: Php" + coffee1.getPrice());

        // Change roast level
        coffee1.changeRoastLevel("Medium");
        System.out.println("Updated Roast Level: " + coffee1.getRoastLevel());

        // Set coffee as decaf
        coffee1.setDecaf(true);
        System.out.println("Is the coffee decaf? " + coffee1.isDecaf());
    }
}
