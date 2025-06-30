package org.mosesidowu.smeecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.mosesidowu.smeecommerce.data.models.ShoppingCart;
import org.mosesidowu.smeecommerce.dtos.requests.AddToCartRequest;
import org.mosesidowu.smeecommerce.dtos.responses.AllProductResponse;
import org.mosesidowu.smeecommerce.dtos.responses.ApiResponse;
import org.mosesidowu.smeecommerce.dtos.responses.CartItemResponse;
import org.mosesidowu.smeecommerce.exception.UserException;
import org.mosesidowu.smeecommerce.services.ProductService;
import org.mosesidowu.smeecommerce.services.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/cart")
@RestController
@PreAuthorize("hasAuthority('CUSTOMER')")
@Validated
@RequiredArgsConstructor
public class CustomerController {

    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    @GetMapping("/search_products")
    public ResponseEntity<ApiResponse> searchProducts(
            @RequestParam(value = "term", required = false) String searchTerm) {
        try {
            List<AllProductResponse> products = productService.searchProducts(searchTerm);
            return new ResponseEntity<>(new ApiResponse(products, true), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search_by_category_and_name")
    public ResponseEntity<?> searchProductsByCategoryAndName(
            @RequestParam String category,
            @RequestParam String name) {
        try {
            List<AllProductResponse> products = productService.searchProductsByCategoryAndName(category, name);
            return new ResponseEntity<>(new ApiResponse(products, true), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCart(
            @RequestBody AddToCartRequest request,
            @RequestHeader("userId") String userId) {
        try {
            shoppingCartService.addItemToCart(userId, request);
            return ResponseEntity.ok(new ApiResponse("Item added successfully", true));
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getCart(@RequestHeader("userId") String userId) {
        try {
            ShoppingCart cart = shoppingCartService.getCartByUser(userId);
            List<CartItemResponse> response = cart.getItems().stream()
                    .map(item -> new CartItemResponse(
                            item.getProductId(),
                            item.getProductName(),
                            item.getProductPrice(),
                            item.getProductQuantity()i
                    ))
                    .toList();
            return ResponseEntity.ok(new ApiResponse(response, true));
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateQuantity(@RequestHeader("userId") String userId, @RequestParam String productId, @RequestParam int quantity) {
        try {
            shoppingCartService.updateCart(userId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Item updated successfully", true));
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<?> removeItemFromCart(@RequestHeader("userId") String userId, @PathVariable String productId) {
        try {
            shoppingCartService.removeItemFromCart(userId, productId);
            return ResponseEntity.ok(new ApiResponse("Item removed", true));
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> clearCart(@RequestHeader("userId") String userId) {
        try {
            shoppingCartService.clearCart(userId);
            return ResponseEntity.ok(new ApiResponse("Cart cleared successfully", true));
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }
}
