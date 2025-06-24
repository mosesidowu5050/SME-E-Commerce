package org.mosesidowu.smeecommerce.services;

import org.mosesidowu.smeecommerce.data.models.Product;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.mosesidowu.smeecommerce.dtos.requests.CreateProductRequest;
import org.mosesidowu.smeecommerce.dtos.requests.ProductRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.AllProductsResponse;
import org.mosesidowu.smeecommerce.dtos.responses.CreateProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    CreateProductResponse createProduct(CreateProductRequest request, MultipartFile imageFile);
    Product updateProduct(String productId, ProductRequestDTO product);
    void deleteProduct(String productId);
    List<AllProductsResponse> getProductByCategory(ProductCategory category);
    List<Product> searchProducts(String searchTerm);
}
