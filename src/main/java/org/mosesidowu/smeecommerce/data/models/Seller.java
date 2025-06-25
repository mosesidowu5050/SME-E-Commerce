package org.mosesidowu.smeecommerce.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@TypeAlias("seller")
public class Seller extends User {

    @DBRef
    private List<Item> items;

}
