package org.mosesidowu.smeecommerce.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CreateProductRequest {
    private String productName;
    private String description;
    private double price;
    private int quantity;
    private String imageUrl;
    private String category;

}
