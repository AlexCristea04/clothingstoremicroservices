package com.example.customers.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByCustomerIdentifier_CustomerId(String customerId);

    boolean existsCustomerByCustomerIdentifier_CustomerId(String customerId);
}
