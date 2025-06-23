package org.mosesidowu.smeecommerce.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("users")
@TypeAlias("user")
public class User {

    @Id
    private String userId;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private Address address;
    private String role;

}
