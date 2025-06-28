package org.mosesidowu.smeecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mosesidowu.smeecommerce.dtos.requests.CreateProductRequest;
import org.mosesidowu.smeecommerce.dtos.responses.CreateProductResponse;
import org.mosesidowu.smeecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
@PreAuthorize("hasAuthority('SELLER')")
@RestControllerAdvice
@RequiredArgsConstructor
public class SellerController {

    private final ProductService productService;


    @PostMapping(value = "/create_product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CreateProductResponse> createProduct(
            // Use @ModelAttribute to bind form fields directly to your DTO
            // Remove @RequestPart("product") and @RequestBody as they conflict
            @ModelAttribute @Valid CreateProductRequest request,
            @RequestPart("imageFile") MultipartFile imageFile) {

        CreateProductResponse response = productService.createProduct(request, imageFile);
        return ResponseEntity.ok(response);
    }


//    @PostMapping(value = "/create_product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<String> testUpload(
//            @RequestPart("product") String product,
//            @RequestPart("imageFile") MultipartFile file) {
//        System.out.println("Got product JSON: " + product);
//        System.out.println("Got file: " + file.getOriginalFilename());
//        return ResponseEntity.ok("Success");
//    }


}
