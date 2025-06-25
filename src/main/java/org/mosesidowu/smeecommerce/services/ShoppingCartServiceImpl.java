package org.mosesidowu.smeecommerce.services;

import org.mosesidowu.smeecommerce.data.models.ShoppingCart;
import org.mosesidowu.smeecommerce.data.repository.ProductRepository;
import org.mosesidowu.smeecommerce.data.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ProductRepository productRepository;



    @Override
    public void addItemToCart(String userId, String productId, int quantity) {

    }

    @Override
    public void removeItemFromCart(String userId, String productId) {

    }

    @Override
    public ShoppingCart viewCart(String userId) {
        return null;
    }

    @Override
    public void clearCart(String userId) {

    }
}
