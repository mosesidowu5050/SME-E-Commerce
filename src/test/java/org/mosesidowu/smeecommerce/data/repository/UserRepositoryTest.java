package org.mosesidowu.smeecommerce.data.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mosesidowu.smeecommerce.data.models.Address;
import org.mosesidowu.smeecommerce.data.models.Role;
import org.mosesidowu.smeecommerce.data.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        Address address = new Address();
        address.setHouseNumber("123");
        address.setStreet("Herbert Macaulay");
        address.setCity("Yaba");
        address.setState("Lagos");
        address.setCountry("Nigeria");


        User user = new User();
        user.setFullName("Moses Idowu");
        user.setEmail("m.d@gmail.com");
        user.setPassword("12345");
        user.setPhoneNumber("09087654321");
        user.setAddress(address);
        user.setRole(Role.CUSTOMER);
        userRepository.save(user);
    }

    @Test
    public void testFindByPhoneNumber() {
        Optional<User> user = userRepository.findByPhoneNumber("09087654321");
        assertTrue(user.isPresent());
    }

    @Test
    public void existsByEmail() {
        boolean emailExists = userRepository.existsByEmail("m.d@gmail.com");
        assertTrue(emailExists);
    }
    
    @Test
    public void existsByPhoneNumber(){
        boolean numberExists = userRepository.existsByPhoneNumber("09087654321");
        assertTrue(numberExists);
    }

    @Test
    public void FindUserByEmail(){
        Optional<User> found = userRepository.findUsersByEmail("m.d@gmail.com") ;
        assertTrue(found.isPresent());
    }

}