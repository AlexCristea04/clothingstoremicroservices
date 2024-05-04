package com.example.customers.presentationlayer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CustomerRequestModel {
    String customerId;
    String lastName;
    String firstName;
    String emailAddress;
    String streetAddress;
    String postalCode;
    String city;
    String province;

}
