package org.mosesidowu.smeecommerce.controller;

import org.mosesidowu.smeecommerce.data.models.ShoppingCart;
import org.mosesidowu.smeecommerce.dtos.requests.AddToCartRequest;
import org.mosesidowu.smeecommerce.dtos.responses.ApiResponse;
import org.mosesidowu.smeecommerce.dtos.responses.CartItemResponse;
import org.mosesidowu.smeecommerce.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequestMapping("/api/cart")
@RestController
public class CartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public ResponseEntity<?> addCart(@RequestBody AddToCartRequest request, @RequestHeader("userId") String userId) {
        shoppingCartService.addItemToCart(userId, request);
        return ResponseEntity.ok(new ApiResponse("Item added successfully",true));
    }

    @GetMapping
    public ResponseEntity<?> getCart(@RequestHeader("userId") String userId) {
        ShoppingCart cart = shoppingCartService.getCartByUser(userId);
        List<CartItemResponse> response = cart.getItems().stream()
                .map(item -> new CartItemResponse(
                        item.getProductId(),
                        item.getProductName(),
                        item.getProductPrice(),
                        item.getProductQuantity()
                ))
        .toList();
        return ResponseEntity.ok(new ApiResponse(response, true));
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateQuantity(@RequestHeader("userId") String userId, @RequestParam String productId, @RequestParam int quantity) {
        shoppingCartService.updateCart(userId,productId, quantity);
        return ResponseEntity.ok(new ApiResponse("Item updated successfully",true));
    }
    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<?> removeItemFromCart(@RequestHeader("userId") String userId, @PathVariable String productId) {
        shoppingCartService.removeItemFromCart(userId, productId);
        return ResponseEntity.ok(new ApiResponse("Item removed",true));
    }
    @DeleteMapping("/clear")
    public ResponseEntity<?> clearCart(@RequestHeader("userId") String userId) {
        shoppingCartService.clearCart(userId);
        return ResponseEntity.ok(new ApiResponse("Cart cleared successfully",true));
    }
}