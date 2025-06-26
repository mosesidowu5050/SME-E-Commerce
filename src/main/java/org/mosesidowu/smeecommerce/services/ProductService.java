package org.mosesidowu.smeecommerce.services;

import org.mosesidowu.smeecommerce.data.models.Product;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.mosesidowu.smeecommerce.dtos.requests.CreateItemRequest;
import org.mosesidowu.smeecommerce.dtos.requests.ItemRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.AllItemResponse;
import org.mosesidowu.smeecommerce.dtos.responses.CreateItemResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    CreateItemResponse createProduct(CreateItemRequest request, MultipartFile imageFile);

    Product updateProduct(String productId, ItemRequestDTO product);

    void deleteProduct(String productId);

    List<AllItemResponse> getProductByCategory(ProductCategory category);

    List<Product> searchProducts(String searchTerm);

    List<Product> searchProductsByCategoryAndName(String categoryStr, String name);
}
