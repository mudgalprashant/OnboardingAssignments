package entities;
import enums.*;

public class Item {

    // Item properties/attributes
    private String name;
    private Category category;
    private double price;
    private double tax;
    private int quantity;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public void setTax(double tax){
        this.tax = tax;
    }
    public double getTax() {
        return tax;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
