package org.mosesidowu.smeecommerce.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AllProductResponse {
    private String productName;
    private String description;
    private double price;
    private int quantity;
    private String imageUrl;
    private String category;

   }
