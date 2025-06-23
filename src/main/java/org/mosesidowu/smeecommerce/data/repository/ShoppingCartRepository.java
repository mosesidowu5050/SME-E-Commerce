package org.mosesidowu.smeecommerce.data.repository;

import org.mosesidowu.smeecommerce.data.models.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Map;
import java.util.Optional;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart,String> {

    Optional<ShoppingCart> findByUserId(String userId);

}
