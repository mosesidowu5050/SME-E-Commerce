package org.mosesidowu.smeecommerce.dtos.requests;

import lombok.Data;

@Data
public class UserRegistrationRequestDTO {

    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;

}
