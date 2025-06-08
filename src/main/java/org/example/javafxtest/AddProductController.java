package org.example.javafxtest;

import Models.Product;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.time.LocalDate;

public class AddProductController {
    @FXML private TextField productNameField;
    @FXML private TextField priceField;
    @FXML private DatePicker manufactureDatePicker;
    @FXML private DatePicker expiryDatePicker;
    @FXML private Label ErrorText;
    @FXML private ComboBox<String> productCategoryComboBox;
    private Product newProduct = null;
    String category;
    String name;
    String priceText;
    LocalDate manufactureDate;
    LocalDate expiryDate;

    public void setProductData(Product product) {
        if (product != null) {
            category = product.getProductСategory();
            name = product.getProductName();
            priceText = product.getPrice().toString();
            manufactureDate = product.getManufactureDate();
            expiryDate = product.getExpiryDate();

            // Заполнить поля UI, если они уже инициализированы (после @FXML загрузки)
            if (productCategoryComboBox != null) productCategoryComboBox.setValue(category);
            if (productNameField != null) productNameField.setText(name);
            if (priceField != null) priceField.setText(priceText);
            if (manufactureDatePicker != null) manufactureDatePicker.setValue(manufactureDate);
            if (expiryDatePicker != null) expiryDatePicker.setValue(expiryDate);
        }
    }

    @FXML
    private void initialize() {
        productCategoryComboBox.getItems().addAll(
                "Молочные продукты",
                "Хлебобулочные изделия",
                "Мясные продукты",
                "Напитки",
                "Фрукты и овощи",
                "Замороженные продукты",
                "Кондитерские изделия",
                "Бакалея",
                "Замороженные продукты",
                "Полуфабрикаты"
        );
        // Можно установить значение по умолчанию
        productCategoryComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void onOkButtonClick(ActionEvent event) {
        category = productCategoryComboBox.getSelectionModel().getSelectedItem();
        name = productNameField.getText();
        priceText = priceField.getText();
        manufactureDate = manufactureDatePicker.getValue();
        expiryDate = expiryDatePicker.getValue();
        if (newProduct != null)
            newProduct = null;
        if (name.isEmpty()) ErrorText.setText("Не указано имя");
        else if (priceText.isEmpty()) ErrorText.setText("Не указана цена");
        else if (category.isEmpty()) ErrorText.setText("Не указана категория товара");
        else if (manufactureDate == null) ErrorText.setText("Не указана дата изготовления");
        else if (expiryDate == null) ErrorText.setText("Не указан срок годности");
        else {
            newProduct = new Product(category, name, Double.parseDouble(priceText), manufactureDate, expiryDate);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }



    public Product getNewProduct() {
        return newProduct == null ? null : newProduct;
    }
}
