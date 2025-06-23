package org.mosesidowu.smeecommerce.services;

import org.mosesidowu.smeecommerce.data.models.User;
import org.mosesidowu.smeecommerce.data.repository.UserRepository;
import org.mosesidowu.smeecommerce.dtos.requests.UserLoginRequestDTO;
import org.mosesidowu.smeecommerce.dtos.requests.UserRegistrationRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(UserRegistrationRequestDTO userRegistrationRequest) {
        return null;
    }

    @Override
    public JwtResponse login(UserLoginRequestDTO userLoginRequest) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }
}
