package org.mosesidowu.smeecommerce.services;

import org.mosesidowu.smeecommerce.data.models.Product;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.mosesidowu.smeecommerce.dtos.requests.CreateProductRequest;
import org.mosesidowu.smeecommerce.dtos.requests.ProductRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.AllProductResponse;
import org.mosesidowu.smeecommerce.dtos.responses.CreateProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {


    CreateProductResponse createProduct(CreateProductRequest request, MultipartFile imageFile);

    Product updateProduct(String productId, ProductRequestDTO product);

    void deleteProduct(String productId);

    List<AllProductResponse> getProductByCategory(ProductCategory category);

    List<AllProductResponse> searchProducts(String searchTerm);

    List<AllProductResponse> searchProductsByCategoryAndName(String categoryStr, String name);

    List<AllProductResponse> viewAllProducts();

}
