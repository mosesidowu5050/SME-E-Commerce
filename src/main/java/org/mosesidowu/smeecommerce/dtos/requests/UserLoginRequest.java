package org.mosesidowu.smeecommerce.dtos.requests;

import lombok.Data;

@Data
public class UserLoginRequestDTO {

    private String email;
    private String password;

}
