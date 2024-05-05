package com.example.apigateway.mapperlayer.customers;

import com.example.apigateway.presentationlayer.customers.CustomerController;
import com.example.apigateway.presentationlayer.customers.CustomerResponseModel;
import org.mapstruct.*;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CustomerResponseMapper {

    CustomerResponseModel responseModelToResponseModel(CustomerResponseModel customerResponseModel);
    List<CustomerResponseModel> responseModelListToResponseModelList(List<CustomerResponseModel> listCustomerResponseModel);

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
