package com.example.products.businesslayer;

import com.example.products.datalayer.ProductStatus;
import com.example.products.datalayer.ProductIdentifier;
import com.example.products.datalayer.Product;
import com.example.products.datalayer.ProductRepository;
import com.example.products.mapperlayer.ProductRequestMapper;
import com.example.products.mapperlayer.ProductResponseMapper;
import com.example.products.presentationlayer.ProductRequestModel;
import com.example.products.presentationlayer.ProductResponseModel;
import com.example.products.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductResponseMapper productResponseMapper;
    private final ProductRequestMapper productRequestMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductResponseMapper productResponseMapper, ProductRequestMapper productRequestMapper) {
        this.productRepository = productRepository;
        this.productResponseMapper = productResponseMapper;
        this.productRequestMapper = productRequestMapper;
    }

    @Override
    public List<ProductResponseModel> getProducts() {
        List<Product> products = productRepository.findAll();
        return productResponseMapper.entityListToResponseModelList(products);
    }

    @Override
    public ProductResponseModel getProductByProductId(String productId) {
        Product product = getProduct(productId);
        return productResponseMapper.entityToResponseModel(product);
    }

    @Override
    public ProductResponseModel addProduct(ProductRequestModel productRequestModel) {
        Product product = productRequestMapper.requestModelToEntity(productRequestModel, new ProductIdentifier());
        return saveProduct(product);
    }

    @Override
    public ProductResponseModel updateProduct(ProductRequestModel productRequestModel, String productId) {
        Product existingProduct = getProduct(productId);

        int updatedQuantity = productRequestModel.getQuantity();

        if (updatedQuantity < 0) {
            updatedQuantity = 0;
        }

        existingProduct.setQuantity(updatedQuantity);
        existingProduct.setStatus(updatedQuantity == 0 ? ProductStatus.OUT_OF_STOCK : ProductStatus.IN_STOCK);

        return saveProduct(existingProduct);
    }

    @Override
    public void removeProduct(String productId) {
        Product product = getProduct(productId);
        productRepository.delete(product);
    }

    // Helper method to retrieve a product by productId and handle NotFoundException
    private Product getProduct(String productId) {
        Product product = productRepository.findByProductIdentifier_ProductId(productId);
        if (product == null) {
            throw new NotFoundException("Unknown productId: " + productId);
        }
        return product;
    }

    // Helper method to save a product and return its response model
    private ProductResponseModel saveProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return productResponseMapper.entityToResponseModel(savedProduct);
    }
}
