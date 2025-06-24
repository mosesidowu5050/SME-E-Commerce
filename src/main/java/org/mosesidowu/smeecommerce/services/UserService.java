package org.mosesidowu.smeecommerce.services;

import org.mosesidowu.smeecommerce.dtos.requests.UserLoginRequestDTO;
import org.mosesidowu.smeecommerce.dtos.requests.UserRegistrationRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.JwtResponse;
import org.mosesidowu.smeecommerce.dtos.responses.UserRegisterResponseDTO;

public interface UserService {

    UserRegisterResponseDTO register (UserRegistrationRequestDTO userRegistrationRequest);
    JwtResponse login(UserLoginRequestDTO userLoginRequest);
    UserRegisterResponseDTO getUserByEmail(String email);
    void logout(String email);

}
