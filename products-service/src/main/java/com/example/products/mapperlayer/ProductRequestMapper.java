package com.example.products.mapperlayer;

import com.example.products.datalayer.ProductIdentifier;
import com.example.products.datalayer.Product;
import com.example.products.presentationlayer.ProductRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {

    @Mapping(target = "id", ignore = true)
    Product requestModelToEntity(ProductRequestModel productRequestModel,
                                 ProductIdentifier productIdentifier);

}
