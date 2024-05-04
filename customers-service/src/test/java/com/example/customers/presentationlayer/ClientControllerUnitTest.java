package com.example.customers.presentationlayer;

import com.example.customers.businesslayer.CustomerService;
import com.example.customers.businesslayer.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = CustomerController.class)
class ClientControllerUnitTest {

    private final String FOUND_CUSTOMER_ID = "123e4567-e89b-12d3-a456-556642440000";
    private final String NOT_FOUND_CUSTOMER_ID = "c3333333-3333-3333-444444444444";
    private final String INVALID_FOUND_CUSTOMER_ID = "c3333333-3333-3333";

    @Autowired
    CustomerController customerController;

    @MockBean
    private CustomerServiceImpl customerService;

    @Test
    public void whenNoCustomerExists_thenReturnEmptyList(){
        //arrange
        when(customerService.getCustomers()).thenReturn(Collections.emptyList());

        //act
        ResponseEntity<List<CustomerResponseModel>> responseEntity = customerController.getCustomers();

        //assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isEmpty());

        verify(customerService, times(1)).getCustomers();
    }

    @Test
    public void whenCustomerExists_thenReturnClient(){
        //arrange
        CustomerRequestModel customerRequestModel = buildCustomerRequestModel();
        CustomerResponseModel customerResponseModel = buildCustomerResponseModel();

        when(customerService.addCustomer(customerRequestModel)).thenReturn(customerResponseModel);

        //act
        ResponseEntity<CustomerResponseModel> responseEntity = customerController.createCustomer(customerRequestModel);

        //assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(customerResponseModel, responseEntity.getBody());

        verify(customerService, times(1)).addCustomer(customerRequestModel);
    }

    private CustomerRequestModel buildCustomerRequestModel(){
        return CustomerRequestModel.builder()
                .customerId(FOUND_CUSTOMER_ID)
                .lastName("Smith")
                .firstName("John")
                .emailAddress("john.smith@example.com")
                .streetAddress("123 Maple Street")
                .postalCode("M1M 1M1")
                .city("Toronto")
                .province("Ontario")
                .build();
    }

    private CustomerResponseModel buildCustomerResponseModel(){
        return CustomerResponseModel.builder()
                .customerId(FOUND_CUSTOMER_ID)
                .lastName("Smith")
                .firstName("John")
                .emailAddress("john.smith@example.com")
                .streetAddress("123 Maple Street")
                .postalCode("M1M 1M1")
                .city("Toronto")
                .province("Ontario")
                .build();
    }
}