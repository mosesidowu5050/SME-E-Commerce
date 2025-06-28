package org.mosesidowu.smeecommerce.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("products")
public class Product {

    @Id
    private String productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productQuantity;
    private String productImageUrl;
    private ProductCategory productCategory;
}
