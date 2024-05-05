package com.example.apigateway.domainclientlayer.orders;

import com.example.apigateway.presentationlayer.orders.OrderRequestModel;
import com.example.apigateway.presentationlayer.orders.OrderResponseModel;
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
public class OrderServiceClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final String ORDERS_SERVICE_BASE_URL;

    public OrderServiceClient(RestTemplate restTemplate, ObjectMapper mapper,
                              @Value("${app.orders-service.host}") String orderServiceHost,
                              @Value("${app.orders-service.port}") String orderServicePort) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        this.ORDERS_SERVICE_BASE_URL = "http://" + orderServiceHost + ":" + orderServicePort + "/api/v1/orders";
    }

    public List<OrderResponseModel> getAllOrders() {
        try {
            String url = ORDERS_SERVICE_BASE_URL;

            OrderResponseModel[] orderResponseModel = restTemplate.getForObject(url, OrderResponseModel[].class);

            return Arrays.asList(orderResponseModel);
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public OrderResponseModel getOrderByOrderId(String orderId) {
        try {
            String url = ORDERS_SERVICE_BASE_URL + "/" + orderId;

            OrderResponseModel orderResponseModel = restTemplate.getForObject(url, OrderResponseModel.class);

            return orderResponseModel;
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public OrderResponseModel createOrder(OrderRequestModel orderRequestModel) {
        try {
            String url = ORDERS_SERVICE_BASE_URL;

            OrderResponseModel orderResponseModel = restTemplate.postForObject(url, orderRequestModel, OrderResponseModel.class);

            return orderResponseModel;
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public OrderResponseModel updateOrderByOrder_Id(OrderRequestModel orderRequestModel, String orderId) {
        try {
            String url = ORDERS_SERVICE_BASE_URL + "/" + orderId;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<OrderRequestModel> requestEntity = new HttpEntity<>(orderRequestModel, headers);
            ResponseEntity<OrderResponseModel> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, OrderResponseModel.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                throw new RuntimeException("Update failed with HTTP status code: " + responseEntity.getStatusCodeValue());
            }
        } catch (HttpClientErrorException ex) {
            throw handleHttpClientException(ex);
        }
    }

    public void deleteOrderByOrder_Id(String orderId) {
        try {
            String url = ORDERS_SERVICE_BASE_URL + "/" + orderId;

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
