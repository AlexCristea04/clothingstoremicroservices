package com.example.apigateway.businesslayer.products;

import com.example.apigateway.domainclientlayer.products.ProductServiceClient;
import com.example.apigateway.mapperlayer.products.ProductResponseMapper;
import com.example.apigateway.presentationlayer.products.ProductRequestModel;
import com.example.apigateway.presentationlayer.products.ProductResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductServiceClient productServiceClient;
    private final ProductResponseMapper productResponseMapper;

    public ProductServiceImpl(ProductServiceClient productServiceClient, ProductResponseMapper productResponseMapper) {
        this.productServiceClient = productServiceClient;
        this.productResponseMapper = productResponseMapper;
    }

    @Override
    public List<ProductResponseModel> getProducts() {
        return productResponseMapper.responseModelListToResponseModelList(productServiceClient.getAllProducts());
    }

    @Override
    public ProductResponseModel getProductByProductId(String productId) {
        return productResponseMapper.responseModelToResponseModel(productServiceClient.getProductByProductId(productId));
    }

    @Override
    public ProductResponseModel addProduct(ProductRequestModel productRequestModel) {
        return productResponseMapper.responseModelToResponseModel(productServiceClient.createProduct(productRequestModel));
    }

    @Override
    public ProductResponseModel updateProduct(ProductRequestModel productRequestModel, String productId) {
        return productResponseMapper.responseModelToResponseModel(productServiceClient.updateProductByProduct_Id(productRequestModel, productId));
    }

    @Override
    public void removeProduct(String productId) {
        productServiceClient.deleteProductByProduct_Id(productId);
    }
}
