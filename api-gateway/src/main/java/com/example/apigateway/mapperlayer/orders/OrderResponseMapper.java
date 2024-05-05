package com.example.apigateway.mapperlayer.orders;

import com.example.apigateway.presentationlayer.orders.OrderController;
import com.example.apigateway.presentationlayer.orders.OrderResponseModel;
import org.mapstruct.*;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface OrderResponseMapper {

    OrderResponseModel responseModelToResponseModel(OrderResponseModel orderResponseModel);
    List<OrderResponseModel> responseModelListToResponseModelList(List<OrderResponseModel> listOrderResponseModel);

    @AfterMapping
    default void addLinks(@MappingTarget OrderResponseModel model) {
        Link selfLink = linkTo(methodOn(OrderController.class)
                .getOrderByOrderId(model.getOrderId()))
                .withSelfRel();
        model.add(selfLink);

        Link ordersLink =
                linkTo(methodOn(OrderController.class)
                        .getOrders())
                        .withRel("All orders");
        model.add(ordersLink);
    }
}
