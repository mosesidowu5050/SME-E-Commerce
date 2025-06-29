package org.mosesidowu.smeecommerce.controller;

import jakarta.validation.Valid;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.mosesidowu.smeecommerce.data.models.Product;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.mosesidowu.smeecommerce.dtos.requests.CreateProductRequest;
import org.mosesidowu.smeecommerce.dtos.requests.ProductRequestDTO;
import org.mosesidowu.smeecommerce.dtos.responses.AllProductResponse;
import org.mosesidowu.smeecommerce.dtos.responses.ApiResponse;
import org.mosesidowu.smeecommerce.dtos.responses.CreateProductResponse;
import org.mosesidowu.smeecommerce.dtos.responses.ProductResponse;
import org.mosesidowu.smeecommerce.exception.UserException;
import org.mosesidowu.smeecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/products")
@PreAuthorize("hasAuthority('SELLER')")
@RestControllerAdvice
@RequiredArgsConstructor
public class SellerController {

    private final ProductService productService;


    @PostMapping(value = "/create_product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CreateProductResponse> createProduct(
            @ModelAttribute @Valid CreateProductRequest request,
            @RequestPart("imageFile") MultipartFile imageFile) {

        CreateProductResponse response = productService.createProduct(request, imageFile);
        return ResponseEntity.ok(response);
    }



    @PostMapping("/update_product/{productId}")
    public ResponseEntity<?> updateProduct(
            @PathVariable String productId,
            @RequestBody @Valid ProductRequestDTO productRequest) {

        try {
            Product product = productService.updateProduct(productId, productRequest);
            return new ResponseEntity<>(new ApiResponse(product, true), HttpStatus.OK);
        }
        catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/delete_product/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productId) {
        try {
            productService.deleteProduct(productId);
            return new ResponseEntity<>(new ApiResponse("Item deleted successfully", true), HttpStatus.OK);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(e.getMessage(), false));
        }
    }


    @GetMapping("/view_all_products")
    public ResponseEntity<?> viewAllProducts() {
        try {
            List<AllProductResponse>  allProducts = productService.viewAllProducts();
            return new ResponseEntity<>(new ApiResponse(allProducts, true), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/get_product_by_category/{category}")
    public ResponseEntity<?> getProductByCategory(@PathVariable String category) {
        try {
            List<AllProductResponse> allProductResponses = productService.getProductByCategory(ProductCategory.valueOf(category));
            return new ResponseEntity<>(new ApiResponse(allProductResponses, true), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/search_products")
    public ResponseEntity<?> searchProducts(@RequestParam String searchTerm) {
        try {
            List<Product> products = productService.searchProducts(searchTerm);
            return new ResponseEntity<>(new ApiResponse(products, true), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/search_products_by_category_and_name")
    public ResponseEntity<?> searchProductsByCategoryAndName(
            @RequestParam String category,
            @RequestParam String name) {
        try {
            List<Product> products = productService.searchProductsByCategoryAndName(category, name);
            return new ResponseEntity<>(new ApiResponse(products, true), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }




}
