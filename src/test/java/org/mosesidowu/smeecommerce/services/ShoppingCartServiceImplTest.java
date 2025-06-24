package org.mosesidowu.smeecommerce.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mosesidowu.smeecommerce.data.models.Product;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.mosesidowu.smeecommerce.data.models.Role;
import org.mosesidowu.smeecommerce.data.repository.ProductRepository;
import org.mosesidowu.smeecommerce.data.repository.ShoppingCartRepository;
import org.mosesidowu.smeecommerce.data.repository.UserRepository;
import org.mosesidowu.smeecommerce.dtos.requests.UserRegistrationRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.UserRegisterResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.annotation.SecurityTestExecutionListeners;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ShoppingCartServiceImplTest {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductRepository productRepository;


    @BeforeEach
    void setUp() {
        shoppingCartRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
    }

    private UserRegistrationRequestDTO registerCustomer() {
        UserRegistrationRequestDTO registerCustomer = new UserRegistrationRequestDTO();
        registerCustomer.setFullName("Moses Idowu");
        registerCustomer.setEmail("md@gmail.com");
        registerCustomer.setPhoneNumber("09091919191");
        registerCustomer.setPassword("12345");
        registerCustomer.setRole(Role.CUSTOMER);
        return registerCustomer;
    }

    private Product createProduct() {
        Product product = new Product();
        product.setProductName("myProduct");
        product.setProductDescription("A skincare product");
        product.setProductPrice(5000.00);
        product.setProductQuantity(10);
        product.setProductImageUrl("http://skincare.com/product.jpg");
        product.setProductCategory(ProductCategory.BEAUTY_AND_HEALTH);
        return productRepository.save(product);
    }

    @Test
    public void testAddItemToCart_returnCount_whenItemIsAddedToCart() {
            UserRegistrationRequestDTO registerUser = registerCustomer();
            UserRegisterResponseDTO response = userService.register(registerUser);
            assertNotNull(response);

            createProduct();

        String userId = response.getUserId();
        String productId = createProduct().getProductId();
        int quantity = 1;

        shoppingCartService.addItemToCart(userId, productId, quantity);
        assertEquals(1, shoppingCartRepository.count());

    }

}