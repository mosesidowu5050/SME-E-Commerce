package org.mosesidowu.smeecommerce.dtos.requests;

import lombok.Data;

@Data
public class CreateItemRequest {
    private String productName;
    private String description;
    private double price;
    private int quantity;
    private String category;
    private String imageUrl;

}
