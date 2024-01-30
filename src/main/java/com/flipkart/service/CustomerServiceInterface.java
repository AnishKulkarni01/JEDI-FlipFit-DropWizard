package com.flipkart.service;

import com.flipkart.bean.Customer;

public interface CustomerServiceInterface {
    void updateCustomerDetails(String updatedVal,String attr,String customerId);

    boolean register(String name, String password, String email, String contact);

    void viewProfile();
    String getCustomerIdFromUsername(String username);
    public Customer getCustomerFromId(String customerId);
}
