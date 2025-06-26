package org.mosesidowu.smeecommerce.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productQuantity;
    private String productImageUrl;
    private ProductCategory productCategory;
}
