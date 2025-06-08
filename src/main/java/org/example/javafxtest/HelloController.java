package org.example.javafxtest;
import Models.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
import java.io.IOException;
import java.time.LocalDate;

public class HelloController {

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

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавить продукт");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(myTable.getScene().getWindow()); // родительское окно
            dialogStage.setScene(new Scene(pane, 200, 200));
            dialogStage.showAndWait();

            // Можно получить результат из контроллера addProduct.fxml и добавить в таблицу
            AddProductController controller = loader.getController();
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
        // Привязка колонок к свойствам модели
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

}