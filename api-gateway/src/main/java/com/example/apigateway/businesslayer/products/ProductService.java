package com.example.apigateway.businesslayer.products;

import com.example.apigateway.presentationlayer.products.ProductRequestModel;
import com.example.apigateway.presentationlayer.products.ProductResponseModel;

import java.util.List;

public interface ProductService {
    List<ProductResponseModel> getProducts();
    ProductResponseModel getProductByProductId(String productId);
    ProductResponseModel addProduct(ProductRequestModel productRequestModel);
    ProductResponseModel updateProduct(ProductRequestModel updatedProduct, String productId);
    void removeProduct(String productId);
}
