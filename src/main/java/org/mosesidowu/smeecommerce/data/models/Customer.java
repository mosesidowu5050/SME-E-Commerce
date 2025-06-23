package org.mosesidowu.smeecommerce.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@TypeAlias("seller")
public class Customer extends User {

//    @Id
//    private String customerId;
//    private String userId;
    private ShoppingCart shoppingCart;
    private List<BillingInformation> billingInformation;

}
