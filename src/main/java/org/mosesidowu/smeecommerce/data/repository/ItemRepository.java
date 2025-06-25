package org.mosesidowu.smeecommerce.data.repository;

import org.mosesidowu.smeecommerce.data.models.Item;
import org.mosesidowu.smeecommerce.data.models.ProductCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String> {

    List<Item> findByProductCategory(ProductCategory productCategory);

    List<Item> findByProductNameContainingIgnoreCase(String productName);

    List<Item> findByProductCategoryContainingIgnoreCase(ProductCategory category);

    List<Item> findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
            String name, String description);

    List<Item> findByProductCategoryAndProductNameContainingIgnoreCase(ProductCategory category, String name);

}
