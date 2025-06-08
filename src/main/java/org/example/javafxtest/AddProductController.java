package org.example.javafxtest;

import Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AddProductController {
    @FXML private TextField productCategoryField;
    @FXML private TextField productNameField;
    @FXML private TextField priceField;
    @FXML private DatePicker manufactureDatePicker;
    @FXML private DatePicker expiryDatePicker;
    @FXML private Label ErrorText;

    private Product newProduct = null;

    @FXML
    private void onOkButtonClick() {
        String category = productCategoryField.getText();
        String name = productNameField.getText();
        String priceText = priceField.getText();
        LocalDate manufactureDate = manufactureDatePicker.getValue();
        LocalDate expiryDate = expiryDatePicker.getValue();
        if (newProduct != null)
            newProduct = null;
        if (name.isEmpty()) ErrorText.setText("Не указано имя");
        else if (priceText.isEmpty()) ErrorText.setText("Не указана цена");
        else if (category.isEmpty()) ErrorText.setText("Не указана категория товара");
        else if (manufactureDate == null) ErrorText.setText("Не указана дата изготовления");
        else if (expiryDate == null) ErrorText.setText("Не указан срок годности");
        newProduct = new Product(category, name, Double.parseDouble(priceText), manufactureDate, expiryDate);
    }



    public Product getNewProduct() {
        return newProduct == null ? null : newProduct;
    }
}
