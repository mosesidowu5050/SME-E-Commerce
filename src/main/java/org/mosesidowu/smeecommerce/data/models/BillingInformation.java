package org.mosesidowu.smeecommerce.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("billing_information")
public class BillingInformation {

    @Id
    private String billingId;
    @DBRef
    private Address billingAddress;
    @DBRef
    private CreditCardInformation creditCardInformation;
    private String receiverName;
    private String receiverPhoneNumber;

}
