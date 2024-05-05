package com.example.apigateway.presentationlayer.customers;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
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
