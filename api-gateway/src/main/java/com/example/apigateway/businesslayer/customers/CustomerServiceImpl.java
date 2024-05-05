package com.example.apigateway.businesslayer.customers;

import com.example.apigateway.domainclientlayer.customers.CustomerServiceClient;
import com.example.apigateway.mapperlayer.customers.CustomerResponseMapper;
import com.example.apigateway.presentationlayer.customers.CustomerRequestModel;
import com.example.apigateway.presentationlayer.customers.CustomerResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerServiceClient customerServiceClient;
    private final CustomerResponseMapper customerResponseMapper;

    public CustomerServiceImpl(CustomerServiceClient customerServiceClient, CustomerResponseMapper customerResponseMapper) {
        this.customerServiceClient = customerServiceClient;
        this.customerResponseMapper = customerResponseMapper;
    }

    @Override
    public List<CustomerResponseModel> getCustomers() {
        return customerResponseMapper.responseModelListToResponseModelList(customerServiceClient.getAllCustomers());
    }

    @Override
    public CustomerResponseModel getCustomerByCustomerId(String customerId) {
        return customerResponseMapper.responseModelToResponseModel(customerServiceClient.getCustomerByCustomerId(customerId));
    }

    @Override
    public CustomerResponseModel addCustomer(CustomerRequestModel customerRequestModel) {
        return customerResponseMapper.responseModelToResponseModel(customerServiceClient.createCustomer(customerRequestModel));
    }

    @Override
    public CustomerResponseModel updateCustomer(CustomerRequestModel customerRequestModel, String customerId) {
        return customerResponseMapper.responseModelToResponseModel(customerServiceClient.updateCustomerByCustomer_Id(customerRequestModel, customerId));
    }

    @Override
    public void removeCustomer(String customerId) {
        customerServiceClient.deleteCustomerByCustomer_Id(customerId);
    }

}
