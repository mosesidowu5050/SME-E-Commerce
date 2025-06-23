package org.mosesidowu.smeecommerce.dtos.responses;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {

    private String token;
    private String email;
    private List<String> roles;

}
