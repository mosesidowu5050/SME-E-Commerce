package org.mosesidowu.smeecommerce.data.repository;

import org.mosesidowu.smeecommerce.data.models.ShoppingCart;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ShoppingCartRepository {

    Optional<ShoppingCart> findByUserId(String userId);
    
}
