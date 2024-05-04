package com.example.products.businesslayer;

import com.example.products.presentationlayer.ProductRequestModel;
import com.example.products.presentationlayer.ProductResponseModel;

import java.util.List;

public interface ProductService {
    List<ProductResponseModel> getProducts();
    ProductResponseModel getProductByProductId(String productId);
    ProductResponseModel addProduct(ProductRequestModel productRequestModel);
    ProductResponseModel updateProduct(ProductRequestModel productRequestModel, String productId);
    void removeProduct(String productId);
}
