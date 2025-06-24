package org.mosesidowu.smeecommerce.utils;

import org.mosesidowu.smeecommerce.data.models.Product;
import org.mosesidowu.smeecommerce.dtos.responses.AllProductsResponse;

import java.util.List;
import java.util.stream.Collectors;


public class ProductMapper {
    public static AllProductsResponse mapToResponse(Product product) {
        AllProductsResponse response = new AllProductsResponse();
        response.setProductName(product.getProductName());
        response.setDescription(product.getProductDescription());
        response.setPrice(product.getProductPrice());
        response.setQuantity(product.getProductQuantity());
        response.setCategory(product.getProductCategory().name());
        response.setImageUrl(product.getProductImageUrl());
        return response;
    }
    public static List<AllProductsResponse> toAllProductsResponse(List<Product> products) {
        return products.stream()
                .map(ProductMapper::mapToResponse)
                .collect(Collectors.toList());
    }
}

