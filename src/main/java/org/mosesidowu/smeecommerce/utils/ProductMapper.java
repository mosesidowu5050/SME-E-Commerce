package org.mosesidowu.smeecommerce.utils;

import org.mosesidowu.smeecommerce.data.models.Product;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.mosesidowu.smeecommerce.dtos.requests.CreateProductRequest;
import org.mosesidowu.smeecommerce.dtos.responses.AllProductsResponse;
import org.mosesidowu.smeecommerce.dtos.responses.CreateProductResponse;

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


    public static void mapProduct(Product product, CreateProductRequest request) {
        product.setProductName(request.getProductName());
        product.setProductDescription(request.getDescription());
        product.setProductPrice(request.getPrice());
        product.setProductQuantity(request.getQuantity());
        product.setProductCategory(ProductCategory.valueOf(request.getCategory()));
    }


    public static CreateProductResponse mapProductToResponse(Product product) {
        CreateProductResponse response = new CreateProductResponse();
        response.setMessage("Product added successfully");
        response.setProductName(product.getProductName());
        response.setDescription(product.getProductDescription());
        response.setPrice(product.getProductPrice());
        response.setQuantity(product.getProductQuantity());
        response.setCategory(product.getProductCategory().name());
        response.setImageUrl(product.getProductImageUrl());
        return response;
    }
}
