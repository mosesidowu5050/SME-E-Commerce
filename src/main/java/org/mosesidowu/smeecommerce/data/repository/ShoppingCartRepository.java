package org.mosesidowu.smeecommerce.services;

import org.mosesidowu.smeecommerce.data.models.ShoppingCart;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ShoppingCartService {

    Optional<ShoppingCart> findByUserId(String userId);
    
}
