package com.example.customers.presentationlayer;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
