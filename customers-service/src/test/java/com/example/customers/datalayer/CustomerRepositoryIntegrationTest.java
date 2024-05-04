package com.example.customers.datalayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class CustomerRepositoryIntegrationTest {
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUpDb() { customerRepository.deleteAll(); }

    @Test
    public void whenClientExists_ReturnClientByClientId(){
        //arrange
        BillingAddress billingAddress = new BillingAddress(
                "123 Main St",
                "12345",
                "Sample City",
                "Sample Province"
        );

        // Initialize Customer
        Customer customer = new Customer(
                "Doe", // Last Name
                "John", // First Name
                "john.doe@example.com", // Email Address
                billingAddress // Billing Address
        );

        customerRepository.save(customer);

        //act
        Customer customerFound = customerRepository.findByCustomerIdentifier_CustomerId(customer.getCustomerIdentifier().getCustomerId());

        //assert
        assertNotNull(customerFound);
        assertEquals(customer, customerFound);

    }
}
