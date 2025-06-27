package org.mosesidowu.smeecommerce.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemResponse {
    private String productId;
    private String name;
    private double price;
    private int quantity;

}
