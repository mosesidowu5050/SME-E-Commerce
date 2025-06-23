package org.mosesidowu.smeecommerce.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("items")
public class Item {

    @Id
    private String itemId;
    private int quantityOfProducts;
    @DBRef
    private List<Product> productList;

}
