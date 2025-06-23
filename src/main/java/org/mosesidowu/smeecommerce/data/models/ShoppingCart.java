package org.mosesidowu.smeecommerce.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "shopping_carts")
public class ShoppingCart {

    @Id
    private String shoppingCartId;
    private List<Item> item;
    private String userId;

}
