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

import java.io.IOException;
import java.time.LocalDate;

public class HelloController {

    @FXML
    private TableColumn<Product, String> productCategoryColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private ObservableList<Product> products;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private TableColumn<Product, LocalDate> manufactureDateColumn;

    @FXML
    private TableColumn<Product, LocalDate> expiryDateColumn;

    @FXML
    public void OnCloseButtonClick() {
        Platform.exit();
    }

    @FXML
    private TableView<Product> myTable;

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void initialize() {
        productCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("productСategory"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        manufactureDateColumn.setCellValueFactory(new PropertyValueFactory<>("manufactureDate"));
        expiryDateColumn.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));

        Product milk = new Product("Молочные продукты", "Молоко", 60.0, LocalDate.of(2025,6,1), LocalDate.of(2025,6,10));
        System.out.println(milk.getId());  // 1

        Product bread = new Product("Хлебобулочные изделия", "Хлеб", 30.0, LocalDate.of(2025,6,5), LocalDate.of(2025,6,8));
        System.out.println(bread.getId());  // 2

        // Создаём начальный список продуктов
        products = FXCollections.observableArrayList(milk, bread);

        // Передаём список в таблицу
        myTable.setItems(products);
    }

    @FXML
    public void OnChangeButtonClick() {
        SQLProductDTO.disconnect();
        openAddProductDialog();
    }
    @FXML
    public void OnDeleteButtonClick() {
        int selectedIndex = myTable.getSelectionModel()
                .getSelectedIndex();
        myTable.getItems().remove(selectedIndex);
    }
}