package com.example.customers.businesslayer;

import com.example.customers.presentationlayer.CustomerRequestModel;
import com.example.customers.presentationlayer.CustomerResponseModel;

import java.util.List;

public interface CustomerService {
    List<CustomerResponseModel> getCustomers();
    CustomerResponseModel getCustomerByCustomerId(String customerId);
    CustomerResponseModel addCustomer(CustomerRequestModel customerRequestModel);
    CustomerResponseModel updateCustomer(CustomerRequestModel updatedCustomer, String customerId);
    void removeCustomer(String customerId);

}
