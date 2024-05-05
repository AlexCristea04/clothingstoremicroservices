package com.example.apigateway.domainclientlayer.products;

import com.example.apigateway.presentationlayer.products.ProductRequestModel;
import com.example.apigateway.presentationlayer.products.ProductResponseModel;
import com.example.apigateway.utils.HttpErrorInfo;
import com.example.apigateway.utils.exceptions.InvalidInputException;
import com.example.apigateway.utils.exceptions.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Slf4j
@Component
public class ProductServiceClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final String PRODUCTS_SERVICE_BASE_URL;

    public ProductServiceClient(RestTemplate restTemplate, ObjectMapper mapper,
                                @Value("${app.products-service.host}") String productServiceHost,
                                @Value("${app.products-service.port}") String productServicePort) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        this.PRODUCTS_SERVICE_BASE_URL = "http://" + productServiceHost + ":" + productServicePort + "/api/v1/products";
    }

    public List<ProductResponseModel> getAllProducts() {
        try {
            String url = PRODUCTS_SERVICE_BASE_URL;

            ProductResponseModel[] productResponseModel = restTemplate.getForObject(url, ProductResponseModel[].class);

            return Arrays.asList(productResponseModel);
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public ProductResponseModel getProductByProductId(String productId) {
        try {
            String url = PRODUCTS_SERVICE_BASE_URL + "/" + productId;

            ProductResponseModel productResponseModel = restTemplate.getForObject(url, ProductResponseModel.class);

            return productResponseModel;
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public ProductResponseModel createProduct(ProductRequestModel productRequestModel) {
        try {
            String url = PRODUCTS_SERVICE_BASE_URL;

            ProductResponseModel productResponseModel = restTemplate.postForObject(url, productRequestModel, ProductResponseModel.class);

            return productResponseModel;
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public ProductResponseModel updateProductByProduct_Id(ProductRequestModel productRequestModel, String productId) {
        try {
            String url = PRODUCTS_SERVICE_BASE_URL + "/" + productId;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<ProductRequestModel> requestEntity = new HttpEntity<>(productRequestModel, headers);
            ResponseEntity<ProductResponseModel> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, ProductResponseModel.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                throw new RuntimeException("Update failed with HTTP status code: " + responseEntity.getStatusCodeValue());
            }
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public void deleteProductByProduct_Id(String productId) {
        try {
            String url = PRODUCTS_SERVICE_BASE_URL + "/" + productId;

            restTemplate.delete(url);
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    private RuntimeException handleHttpClientException(HttpClientErrorException ex) {
        if (ex.getStatusCode() == NOT_FOUND) {
            return new NotFoundException(getErrorMessage(ex));
        }
        if (ex.getStatusCode() == UNPROCESSABLE_ENTITY) {
            return new InvalidInputException(getErrorMessage(ex));
        }
        log.warn("Got an unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
        log.warn("Error body: {}", ex.getResponseBodyAsString());
        return ex;
    }

    private String getErrorMessage(HttpClientErrorException ex) {
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        } catch (IOException ioex) {
            return ioex.getMessage();
        }
    }
}
