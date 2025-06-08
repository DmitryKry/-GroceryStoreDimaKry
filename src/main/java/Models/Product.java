package Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class Product  {
    private long id;
    private final String productСategory;
    private final String productName;
    private final Double price;
    private final LocalDate manufactureDate;
    private final LocalDate expiryDate;
}