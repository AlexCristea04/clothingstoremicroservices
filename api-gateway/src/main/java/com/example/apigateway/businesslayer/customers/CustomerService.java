package com.example.apigateway.businesslayer.customers;

import com.example.apigateway.presentationlayer.customers.CustomerRequestModel;
import com.example.apigateway.presentationlayer.customers.CustomerResponseModel;

import java.util.List;

public interface CustomerService {
    List<CustomerResponseModel> getCustomers();
    CustomerResponseModel getCustomerByCustomerId(String customerId);
    CustomerResponseModel addCustomer(CustomerRequestModel customerRequestModel);
    CustomerResponseModel updateCustomer(CustomerRequestModel updatedCustomer, String customerId);
    void removeCustomer(String customerId);

}