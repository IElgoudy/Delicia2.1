package Service;

import com.pluralsight.Product;
import com.pluralsight.Sandwich;
import com.pluralsight.Topping;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private String orderId;
    private Date orderDate;
    private List<Product> products;
    private double totalPrice;

    public Order(String orderId, Date orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public double calculateTotalPrice() {
        totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.calculatePrice();
        }
        return totalPrice;
    }

    public void printReceipt() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH-mm-ss");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");

        StringBuilder receipt = new StringBuilder();
        receipt.append("#########################################\n");
        receipt.append("#                RECEIPT                #\n");
        receipt.append("#########################################\n");
        receipt.append("Order Name: ").append(orderId).append("\n");
        receipt.append("-----------------------------------------\n");
        receipt.append("Order Date: ").append(dateFormat.format(orderDate)).append("\n");
        receipt.append("Time: ").append(timeFormat.format(orderDate)).append("\n");
        receipt.append("-----------------------------------------\n");

        for (Product product : products) {
            receipt.append("Item: ").append(product.getName()).append("\n");
            if (product instanceof Sandwich) {
                Sandwich sandwich = (Sandwich) product;
                receipt.append("  Bread: ").append(sandwich.getBreadType()).append("\n");
                receipt.append("  Size: ").append(sandwich.getSize()).append("\n");
                receipt.append("  Toasted: ").append(sandwich.isToasted() ? "Yes" : "No").append("\n");
                receipt.append("  Toppings:\n");
                for (Topping topping : sandwich.getToppings()) {
                    receipt.append("    - ").append(topping.getName()).append("\n");
                }
            }
            receipt.append("  Price: $").append(product.calculatePrice()).append("\n");
            receipt.append("-----------------------------------------\n");
        }

        receipt.append("Total Price: $").append(calculateTotalPrice()).append("\n");
        receipt.append("-----------------------------------------\n");
        receipt.append("Thank you for your order!\n");
        receipt.append("#########################################\n");

        // Print receipt to console
        System.out.print(receipt.toString());

        // Write receipt to file
        String filePath = String.format("C:/PluralSight/delicia/receipts/receipt_%s_%s.txt", orderId, dateTimeFormat.format(orderDate));
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(receipt.toString());
        } catch (IOException e) {
            System.out.println("An error occurred while writing the receipt to the file.");
            e.printStackTrace();
        }
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
