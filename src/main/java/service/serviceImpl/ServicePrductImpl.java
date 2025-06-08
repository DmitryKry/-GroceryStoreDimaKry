package service.serviceImpl;

import DTO.SQLProductDTO;
import Models.Product;
import service.ServiceProduct;

import java.util.List;

public class ServicePrductImpl implements ServiceProduct {
    @Override
    public Boolean addProduct(Product product) {
        return SQLProductDTO.addProduct(product);
    }

    @Override
    public Boolean deleteProductById(Long id) {
        return SQLProductDTO.deleteProductById(id);
    }

    @Override
    public Boolean updateProduct(Product product) {
        return SQLProductDTO.updateProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return SQLProductDTO.getAllProducts();
    }

    @Override
    public int getProductCount() {
        return SQLProductDTO.getProductCount();
    }
}
