package org.mosesidowu.smeecommerce.dtos.responses;

import lombok.Data;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;

@Data
public class ProductResponse {

    private String productName;
    private String productDescription;
    private double productPrice;
    private int productQuantity;
    private String productImageUrl;
    private ProductCategory productCategory;
}
