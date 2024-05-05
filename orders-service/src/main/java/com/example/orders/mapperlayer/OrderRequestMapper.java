package com.example.orders.mapperlayer;

import com.example.orders.datalayer.*;
import com.example.orders.presentationlayer.OrderRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderRequestMapper {

    @Mapping(target = "id", ignore = true)
    Order requestModelToEntity(OrderRequestModel orderRequestModel,
                               OrderIdentifier orderIdentifier,
                               ProductIdentifier productIdentifier,
                               CustomerIdentifier customerIdentifier,
                               EmployeeIdentifier employeeIdentifier);

}