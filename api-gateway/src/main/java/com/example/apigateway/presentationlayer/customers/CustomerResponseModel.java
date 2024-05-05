package com.example.apigateway.presentationlayer.customers;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseModel extends RepresentationModel<CustomerResponseModel> {

    String customerId;
    String lastName;
    String firstName;
    String emailAddress;
    String streetAddress;
    String postalCode;
    String city;
    String province;

}
