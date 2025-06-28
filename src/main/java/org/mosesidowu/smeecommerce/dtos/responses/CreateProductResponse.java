package org.mosesidowu.smeecommerce.dtos.responses;

import lombok.Data;

@Data
public class CreateProductResponse {

    private String productId;
    private String message;
    private String productName;
    private String description;
    private double price;
    private int quantity;
    private String imageUrl;
    private String category;

}
