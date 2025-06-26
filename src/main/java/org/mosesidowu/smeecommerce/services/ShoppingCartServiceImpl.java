package org.mosesidowu.smeecommerce.services;

import org.mosesidowu.smeecommerce.data.models.ShoppingCart;
import org.mosesidowu.smeecommerce.data.repository.ProductRepository;
import org.mosesidowu.smeecommerce.data.repository.ShoppingCartRepository;
import org.mosesidowu.smeecommerce.dtos.requests.AddToCartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public ShoppingCart getCartByUser(String userId) {
        return shoppingCartRepository.findByUserId(userId).orElseGet(() ->{
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUserId(userId);
            return shoppingCartRepository.save(shoppingCart);
        });
    }

    @Override
    public void addItemToCart(String userId, AddToCartRequest request) {
        ShoppingCart cart = getCartByUser(userId);
        Optional<ShoppingCart.Item> existingItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(request.getProductId())).findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setProductQuantity(existingItem.get().getProductQuantity() + request.getQuantity());
        }else {
            cart.getItems().add(new ShoppingCart.Item(
                    request.getProductId(),
                    request.getName(),
                    request.getPrice(),
                    request.getQuantity()
            ));
        }
        shoppingCartRepository.save(cart);
    }


    @Override
    public void removeItemFromCart(String userId, String productId) {
        ShoppingCart cart = getCartByUser(userId);
        cart.getItems().removeIf(item -> item.getProductId().equals(productId));
        shoppingCartRepository.save(cart);
    }

    @Override
    public void updateCart(String userId, String productId, int quantity) {
        ShoppingCart cart = getCartByUser(userId);
        for (ShoppingCart.Item item : cart.getItems()) {
            if (item.getProductId().equals(productId)){
                item.setProductQuantity(quantity);
                break;
            }
        }
        shoppingCartRepository.save(cart);
    }

    @Override
    public List<ShoppingCart.Item> viewCart(String userId) {
        return getCartByUser(userId).getItems();
    }

    @Override
    public void clearCart(String userId) {
        ShoppingCart cart = getCartByUser(userId);
        cart.getItems().clear();
        shoppingCartRepository.save(cart);
    }
}
