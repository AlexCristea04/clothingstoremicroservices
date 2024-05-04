package com.example.customers.businesslayer;

import com.example.customers.datalayer.CustomerIdentifier;
import com.example.customers.datalayer.BillingAddress;
import com.example.customers.datalayer.Customer;
import com.example.customers.datalayer.CustomerRepository;
import com.example.customers.mapperlayer.CustomerRequestMapper;
import com.example.customers.mapperlayer.CustomerResponseMapper;
import com.example.customers.presentationlayer.CustomerRequestModel;
import com.example.customers.presentationlayer.CustomerResponseModel;
import com.example.customers.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerResponseMapper customerResponseMapper;
    private final CustomerRequestMapper customerRequestMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerResponseMapper customerResponseMapper, CustomerRequestMapper customerRequestMapper) {
        this.customerRepository = customerRepository;
        this.customerResponseMapper = customerResponseMapper;
        this.customerRequestMapper = customerRequestMapper;
    }

    @Override
    public List<CustomerResponseModel> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerResponseMapper.entityListToResponseModelList(customers);
    }

    @Override
    public CustomerResponseModel getCustomerByCustomerId(String customerId) {
        Customer customer = customerRepository.findByCustomerIdentifier_CustomerId(customerId);

        if (customer == null) {
            throw new NotFoundException("Unknown customerId: " + customerId);
        }
        return customerResponseMapper.entityToResponseModel(customer);
    }

    @Override
    public CustomerResponseModel addCustomer(CustomerRequestModel customerRequestModel) {
        BillingAddress billingAddress = new BillingAddress(
                customerRequestModel.getStreetAddress(),
                customerRequestModel.getPostalCode(),
                customerRequestModel.getCity(),
                customerRequestModel.getProvince());

        Customer customer = customerRequestMapper.requestModelToEntity(customerRequestModel, new CustomerIdentifier(), billingAddress);

        customer.setBillingAddress(billingAddress);
        return customerResponseMapper.entityToResponseModel(customerRepository.save(customer));
    }

    @Override
    public CustomerResponseModel updateCustomer(CustomerRequestModel customerRequestModel, String customerId) {
        Customer existingCustomer = customerRepository.findByCustomerIdentifier_CustomerId(customerId);

        if (existingCustomer == null) {
            throw new NotFoundException("Unknown customerId: " + customerId);
        }

        BillingAddress billingAddress = new BillingAddress(
                customerRequestModel.getStreetAddress(),
                customerRequestModel.getPostalCode(),
                customerRequestModel.getCity(),
                customerRequestModel.getProvince());

        Customer updatedCustomer = customerRequestMapper.requestModelToEntity(customerRequestModel,
                existingCustomer.getCustomerIdentifier(), billingAddress);
        updatedCustomer.setId(existingCustomer.getId());

        Customer response = customerRepository.save(updatedCustomer);
        return customerResponseMapper.entityToResponseModel(response);
    }

    @Override
    public void removeCustomer(String customerId) {
        Customer existingCustomer = customerRepository.findByCustomerIdentifier_CustomerId(customerId);

        if (existingCustomer == null) {
            throw new NotFoundException("Unknown customerId: " + customerId);
        }

        customerRepository.delete(existingCustomer);
    }
}
