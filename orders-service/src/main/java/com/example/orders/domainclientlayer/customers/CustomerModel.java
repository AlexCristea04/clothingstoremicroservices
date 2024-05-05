package com.example.orders.domainclientlayer.customers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModel {
    String customerId;
    String lastName;
    String firstName;
    String emailAddress;
    String streetAddress;
    String postalCode;
    String city;
    String province;

}
