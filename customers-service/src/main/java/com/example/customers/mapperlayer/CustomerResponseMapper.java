package com.example.customers.mapperlayer;

import com.example.customers.datalayer.Customer;
import com.example.customers.presentationlayer.CustomerController;
import com.example.customers.presentationlayer.CustomerResponseModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface CustomerResponseMapper {

    @Mapping(expression = "java(customer.getCustomerIdentifier().getCustomerId())", target = "customerId")
    @Mapping(expression = "java(customer.getLastName())", target = "lastName")
    @Mapping(expression = "java(customer.getFirstName())", target = "firstName")
    @Mapping(expression = "java(customer.getEmailAddress())", target = "emailAddress")
    @Mapping(expression = "java(customer.getBillingAddress().getStreetAddress())", target = "streetAddress")
    @Mapping(expression = "java(customer.getBillingAddress().getPostalCode())", target = "postalCode")
    @Mapping(expression = "java(customer.getBillingAddress().getCity())", target = "city")
    @Mapping(expression = "java(customer.getBillingAddress().getProvince())", target = "province")
    CustomerResponseModel entityToResponseModel(Customer customer);
    List<CustomerResponseModel> entityListToResponseModelList(List<Customer> customers);

    @AfterMapping
    default void addLinks(@MappingTarget CustomerResponseModel model){

            Link selfLink = linkTo(methodOn(CustomerController.class)
                    .getCustomerByCustomerId(model.getCustomerId()))
                    .withSelfRel();
            model.add(selfLink);

            Link customersLink =
                    linkTo(methodOn(CustomerController.class)
                            .getCustomers())
                            .withRel("All customers");
            model.add(customersLink);
    }
}
