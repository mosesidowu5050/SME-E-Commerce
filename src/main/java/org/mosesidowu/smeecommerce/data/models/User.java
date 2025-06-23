package org.mosesidowu.smeecommerce.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
public abstract class User {

    @Id
    private String userId;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private Address address;
    private String country;
    private String role;

}
