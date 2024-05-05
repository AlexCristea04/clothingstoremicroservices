package com.example.apigateway.mapperlayer.products;

import com.example.apigateway.presentationlayer.products.ProductController;
import com.example.apigateway.presentationlayer.products.ProductResponseModel;
import org.mapstruct.*;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProductResponseMapper {

    ProductResponseModel responseModelToResponseModel(ProductResponseModel productResponseModel);
    List<ProductResponseModel> responseModelListToResponseModelList(List<ProductResponseModel> listProductResponseModel);

    @AfterMapping
    default void addLinks(@MappingTarget ProductResponseModel model) {
        Link selfLink = linkTo(methodOn(ProductController.class)
                .getProductByProductId(model.getProductId()))
                .withSelfRel();
        model.add(selfLink);

        Link productsLink =
                linkTo(methodOn(ProductController.class)
                        .getProducts())
                        .withRel("All products");
        model.add(productsLink);
    }
}
