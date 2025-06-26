package org.mosesidowu.smeecommerce.utils;

import org.mosesidowu.smeecommerce.data.models.Product;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.mosesidowu.smeecommerce.dtos.requests.CreateItemRequest;
import org.mosesidowu.smeecommerce.dtos.requests.ItemRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.AllItemResponse;
import org.mosesidowu.smeecommerce.dtos.responses.CreateItemResponse;
import org.mosesidowu.smeecommerce.exception.InvalidCategoryException;
import org.mosesidowu.smeecommerce.exception.UserException;

import java.util.List;
import java.util.stream.Collectors;


public class ItemMapper {
    public static AllItemResponse mapToResponse(Product product) {
        AllItemResponse response = new AllItemResponse();
        response.setProductName(product.getProductName());
        response.setDescription(product.getProductDescription());
        response.setPrice(product.getProductPrice());
        response.setQuantity(product.getProductQuantity());
        response.setCategory(product.getProductCategory().name());
        response.setImageUrl(product.getProductImageUrl());

        return response;
    }


    public static List<AllItemResponse> toAllProductsResponse(List<Product> products) {
        return products.stream()
                .map(ItemMapper::mapToResponse)
                .collect(Collectors.toList());
    }


    public static void mapProduct(Product product, CreateItemRequest request) {
        product.setProductName(request.getProductName());
        product.setProductDescription(request.getDescription());
        product.setProductPrice(request.getPrice());
        product.setProductQuantity(request.getQuantity());
        product.setProductCategory(ProductCategory.valueOf(request.getCategory()));
        product.setProductImageUrl(request.getImageUrl());
    }


    public static CreateItemResponse mapProductToResponse(Product product) {
        CreateItemResponse response = new CreateItemResponse();
        response.setMessage("Product added successfully");
        response.setProductName(product.getProductName());
        response.setDescription(product.getProductDescription());
        response.setPrice(product.getProductPrice());
        response.setQuantity(product.getProductQuantity());
        response.setCategory(product.getProductCategory().name());
        response.setImageUrl(product.getProductImageUrl());

        return response;
    }

    public static void updateMapperProductResponse(ItemRequestDTO productDTO, Product existingProduct) {
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
