package org.mosesidowu.smeecommerce.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("shopping_carts")
public class ShoppingCart {

    @Id
    private String shoppingCartId;
    @DBRef
    private List<Item> items;
    private String userId;

}
