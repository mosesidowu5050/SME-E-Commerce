package org.mosesidowu.smeecommerce.services;

import com.cloudinary.Cloudinary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mosesidowu.smeecommerce.data.models.Product;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.mosesidowu.smeecommerce.data.repository.ProductRepository;
import org.mosesidowu.smeecommerce.dtos.requests.CreateProductRequest;
import org.mosesidowu.smeecommerce.dtos.requests.ProductRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.AllProductsResponse;
import org.mosesidowu.smeecommerce.dtos.responses.CreateProductResponse;
import org.mosesidowu.smeecommerce.exception.ProductNotFoundException;
import org.mosesidowu.smeecommerce.exception.UnauthorizedActionException;
import org.mosesidowu.smeecommerce.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Cloudinary cloudinary;

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();
    }
    @Test
    public void createProduct_throwsExceptionWhenUploadFails() throws IOException {
        CreateProductRequest request = new CreateProductRequest();
        request.setProductName("Samsung Galaxy");
        request.setDescription("Latest smartphone");
        request.setPrice(800.0);
        request.setQuantity(20);
        request.setCategory("ELECTRONICS");
        request.setImageUrl("https://hudsonaeast.pages.dev/ryjdy-new-samsung-phone-s24-ultra-2025-images-ljgs/");

        MockMultipartFile image = new MockMultipartFile("image", "galaxy.jpg", "image/jpeg", "image data".getBytes());
        when(cloudinary.uploader().upload(any(byte[].class), any(Map.class))).thenReturn(Map.of("url", "http://cloudinary.com/spectre.jpg"));

        assertThrows(UserException.class, () -> productService.createProduct(request, image));
    }

//    @Test
//    void createProduct_throwsExceptionOnUploadFailure() {
//        CreateProductRequest request = new CreateProductRequest();
//        request.setProductName("Samsung Galaxy");
//        request.setDescription("Latest smartphone");
//        request.setPrice(800.0);
//        request.setQuantity(20);
//        request.setCategory("ELECTRONICS");
//
//        MockMultipartFile image = new MockMultipartFile("image", "galaxy.jpg", "image/jpeg", "image data".getBytes());
//        when(cloudinary.uploader().upload(any(byte[].class), any(Map.class))).thenThrow(new RuntimeException("Upload failed"));
//
//        assertThrows(UserException.class, () -> productService.createProduct(request, image));
//    }
//
//    @Test
//    void updateProduct_updatesSuccessfully() {
//        Product product = new Product();
//        product.setProductId("123");
//        product.setProductName("Old Phone");
//        product.setProductPrice(500.0);
//        productRepository.save(product);
//
//        ProductRequestDTO dto = new ProductRequestDTO();
//        dto.setProductName("Iphone 14");
//        dto.setProductDescription("New model");
//        dto.setProductPrice(900.0);
//        dto.setProductQuantity(15);
//        dto.setProductCategory(ProductCategory.ELECTRONICS);
//
//        Product updated = productService.updateProduct("123", dto);
//
//        assertEquals("Iphone 14", updated.getProductName());
//        assertEquals(900.0, updated.getProductPrice());
//    }
//
//    @Test
//    void updateProduct_throwsProductNotFound() {
//        ProductRequestDTO dto = new ProductRequestDTO();
//        dto.setProductName("Iphone 14");
//
//        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct("invalid", dto));
//    }
//
//    @Test
//    @WithMockUser(username = "chidi.o@gmail.com")
//    void deleteProduct_deletesSuccessfully() {
//        Product product = new Product();
//        product.setProductId("123");
//        product.setProductName("chidi.o@gmail.com");
//        productRepository.save(product);
//
//        productService.deleteProduct("123");
//
//        assertEquals(0, productRepository.count());
//    }
//
//    @Test
//    @WithMockUser(username = "chidi.o@gmail.com")
//    void deleteProduct_throwsUnauthorized() {
//        Product product = new Product();
//        product.setProductId("123");
//        product.setProductName("amaka.n@gmail.com");
//        productRepository.save(product);
//
//        assertThrows(UnauthorizedActionException.class, () -> productService.deleteProduct("123"));
//    }
//
//    @Test
//    void getProductByCategory_returnsProducts() {
//        Product product = new Product();
//        product.setProductId("123");
//        product.setProductName("Samsung Galaxy");
//        product.setProductCategory(ProductCategory.ELECTRONICS);
//        productRepository.save(product);
//
//        List<AllProductsResponse> responses = productService.getProductByCategory(ProductCategory.ELECTRONICS);
//
//        assertEquals(1, responses.size());
//        assertEquals("Samsung Galaxy", responses.get(0).getProductName());
//    }
//
//    @Test
//    void getProductByCategory_returnsEmptyList() {
//        List<AllProductsResponse> responses = productService.getProductByCategory(ProductCategory.FASHION);
//        assertTrue(responses.isEmpty());
//    }
//
//    @Test
//    void searchProducts_returnsEmptyList() {
//        List<Product> products = productService.searchProducts("phone");
//        assertTrue(products.isEmpty());
//    }
}