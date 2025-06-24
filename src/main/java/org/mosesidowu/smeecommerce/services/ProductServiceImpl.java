package org.mosesidowu.smeecommerce.services;

import org.mosesidowu.smeecommerce.data.models.Product;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.mosesidowu.smeecommerce.data.repository.ProductRepository;
import org.mosesidowu.smeecommerce.dtos.requests.ProductRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.AllProductsResponse;
import org.mosesidowu.smeecommerce.utils.ProductMapper;
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
    public List<AllProductsResponse> getProductByCategory(ProductCategory category) {
        List<Product> products = productRepository.findByProductCategoryContainingIgnoreCase(category);
        return ProductMapper.toAllProductsResponse(products);
    }

    @Override
    public List<Product> searchProducts(String searchTerm) {
        return List.of();
    }
}
