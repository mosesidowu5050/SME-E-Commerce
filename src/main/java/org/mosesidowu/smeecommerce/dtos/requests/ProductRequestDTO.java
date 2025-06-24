package org.mosesidowu.smeecommerce.dtos.requests;

import lombok.Data;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;

@Data
public class ProductRequestDTO {

    private String productName;
    private String productDescription;
    private double productPrice;
    private int productQuantity;
    private String productImageUrl;
    private ProductCategory productCategory;

}
