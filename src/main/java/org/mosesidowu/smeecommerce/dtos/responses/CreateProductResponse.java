package org.mosesidowu.smeecommerce.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CreateProductResponse {
    private String message;
    private String productName;
    private String description;
    private double price;
    private int quantity;
    private String imageUrl;
    private String category;

}
