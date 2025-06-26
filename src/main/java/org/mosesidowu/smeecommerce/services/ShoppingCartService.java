package org.mosesidowu.smeecommerce.services;

import org.mosesidowu.smeecommerce.data.models.ShoppingCart;
import org.mosesidowu.smeecommerce.dtos.requests.AddToCartRequest;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCart getCartByUser(String userId);
    void addItemToCart(String userId, AddToCartRequest request);
    void removeItemFromCart(String userId, String productId);
    void clearCart(String userId);

    void updateCart(String userId, String productId, int quantity);

    List<ShoppingCart.Item> viewCart(String userId);

}
