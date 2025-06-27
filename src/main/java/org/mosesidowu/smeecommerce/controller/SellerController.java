package org.mosesidowu.smeecommerce.controller;

import org.mosesidowu.smeecommerce.data.models.Product;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.mosesidowu.smeecommerce.dtos.requests.CreateProductRequest;
import org.mosesidowu.smeecommerce.dtos.requests.ProductRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.AllProductResponse;
import org.mosesidowu.smeecommerce.dtos.responses.ApiResponse;
import org.mosesidowu.smeecommerce.dtos.responses.CreateProductResponse;
import org.mosesidowu.smeecommerce.exception.UserException;
import org.mosesidowu.smeecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/products")
@Validated
public class SellerController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create_product")
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<CreateProductResponse> createProduct(
            @RequestPart("product") CreateProductRequest request,
            @RequestPart("imageFile") MultipartFile imageFile) {

        CreateProductResponse response = productService.createProduct(request, imageFile);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/updateProduct")
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<?> updateProduct(
            @PathVariable String productId,
            @RequestBody ProductRequestDTO productDTO) {
        try {
            Product updatedProduct = productService.updateProduct(productId, productDTO);
            return new ResponseEntity<>(new ApiResponse(updatedProduct, true), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteProduct")
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<?> deleteProduct(@PathVariable String productId) {
        try {
            productService.deleteProduct(productId);
            return new ResponseEntity<>(new ApiResponse("Deleted successfully", true), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-products")
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<?> getProductByCategory(@PathVariable ProductCategory category){
        try {
            List<AllProductResponse> products = productService.getProductByCategory(category);
            return new ResponseEntity<>(new ApiResponse(products, true), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/search-products")
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<?> searchProducts(@RequestParam String searchTerm){
        try {
            List<Product> products = productService.searchProducts(searchTerm);
            return new ResponseEntity<>(new ApiResponse(products, true), HttpStatus.OK);
        }catch (UserException e){
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/search-products-by-category-and-name")
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<?> searchProductsByCategoryAndName(@RequestParam String categoryStr, String name){
        try {
            List<Product> products = productService.searchProductsByCategoryAndName(categoryStr, name);
            return new ResponseEntity<>(new ApiResponse(products, true), HttpStatus.OK);
        }catch (UserException e){
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }
}