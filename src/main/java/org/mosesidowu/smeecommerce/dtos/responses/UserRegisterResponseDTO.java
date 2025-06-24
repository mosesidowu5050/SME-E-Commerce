package org.mosesidowu.smeecommerce.dtos.responses;

import lombok.Data;
import org.mosesidowu.smeecommerce.data.models.Role;

@Data
public class UserRegisterResponseDTO {

    private  String userId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Role role;

}
