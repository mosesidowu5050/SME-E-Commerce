package org.mosesidowu.smeecommerce.controller;

import org.mosesidowu.smeecommerce.dtos.requests.CreateProductRequest;
import org.mosesidowu.smeecommerce.dtos.responses.ApiResponse;
import org.mosesidowu.smeecommerce.dtos.responses.CreateProductResponse;
import org.mosesidowu.smeecommerce.exception.UserException;
import org.mosesidowu.smeecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create_product")
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<CreateProductResponse> createProduct(
            @RequestPart("product") CreateProductRequest request,
            @RequestPart("imageFile") MultipartFile imageFile

    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("Authenticated user: " + auth.getName());
//        System.out.println("Authorities: " + auth.getAuthorities());
        CreateProductResponse response = productService.createProduct(request, imageFile);


        return ResponseEntity.ok(response);
    }

}
