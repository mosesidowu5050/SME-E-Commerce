package org.mosesidowu.smeecommerce.services;

import org.mosesidowu.smeecommerce.data.models.ShoppingCart;

public interface ShoppingCartService {

    void addItemToCart(String userId, String productId, int quantity);
    void removeItemFromCart(String userId, String productId);
    ShoppingCart viewCart(String userId);
    void clearCart(String userId);

}
