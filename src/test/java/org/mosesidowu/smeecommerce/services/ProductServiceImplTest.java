package org.mosesidowu.smeecommerce.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mosesidowu.smeecommerce.data.models.Product;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.mosesidowu.smeecommerce.data.repository.ProductRepository;
import org.mosesidowu.smeecommerce.dtos.requests.CreateProductRequest;
import org.mosesidowu.smeecommerce.dtos.requests.ProductRequestDTO;
import org.mosesidowu.smeecommerce.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
    public class ProductServiceImplTest {

        @Mock
        private Cloudinary cloudinary;

        @Mock
        private Uploader uploader;

        @Mock
        private ProductServiceImpl productService;

        @Mock
        private ProductRepository productRepository;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
            when(cloudinary.uploader()).thenReturn(uploader);
        }

    public void createProduct_throwsExceptionWhenUploadFails() throws IOException {
        CreateProductRequest request = new CreateProductRequest();
        request.setProductName("Samsung Galaxy");
        request.setDescription("Latest smartphone");
        request.setPrice(800_000.0);
        request.setQuantity(5);
        request.setCategory("ELECTRONICS");
        request.setImageUrl("https://example.com/sample-image.jpg");

        MockMultipartFile image = new MockMultipartFile(
                "image",
                "galaxy.jpg",
                "image/jpeg",
                "new image data".getBytes()
        );

        when(cloudinary.uploader().upload(any(byte[].class), any(Map.class)))
                .thenThrow(new UserException("Missing required parameter - file"));

        UserException exception = assertThrows(UserException.class, () -> {
            productService.createProduct(request, image);
        });

        assertEquals("Failed to upload image: Missing required parameter - file", exception.getMessage());
    }


//    @Test
    public void updateProduct_updatesSuccessfully() {

        Product existingProduct = new Product();
        existingProduct.setProductId(String.valueOf(1L));
        existingProduct.setProductName("my old Phone");
        existingProduct.setProductPrice(500_000.00);
        existingProduct.setProductDescription("Old model");
        existingProduct.setProductQuantity(10);
        existingProduct.setProductCategory(ProductCategory.ELECTRONICS);

        when(productRepository.findById(String.valueOf(1L))).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProductRequestDTO request = new ProductRequestDTO();
        request.setProductName("Iphone 14");
        request.setProductDescription("New model");
        request.setProductPrice(900_000.00);
        request.setProductQuantity(15);
        request.setProductCategory(ProductCategory.ELECTRONICS);


        Product updated = productService.updateProduct(String.valueOf(1L), request);

        assertNotNull(updated);
        assertEquals("Iphone 14", updated.getProductName());
        assertEquals(900_000.00, updated.getProductPrice());
        assertEquals("New model", updated.getProductDescription());
        assertEquals(15, updated.getProductQuantity());
        assertEquals(ProductCategory.ELECTRONICS, updated.getProductCategory());

        verify(productRepository, times(1)).findById(String.valueOf(1L));
        verify(productRepository, times(1)).save(existingProduct);
    }

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