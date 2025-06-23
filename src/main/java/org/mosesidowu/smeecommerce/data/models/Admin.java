package org.mosesidowu.smeecommerce.data.models;

import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.TypeAlias;


import java.util.List;

@EqualsAndHashCode(callSuper = true)
@TypeAlias("admin")
public class Admin extends User {

    private List<String> permissions;
}
