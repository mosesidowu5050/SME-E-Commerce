package org.mosesidowu.smeecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.mosesidowu.smeecommerce.dtos.requests.CreateProductRequest;
import org.mosesidowu.smeecommerce.dtos.responses.ApiResponse;
import org.mosesidowu.smeecommerce.dtos.responses.CreateProductResponse;
import org.mosesidowu.smeecommerce.exception.UserException;
import org.mosesidowu.smeecommerce.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = "/create_product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createProduct(
            @RequestPart("product") CreateProductRequest request,
            @RequestPart("image") MultipartFile imageFile) {
        try {
            CreateProductResponse response = productService.createProduct(request, imageFile);
            return new ResponseEntity<>(new ApiResponse(response, true), HttpStatus.OK);
        } catch (UserException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), false));
        }
    }

}
