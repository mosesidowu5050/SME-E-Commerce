package org.mosesidowu.smeecommerce.services;


import org.mosesidowu.smeecommerce.data.models.User;
import org.mosesidowu.smeecommerce.dtos.requests.UserLoginRequestDTO;
import org.mosesidowu.smeecommerce.dtos.requests.UserRegistrationRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.JwtResponse;
import org.mosesidowu.smeecommerce.dtos.responses.UserRegisterResponseDTO;

public interface UserService {

    UserRegisterResponseDTO register (UserRegistrationRequestDTO userRegistrationRequest);
    JwtResponse login(UserLoginRequestDTO userLoTginRequest);
    User getUserByEmail(String email);
    void logout(String email);

}
