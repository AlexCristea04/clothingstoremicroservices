package com.example.products.mapperlayer;

import com.example.products.datalayer.Product;
import com.example.products.presentationlayer.ProductController;
import com.example.products.presentationlayer.ProductResponseModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface ProductResponseMapper {

    @Mapping(expression = "java(product.getProductIdentifier().getProductId())", target = "productId")
    @Mapping(expression = "java(product.getName())", target = "name")
    @Mapping(expression = "java(product.getDescription())", target = "description")
    @Mapping(expression = "java(product.getSize())", target = "size")
    @Mapping(expression = "java(product.getPrice())", target = "price")
    @Mapping(expression = "java(product.getQuantity())", target = "quantity")
    @Mapping(expression = "java(product.getStatus().name())", target = "status")
    ProductResponseModel entityToResponseModel(Product product);

    List<ProductResponseModel> entityListToResponseModelList(List<Product> products);

    @AfterMapping
    default void addLinks(@MappingTarget ProductResponseModel model){

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
