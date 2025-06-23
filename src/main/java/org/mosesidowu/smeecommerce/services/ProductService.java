package org.mosesidowu.smeecommerce.services;

import org.mosesidowu.smeecommerce.data.models.Product;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.mosesidowu.smeecommerce.dtos.requests.ProductRequestDTO;

import java.util.List;

public interface ProductService {

    Product createProduct(ProductRequestDTO product);
    Product updateProduct(String productId, ProductRequestDTO product);
    void deleteProduct(String productId);
    List<Product> getProductByCategory(ProductCategory category);
    List<Product> searchProducts(String searchTerm);
}
