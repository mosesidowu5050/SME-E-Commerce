package org.mosesidowu.smeecommerce.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.mosesidowu.smeecommerce.data.models.Role;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponse {

    private String token;
    private String email;
    private List<String> roles;

}
