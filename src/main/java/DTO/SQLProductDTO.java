package DTO;
import Models.Product;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SQLProductDTO {
    private static final String URL = "jdbc:postgresql://localhost:5432/productmarket";
    private static final String USER = "postgres";
    private static final String PASSWORD = "z7J.6q";
    private static Connection connection;

    public static Connection connect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Подключение к базе прошло успешно.");
            return connection;
        } catch (SQLException e) {
            System.out.println("❌ Ошибка подключения: " + e.getMessage());
            return null;
        }
    }

    public static boolean addProduct(Product product) {
        String sql = "INSERT INTO products (product_category, product_name, price, manufacture_date, expiry_date) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, product.getProductСategory());
            stmt.setString(2, product.getProductName());
            stmt.setDouble(3, product.getPrice());
            stmt.setDate(4, java.sql.Date.valueOf(product.getManufactureDate()));
            stmt.setDate(5, java.sql.Date.valueOf(product.getExpiryDate()));
            stmt.executeUpdate();
            System.out.println("Продукт успешно добавлен!");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean deleteProductById(long id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, id);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Запись с id = " + id + " успешно удалена.");
                return true;
            } else {
                System.out.println("Запись с id = " + id + " не найдена.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateProduct(Product product) {
        String sql = "UPDATE products SET product_category = ?, product_name = ?, price = ?, manufacture_date = ?, expiry_date = ? WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql)

            stmt.setString(1, product.getProductСategory());
            stmt.setString(2, product.getProductName());
            stmt.setDouble(3, product.getPrice());
            stmt.setDate(4, java.sql.Date.valueOf(product.getManufactureDate()));
            stmt.setDate(5, java.sql.Date.valueOf(product.getExpiryDate()));
            stmt.setLong(6, product.getId()); // идентификатор записи, которую редактируем

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Продукт успешно обновлён.");
                return true;
            } else {
                System.out.println("Продукт с таким ID не найден.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                long id = rs.getLong("id");
                String category = rs.getString("product_category");
                String name = rs.getString("product_name");
                double price = rs.getDouble("price");
                LocalDate manufactureDate = rs.getDate("manufacture_date").toLocalDate();
                LocalDate expiryDate = rs.getDate("expiry_date").toLocalDate();

                Product product = new Product(id, category, name, price, manufactureDate, expiryDate);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static int getProductCount() {
        String sql = "SELECT COUNT(*) FROM products";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt(1); // получаем значение COUNT(*)
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("🔌 Соединение с базой данных закрыто.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Ошибка при закрытии соединения: " + e.getMessage());
        }
    }
}
