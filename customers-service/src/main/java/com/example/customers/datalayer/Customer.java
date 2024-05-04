package com.example.customers.datalayer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="customers")
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //private identifier

    @Embedded
    private CustomerIdentifier customerIdentifier; //public identifier

    private String lastName;
    private String firstName;
    private String emailAddress;

    @Embedded
    private BillingAddress billingAddress;

    public Customer(@NotNull String lastName, @NotNull String firstName, @NotNull String emailAddress, @NotNull BillingAddress billingAddress) {
        this.customerIdentifier = new CustomerIdentifier();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.billingAddress = billingAddress;
    }

}
