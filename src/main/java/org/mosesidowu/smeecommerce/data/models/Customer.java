package org.mosesidowu.smeecommerce.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.TypeAlias;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@TypeAlias("seller")
public class Customer extends User {

    private ShoppingCart shoppingCart;
    private List<BillingInformation> billingInformation;

}
