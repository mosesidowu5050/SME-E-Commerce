package org.mosesidowu.smeecommerce.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("products")
public class Product {

    @Id
    private String productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private ProductCategory productCategory;

}
