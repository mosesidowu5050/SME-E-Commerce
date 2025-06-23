package org.mosesidowu.smeecommerce.dtos.requests;

import lombok.Data;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;

@Data
public class ProductRequestDTO {

    private String productName;
    private String productDescription;
    private String productPrice;
    private ProductCategory productCategory;

}
