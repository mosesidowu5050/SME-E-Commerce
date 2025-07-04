package org.mosesidowu.smeecommerce.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mosesidowu.smeecommerce.data.models.Role;
import org.mosesidowu.smeecommerce.data.repository.UserRepository;
import org.mosesidowu.smeecommerce.dtos.requests.UserLoginRequestDTO;
import org.mosesidowu.smeecommerce.dtos.requests.UserRegistrationRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.JwtResponse;
import org.mosesidowu.smeecommerce.dtos.responses.UserRegisterResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();

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

    private UserRegistrationRequestDTO registerSeller() {
        UserRegistrationRequestDTO registerSeller = new UserRegistrationRequestDTO();
        registerSeller.setFullName("Eric Alli");
        registerSeller.setEmail("ea@gmail.com");
        registerSeller.setPhoneNumber("09092929292");
        registerSeller.setPassword("54321");
        registerSeller.setRole(Role.SELLER);
        return registerSeller;
    }

    private UserRegistrationRequestDTO registerAdmin() {
        UserRegistrationRequestDTO registerAdmin = new UserRegistrationRequestDTO();
        registerAdmin.setFullName("Peejay");
        registerAdmin.setEmail("pj@gmail.com");
        registerAdmin.setPhoneNumber("09093939393");
        registerAdmin.setPassword("12543");
        registerAdmin.setRole(Role.ADMIN);
        return registerAdmin;
    }
    @Test
    public void testToRegisterCustomer_returnResponse_whenUserIsRegisteredSuccessfully() {
        UserRegistrationRequestDTO register = new UserRegistrationRequestDTO();
        register.setFullName("Moses Idowu");
        register.setEmail("md@gmail.com");
        register.setPhoneNumber("09091919191");
        register.setPassword("12345");
        register.setRole(Role.CUSTOMER);


        UserRegisterResponseDTO response = userService.register(register);
        assertNotNull(response);
        assertEquals(1, userRepository.count());
    }

    @Test
    public void testToRegisterSeller_returnResponse_whenUserIsRegisteredSuccessfully() {
        UserRegistrationRequestDTO register = new UserRegistrationRequestDTO();
        register.setFullName("Eric Alli");
        register.setEmail("ea@gmail.com");
        register.setPhoneNumber("09092929292");
        register.setPassword("54321");
        register.setRole(Role.SELLER);


        UserRegisterResponseDTO response = userService.register(register);
        assertNotNull(response);
        assertEquals(1, userRepository.count());
    }

    @Test
    public void testToRegisterAdmin_returnResponse_whenUserIsRegisteredSuccessfully() {
        UserRegistrationRequestDTO register = new UserRegistrationRequestDTO();
        register.setFullName("Peejay");
        register.setEmail("pj@gmail.com");
        register.setPhoneNumber("09093939393");
        register.setPassword("12543");
        register.setRole(Role.ADMIN);


        UserRegisterResponseDTO response = userService.register(register);
        assertNotNull(response);
        assertEquals(1, userRepository.count());
    }
    
    @Test
    public void testToRegister2Customer_1Seller_returnResponse_whenUserIsRegisteredSuccessfully() {
        UserRegistrationRequestDTO register = new UserRegistrationRequestDTO();
        register.setFullName("Eric Alli");
        register.setEmail("ea@gmail.com");
        register.setPhoneNumber("09092929292");
        register.setPassword("54321");
        register.setRole(Role.SELLER);

        UserRegisterResponseDTO response = userService.register(register);
        assertNotNull(response);
        
        UserRegistrationRequestDTO customer1 = new UserRegistrationRequestDTO();
        customer1.setFullName("Moses Idowu");
        customer1.setEmail("md@gmail.com");
        customer1.setPhoneNumber("09091919191");
        customer1.setPassword("12345");
        customer1.setRole(Role.CUSTOMER);

        UserRegisterResponseDTO customerResponse = userService.register(customer1);
        assertNotNull(customerResponse);

        UserRegistrationRequestDTO customer2 = new UserRegistrationRequestDTO();
        customer2.setFullName("Tunde Idowu");
        customer2.setEmail("td@gmail.com");
        customer2.setPhoneNumber("09095919691");
        customer2.setPassword("12345");
        customer2.setRole(Role.CUSTOMER);

        UserRegisterResponseDTO customerResponse2 = userService.register(customer2);
        assertNotNull(customerResponse2);
        assertEquals(3, userRepository.count());
    }

    @Test
    public void testToLogin2Customer_returnResponse_whenUserIsLoginSuccessfully() {
        UserRegistrationRequestDTO registerUser = registerCustomer();
        UserRegisterResponseDTO userResponse = userService.register(registerUser);

        assertNotNull(userResponse);
        UserLoginRequestDTO login = new UserLoginRequestDTO();
        login.setEmail("md@gmail.com");
        login.setPassword("12345");

        JwtResponse response = userService.login(login);
        assertNotNull(response);
        assertEquals(1, userRepository.count());
    }

    @Test
    public void testToLoginSeller_returnResponse_whenUserIsLoginSuccessfully() {
        UserRegistrationRequestDTO registerUser = registerSeller();
        UserRegisterResponseDTO userResponse = userService.register(registerUser);
        assertNotNull(userResponse);

        UserLoginRequestDTO login = new UserLoginRequestDTO();
        login.setEmail("ea@gmail.com");
        login.setPassword("54321");

        JwtResponse response = userService.login(login);
        assertNotNull(response);
        assertEquals(1, userRepository.count());

    }

    @Test
    public void testToLoginAdmin_returnResponse_whenUserIsLoginSuccessfully() {
        UserRegistrationRequestDTO registerUser = registerAdmin();
        UserRegisterResponseDTO userResponse = userService.register(registerUser);
        assertNotNull(userResponse);

        UserLoginRequestDTO login = new UserLoginRequestDTO();
        login.setEmail("pj@gmail.com");
        login.setPassword("12543");

        JwtResponse response = userService.login(login);
        assertNotNull(response);
        assertEquals(1, userRepository.count());

    }


    @Test
    public void testToLoginUser_throwException_whenUserUsesInvalidPassword() {
        UserRegisterResponseDTO userResponse = userService.register(registerAdmin());
        assertNotNull(userResponse);

        UserLoginRequestDTO loginUser = new UserLoginRequestDTO();
        loginUser.setEmail(userResponse.getEmail());
        loginUser.setPassword("12443");

        assertThrows(BadCredentialsException.class, () -> userService.login(loginUser));
      }
    
}


