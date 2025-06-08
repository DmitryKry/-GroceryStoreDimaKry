package org.example.javafxtest;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

public class HelloController {
    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private TableColumn<Product, LocalDate> manufactureDateColumn;

    @FXML
    private TableColumn<Product, LocalDate> expiryDateColumn;


    @FXML
    private Label welcomeText;

    @FXML
    private Label nextText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Я не знаю, хз");
    }

    @FXML
    protected void onNextButtonClick() {
        nextText.setText("ОООООО, победа, вместо обеда");
    }

    @FXML
    protected void onClearButtonClick() {
        welcomeText.setText("");
        nextText.setText("");
    }
    @FXML
    private TableView<Product> myTable;
}