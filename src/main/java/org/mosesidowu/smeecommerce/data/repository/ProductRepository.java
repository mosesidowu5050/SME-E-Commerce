package org.mosesidowu.smeecommerce.data.repository;

import org.mosesidowu.smeecommerce.data.models.Product;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.mosesidowu.smeecommerce.dtos.responses.ProductResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByProductCategory(ProductCategory productCategory);

    List<Product> findByProductNameContainingIgnoreCase(String productName);

    List<Product> findByProductCategoryContainingIgnoreCase(ProductCategory category);

    List<Product> findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
            String name, String description);

    List<Product> findByProductCategoryAndProductNameContainingIgnoreCase(ProductCategory category, String name);

    List<Product> findByCreatedBy(String currentSellerEmail);
}
