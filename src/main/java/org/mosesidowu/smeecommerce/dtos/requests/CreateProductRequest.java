package org.mosesidowu.smeecommerce.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateProductRequest {
    @NotBlank(message = "This field is required")
    private String productName;

    @NotBlank(message = "This field is required")
    private String description;

    @NotBlank(message = "This field is required")
    private double price;

    @NotBlank(message = "This field is required")
    private int quantity;

    @NotBlank(message = "This field is required")
    private String category;

    @NotBlank(message = "This field is required")
    private String imageUrl;

}
