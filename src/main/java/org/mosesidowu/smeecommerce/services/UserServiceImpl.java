package org.mosesidowu.smeecommerce.services;

import lombok.RequiredArgsConstructor;
import org.mosesidowu.smeecommerce.data.models.Role;
import org.mosesidowu.smeecommerce.data.models.User;
import org.mosesidowu.smeecommerce.data.repository.CustomerRepository;
import org.mosesidowu.smeecommerce.data.repository.SellerRepository;
import org.mosesidowu.smeecommerce.data.repository.UserRepository;
import org.mosesidowu.smeecommerce.dtos.requests.UserLoginRequestDTO;
import org.mosesidowu.smeecommerce.dtos.requests.UserRegistrationRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.JwtResponse;
import org.mosesidowu.smeecommerce.dtos.responses.UserRegisterResponseDTO;
import org.mosesidowu.smeecommerce.exception.EmailAlreadyExistException;
import org.mosesidowu.smeecommerce.exception.InvalidEmailException;
import org.mosesidowu.smeecommerce.exception.PhoneNumberAlreadyExistException;
import org.mosesidowu.smeecommerce.exception.UserException;
import org.mosesidowu.smeecommerce.security.JwtUtil;
import org.mosesidowu.smeecommerce.utils.AuthUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.mosesidowu.smeecommerce.utils.Mapper.*;
import static org.mosesidowu.smeecommerce.utils.RoleMapper.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    private final JwtUtil jwtUtil;


    @Override
    public UserRegisterResponseDTO register(UserRegistrationRequestDTO userRegistrationRequest) {
        isUserRegistered(userRegistrationRequest);
        User user = registerUserRequest(userRegistrationRequest);
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        User savedUser = userRepository.save(user);

        if (user.getRole().equals(Role.SELLER)) sellerRepository.save(getSeller(savedUser));
        else if (user.getRole().equals(Role.CUSTOMER)) customerRepository.save(getCustomer(savedUser));
        else throw new UserException("Invalid user role");

        return getUserResponse(savedUser);
    }



    @Override
    public JwtResponse login(UserLoginRequestDTO userLoginRequest) {
        authenticateUserLogin(userLoginRequest);
        return getJwtResponse(userLoginRequest);
    }



    @Override
    public UserRegisterResponseDTO getUserByEmail(String email) {
        User user = userRepository.findUsersByEmail(email)
                .orElseThrow(() -> new UserException("User not found with email: " + email));
        if (user.getEmail() == null || user.getEmail().isEmpty()) throw new InvalidEmailException("Email cannot be null or empty");

        return getUserResponse(user);
    }



    @Override
    public void logout(String email) {
        if (email == null || email.trim().isEmpty()) throw new InvalidEmailException("Email cannot be null or empty");

        userRepository.findUsersByEmail(email)
                .orElseThrow(() -> new UserException("User not found with email: " + email));
    }



    private JwtResponse getJwtResponse(UserLoginRequestDTO userLoginRequest) {
        User user = userRepository.findUsersByEmail(userLoginRequest.getEmail())
                .orElseThrow(() -> new UserException("User not found"));

        String token = jwtUtil.generateToken(user.getEmail());
        if (token == null) throw new UserException("Failed to generate token");

        return new JwtResponse(token, user.getEmail(), List.of(user.getRole().name()));
    }



    private void authenticateUserLogin(UserLoginRequestDTO userLoginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), userLoginRequest.getPassword())
            );
        } catch (UserException e) {
            throw new InvalidEmailException("Invalid email or password");
        }
    }


    private void isUserRegistered(UserRegistrationRequestDTO userRegistrationRequest) {
        if (userRepository.existsByEmail(userRegistrationRequest.getEmail())) throw new EmailAlreadyExistException("Email already exists");
        if (userRepository.existsByPhoneNumber(userRegistrationRequest.getPhoneNumber())) throw new PhoneNumberAlreadyExistException("Phone number already exists");
    }
}
