package org.mosesidowu.smeecommerce.utils;

import org.mosesidowu.smeecommerce.data.models.Customer;
import org.mosesidowu.smeecommerce.data.models.Seller;
import org.mosesidowu.smeecommerce.data.models.User;

import java.util.List;

public class RoleMapper {

    public static Seller getSeller(User user) {
        Seller seller = new Seller();
        seller.setUserId(user.getUserId());
        seller.setFullName(user.getFullName());
        seller.setEmail(user.getEmail());
        seller.setPassword(user.getPassword());
        seller.setPhoneNumber(user.getPhoneNumber());
        seller.setRole(user.getRole());
        seller.setAddress(user.getAddress());
        seller.setItems(List.of());

        return seller;
    }



    public static Customer getCustomer(User user) {
        Customer customer = new Customer();
        customer.setUserId(user.getUserId());
        customer.setFullName(user.getFullName());
        customer.setEmail(user.getEmail());
        customer.setPassword(user.getPassword());
        customer.setPhoneNumber(user.getPhoneNumber());
        customer.setRole(user.getRole());
        customer.setAddress(user.getAddress());

        customer.setShoppingCart(null);
        customer.setBillingInformation(List.of());

        return customer;
    }


}
