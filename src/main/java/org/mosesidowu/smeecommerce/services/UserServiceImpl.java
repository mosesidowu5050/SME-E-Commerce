package org.mosesidowu.smeecommerce.services;

import org.mosesidowu.smeecommerce.data.models.User;
import org.mosesidowu.smeecommerce.data.repository.UserRepository;
import org.mosesidowu.smeecommerce.dtos.requests.UserLoginRequestDTO;
import org.mosesidowu.smeecommerce.dtos.requests.UserRegistrationRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.JwtResponse;
import org.mosesidowu.smeecommerce.exception.EmailAlreadyExistException;
import org.mosesidowu.smeecommerce.exception.InvalidEmailException;
import org.mosesidowu.smeecommerce.exception.PhoneNumberAlreadyExistException;
import org.mosesidowu.smeecommerce.exception.UserException;
import org.mosesidowu.smeecommerce.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.mosesidowu.smeecommerce.utils.Mapper.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public User register(UserRegistrationRequestDTO userRegistrationRequest) {
        isUserRegistered(userRegistrationRequest);
        User user = registerUserRequest(userRegistrationRequest);
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));

        return userRepository.save(user);
    }


    @Override
    public JwtResponse login(UserLoginRequestDTO userLoginRequest) {
        authenticateUserLogin(userLoginRequest);
        return getJwtResponse(userLoginRequest);
    }



    private JwtResponse getJwtResponse(UserLoginRequestDTO userLoginRequest) {
        User user = userRepository.findUsersByEmail(userLoginRequest.getEmail())
                .orElseThrow(() -> new UserException("User not found"));

        String token = jwtUtil.generateToken(user.getEmail());
        if (token == null) throw new UserException("Failed to generate token");

        return new JwtResponse(token, user.getEmail(), List.of(user.getRole().name()));
    }


    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findUsersByEmail(email)
                .orElseThrow(() -> new UserException("User not found with email: " + email));
        if (user.getEmail() == null || user.getEmail().isEmpty()) throw new InvalidEmailException("Email cannot be null or empty");

        return user;
    }



    private void authenticateUserLogin(UserLoginRequestDTO userLoginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), userLoginRequest.getPassword())
            );
        } catch (Exception e) {
            throw new InvalidEmailException("Invalid email or password");
        }
    }


    private void isUserRegistered(UserRegistrationRequestDTO userRegistrationRequest) {
        if (userRepository.existsByEmail(userRegistrationRequest.getEmail())) throw new EmailAlreadyExistException("Email already exists");
        if (userRepository.existsByPhoneNumber(userRegistrationRequest.getPhoneNumber())) throw new PhoneNumberAlreadyExistException("Phone number already exists");
    }


}
