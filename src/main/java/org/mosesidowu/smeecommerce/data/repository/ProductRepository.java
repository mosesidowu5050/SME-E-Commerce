package org.mosesidowu.smeecommerce.data.repository;

import org.mosesidowu.smeecommerce.data.models.Product;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByProductCategory(ProductCategory productCategory);

    List<Product> findByProductNameContainingIgnoreCase(String productName);

}
