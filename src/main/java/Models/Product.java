package Models;

import lombok.Data;

import java.time.LocalDate;
@Data
public class Product  {
    private long id;
    private final String productСategory;
    private final String productName;
    private final Double price;
    private final LocalDate manufactureDate;
    private final LocalDate expiryDate;
    private static long countProducts = 0;
    public Product(String category, String productName, Double price, LocalDate manufactureDate, LocalDate expiryDate) {
        this.id = ++countProducts;  // автоматически присваиваем id
        this.productСategory = category;
        this.productName = productName;
        this.price = price;
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
    }
}