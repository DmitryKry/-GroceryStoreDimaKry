package org.example.javafxtest;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Product  {
    private long id;
    private final String productName;
    private final Double price;
    private final LocalDate manufactureDate;
    private final LocalDate expiryDate;
}
