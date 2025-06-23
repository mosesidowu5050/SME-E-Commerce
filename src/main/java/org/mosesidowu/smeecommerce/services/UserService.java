package org.mosesidowu.smeecommerce.services;


import org.mosesidowu.smeecommerce.data.models.User;
import org.mosesidowu.smeecommerce.dtos.requests.UserLoginRequestDTO;
import org.mosesidowu.smeecommerce.dtos.requests.UserRegistrationRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.JwtResponse;

public interface UserService {

    User register (UserRegistrationRequestDTO userRegistrationRequest);
    JwtResponse login(UserLoginRequestDTO userLoginRequest);
    User getUserByEmail(String email);
    void logout(String email);

}
