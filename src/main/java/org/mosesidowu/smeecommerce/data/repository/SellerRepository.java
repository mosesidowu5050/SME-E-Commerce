package org.mosesidowu.smeecommerce.data.repository;

import org.mosesidowu.smeecommerce.data.models.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SellerRepository extends MongoRepository<Seller,String> {

    Optional<Seller> findByUserId(String userId);

    Optional<Seller> findByEmail(String email);
}
