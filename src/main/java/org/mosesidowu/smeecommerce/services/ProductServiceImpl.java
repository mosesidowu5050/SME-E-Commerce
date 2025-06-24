package org.mosesidowu.smeecommerce.services;

import org.mosesidowu.smeecommerce.data.models.Product;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.mosesidowu.smeecommerce.data.repository.ProductRepository;
import org.mosesidowu.smeecommerce.dtos.requests.ProductRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(ProductRequestDTO product) {
        return null;
    }

    @Override
    public Product updateProduct(String productId, ProductRequestDTO product) {
        return null;
    }

    @Override
    public void deleteProduct(String productId) {

    }

    @Override
    public List<Product> getProductByCategory(ProductCategory category) {
        return List.of();
    }

    @Override
    public List<Product> searchProducts(String searchTerm) {
        return List.of();
    }
}
