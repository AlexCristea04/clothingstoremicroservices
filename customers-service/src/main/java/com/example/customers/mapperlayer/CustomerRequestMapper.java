package com.example.customers.mapperlayer;

import com.example.customers.datalayer.CustomerIdentifier;
import com.example.customers.datalayer.BillingAddress;
import com.example.customers.datalayer.Customer;
import com.example.customers.presentationlayer.CustomerRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {
    @Mapping(target = "id", ignore = true)
    Customer requestModelToEntity(CustomerRequestModel customerRequestModel,
                                  CustomerIdentifier customerIdentifier,
                                  BillingAddress billingAddress);

}
