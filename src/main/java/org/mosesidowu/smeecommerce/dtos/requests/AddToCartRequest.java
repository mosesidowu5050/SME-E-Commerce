package org.mosesidowu.smeecommerce.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AddToCartRequest {
    private String productId;
    private String name;
    private double price;
    private int quantity;
}
