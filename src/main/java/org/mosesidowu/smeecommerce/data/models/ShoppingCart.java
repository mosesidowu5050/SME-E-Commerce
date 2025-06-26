package org.mosesidowu.smeecommerce.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("shopping_carts")
public class ShoppingCart {

    @Id
    private String shoppingCartId;
    @DBRef
    private List<Item> items = new ArrayList<>();
    private List<Product> productsList;
    private String userId;

    
@Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Item {
        private String productId;
        private String productName;
        private String productDescription;
        private double productPrice;
        private int productQuantity;
        private String productImageUrl;
        private ProductCategory productCategory;

    public Item(String productId, String name, double price, int quantity) {
    }
}


}
