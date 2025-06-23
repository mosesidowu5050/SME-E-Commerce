package org.mosesidowu.smeecommerce.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "credit_card_information")
public class CreditCardInformation {

    @Id
    private String creditCardId;
    private CardType cardType;
    private String cardName;
    private String cardNumber;
    private String expirationMonth;
    private String expirationYear;
    private String cvv;

}
