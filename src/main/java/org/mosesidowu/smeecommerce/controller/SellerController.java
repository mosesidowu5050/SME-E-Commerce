package org.mosesidowu.smeecommerce.controller;

import jakarta.validation.Valid;
import org.mosesidowu.smeecommerce.dtos.requests.CreateProductRequest;
import org.mosesidowu.smeecommerce.dtos.responses.CreateProductResponse;
import org.mosesidowu.smeecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/products")
@PreAuthorize("hasAuthority('SELLER')")
@Validated
public class SellerController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create_product")
    public ResponseEntity<CreateProductResponse> createProduct(
            @Valid @RequestPart("product") CreateProductRequest request,
            @RequestPart("imageFile") MultipartFile imageFile) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CreateProductResponse response = productService.createProduct(request, imageFile);

        return ResponseEntity.ok(response);
    }

}
