package org.example.javafxtest;
import DTO.SQLProductDTO;
import Models.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Builder;
import service.ServiceProduct;
import service.serviceImpl.ServicePrductImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class HelloController {
    ServiceProduct serviceProduct = new ServicePrductImpl();

    @FXML private ComboBox<String> productCategoryComboBox;

    @FXML
    private Label CountProducts;

    @FXML
    private TableColumn<Product, String> productCategoryColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private TableColumn<Product, LocalDate> manufactureDateColumn;

    @FXML
    private TableColumn<Product, LocalDate> expiryDateColumn;

    @FXML
    private TableView<Product> myTable;

    @FXML
    public void initialize() {
        productCategoryComboBox.getItems().addAll(
                "Все",
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
        // Загружаем данные из сервиса
        List<Product> productList = serviceProduct.getAllProducts();
        ObservableList<Product> products = FXCollections.observableArrayList(productList);
        // Устанавливаем данные в TableView
        myTable.setItems(products);
        CountProducts.setText("Кол-во товара: " + String.valueOf(serviceProduct.getProductCount()));

        // Настраиваем колонки
        productCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("productСategory"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        manufactureDateColumn.setCellValueFactory(new PropertyValueFactory<>("manufactureDate"));
        expiryDateColumn.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
    }

    @FXML
    private void openAddProductDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addProduct.fxml"));
            AnchorPane pane = loader.load();

            AddProductController controller = loader.getController();

            // Получаем выбранный продукт из таблицы
            Product selectedProduct = myTable.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                controller.setProductData(selectedProduct);
                myTable.getItems().remove(myTable.getSelectionModel()
                        .getSelectedIndex());
            }

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавить продукт");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(myTable.getScene().getWindow()); // родительское окно
            dialogStage.setScene(new Scene(pane, 300, 400));
            dialogStage.showAndWait();

            Product newProduct = controller.getNewProduct();
            if (newProduct != null) {
                myTable.getItems().add(newProduct);
            }

            List<Product> productList = serviceProduct.getAllProducts();
            ObservableList<Product> products = FXCollections.observableArrayList(productList);
            CountProducts.setText("Кол-во товара: " + String.valueOf(serviceProduct.getProductCount()));
            // Устанавливаем данные в TableView
            myTable.setItems(products);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void OnCloseButtonClick() {
        SQLProductDTO.disconnect();
        Platform.exit();
    }

    @FXML
    public void OnChangeButtonClick() {
        openAddProductDialog();
    }
    @FXML
    public void OnDeleteButtonClick() {
        serviceProduct.deleteProductById(myTable.getSelectionModel().getSelectedItem().getId());
        List<Product> productList = serviceProduct.getAllProducts();
        ObservableList<Product> products = FXCollections.observableArrayList(productList);
        CountProducts.setText("Кол-во товара: " + String.valueOf(serviceProduct.getProductCount()));
        myTable.setItems(products);
    }

    @FXML
    public void OnEditButtonClick() {
        String category = productCategoryComboBox.getSelectionModel().getSelectedItem();
        if (!category.equals("Все")){
            List<Product> productList = serviceProduct.getProductsByCategory(category);
            ObservableList<Product> products = FXCollections.observableArrayList(productList);
            CountProducts.setText("Кол-во товара: " + String.valueOf(productList.size()));
            myTable.setItems(products);
        } else {
            List<Product> productList = serviceProduct.getAllProducts();
            ObservableList<Product> products = FXCollections.observableArrayList(productList);
            CountProducts.setText("Кол-во товара: " + String.valueOf(serviceProduct.getProductCount()));
            myTable.setItems(products);
        }
    }
}