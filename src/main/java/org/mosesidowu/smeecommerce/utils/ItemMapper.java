package org.mosesidowu.smeecommerce.utils;

import org.mosesidowu.smeecommerce.data.models.Product;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.mosesidowu.smeecommerce.dtos.requests.CreateProductRequest;
import org.mosesidowu.smeecommerce.dtos.requests.ProductRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.AllProductResponse;
import org.mosesidowu.smeecommerce.dtos.responses.CreateProductResponse;
import org.mosesidowu.smeecommerce.exception.InvalidCategoryException;
import org.mosesidowu.smeecommerce.exception.UserException;

import java.util.List;
import java.util.stream.Collectors;


public class ItemMapper {
    public static AllProductResponse mapToResponse(Product product) {
        AllProductResponse response = new AllProductResponse();
        response.setProductName(product.getProductName());
        response.setDescription(product.getProductDescription());
        response.setPrice(product.getProductPrice());
        response.setQuantity(product.getProductQuantity());
        response.setCategory(product.getProductCategory().name());
        response.setImageUrl(product.getProductImageUrl());

        return response;
    }


    public static List<AllProductResponse> toAllProductsResponse(List<Product> products) {
        return products.stream()
                .map(ItemMapper::mapToResponse)
                .collect(Collectors.toList());
    }


    public static void mapProduct(Product product, CreateProductRequest request) {
        product.setProductName(request.getProductName());
        product.setProductDescription(request.getDescription());
        product.setProductPrice(request.getPrice());
        product.setProductQuantity(request.getQuantity());
        product.setProductCategory(ProductCategory.valueOf(request.getCategory()));
        product.setProductImageUrl(request.getImageUrl());
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

    public static void updateMapperProductResponse(ProductRequestDTO productDTO, Product existingProduct) {
        existingProduct.setProductName(productDTO.getProductName());
        existingProduct.setProductDescription(productDTO.getProductDescription());
        existingProduct.setProductPrice(productDTO.getProductPrice());
        existingProduct.setProductQuantity(productDTO.getProductQuantity());
        existingProduct.setProductCategory(safeParseCategory(String.valueOf(productDTO.getProductCategory())));
    }


    public static ProductCategory safeParseCategory(String categoryStr) {
        try {
            return ProductCategory.valueOf(categoryStr.trim().toUpperCase());
        } catch (UserException | NullPointerException e) {
            throw new InvalidCategoryException("Invalid product category: " + categoryStr);
        }
    }
}
