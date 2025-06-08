package service;

import Models.Product;

import java.util.List;

public interface ServiceProduct {
    Boolean addProduct(Product product);
    Boolean deleteProductById(Long id);
    Boolean updateProduct(Product product);
    List<Product> getAllProducts();
    int getProductCount();
}
