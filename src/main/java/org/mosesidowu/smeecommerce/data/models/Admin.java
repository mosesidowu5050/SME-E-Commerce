package org.mosesidowu.smeecommerce.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@TypeAlias("admin")
public class Admin extends User {

    private List<String> permissions;
}
