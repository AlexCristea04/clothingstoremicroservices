package com.example.customers.presentationlayer;

import com.example.customers.datalayer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("h2")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ClientControllerIntegrationTest {

    private final String BASE_URI_CUSTOMERS = "api/v1/customers";
    private final String FOUND_CUSTOMER_ID = "123e4567-e89b-12d3-a456-556642440000";
    private final String FOUND_CUSTOMER_LAST_NAME = "Smith";
    private final String NOT_FOUND_CUSTOMER_ID = "c3333333-3333-3333-444444444444";

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void whenGetClients_thenReturnAllClients(){

        long sizeDB = customerRepository.count();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"+ customerRepository.count());
        //act & assert
        webTestClient.get().uri(BASE_URI_CUSTOMERS)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(CustomerResponseModel.class)
                .value((list) -> {
                    assertNotNull(list);
                    assertEquals(list.size(), sizeDB);
                });
    }

    @Test
    public void whenGetCustomerDoesNotExist_thenReturnNotFound(){
        //act & assert
        webTestClient.get().uri(BASE_URI_CUSTOMERS + "/" + NOT_FOUND_CUSTOMER_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.httpStatus").isEqualTo("NOT_FOUND")
                .jsonPath("$.message").isEqualTo("Unknown customerId: " + NOT_FOUND_CUSTOMER_ID);
    }

    @Test
    public void whenValidClient_thenCreateClient(){
        //arrange
        long sizeDB = customerRepository.count();

        CustomerRequestModel customerRequestModel = new CustomerRequestModel(FOUND_CUSTOMER_ID,
                "Smith",
                "John",
                "john.smith@example.com",
                "123 Maple Street",
                "M1M 1M1",
                "Toronto",
                "Ontario"
        );

        webTestClient.post()
                .uri(BASE_URI_CUSTOMERS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(customerRequestModel)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.valueOf("application/hal+json"))
                .expectBody(CustomerResponseModel.class)
                .value((customerResponseModel) -> {
                    assertNotNull(customerResponseModel);
                    assertEquals(customerRequestModel.getLastName(), customerResponseModel.getLastName());
                    assertEquals(customerRequestModel.getFirstName(), customerResponseModel.getFirstName());
                    assertEquals(customerRequestModel.getEmailAddress(), customerResponseModel.getEmailAddress());
                    assertEquals(customerRequestModel.getStreetAddress(), customerResponseModel.getStreetAddress());
                    assertEquals(customerRequestModel.getPostalCode(), customerResponseModel.getPostalCode());
                    assertEquals(customerRequestModel.getCity(), customerResponseModel.getCity());
                    assertEquals(customerRequestModel.getProvince(), customerResponseModel.getProvince());
                });

        long sizeDBAfter = customerRepository.count();
        assertEquals(sizeDB + 1, sizeDBAfter);
    }

    @Test
    public void whenUpdateClient_thenReturnUpdatedClient() {
        // Arrange
        CustomerRequestModel customerToUpdate = new CustomerRequestModel(FOUND_CUSTOMER_ID,
                "Smith",
                "John",
                "john.smith@example.com",
                "123 Maple Street",
                "M1M 1M1",
                "Toronto",
                "Ontario");


        // Act & Assert
        webTestClient.put()
                .uri(BASE_URI_CUSTOMERS + "/" + FOUND_CUSTOMER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(customerToUpdate)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.valueOf("application/hal+json"))
                .expectBody(CustomerResponseModel.class)
                .value((updatedCustomer) -> {
                    assertNotNull(updatedCustomer);
                    assertEquals(customerToUpdate.getLastName(), updatedCustomer.getLastName());
                    assertEquals(customerToUpdate.getFirstName(), updatedCustomer.getFirstName());
                    assertEquals(customerToUpdate.getEmailAddress(), updatedCustomer.getEmailAddress());
                    assertEquals(customerToUpdate.getStreetAddress(), updatedCustomer.getStreetAddress());
                    assertEquals(customerToUpdate.getPostalCode(), updatedCustomer.getPostalCode());
                    assertEquals(customerToUpdate.getCity(), updatedCustomer.getCity());
                    assertEquals(customerToUpdate.getProvince(), updatedCustomer.getProvince());
                });
    }

    @Test
    public void whenUpdateNonExistentClient_thenThrowNotFoundException() {
        // Arrange
        String nonExistentCustomerId = "nonExistentId";
        CustomerRequestModel updatedCustomer = new CustomerRequestModel(nonExistentCustomerId, "UpdateLastName",
                "UpdateFirstName",
                "UpdateEmail",
                "UpdateStreet",
                "UpdatePostal",
                "UpdateCity",
                "UpdateProvince"
        );

        // Act & Assert
        webTestClient.put()
                .uri(BASE_URI_CUSTOMERS + "/" + nonExistentCustomerId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedCustomer)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.httpStatus").isEqualTo("NOT_FOUND")
                .jsonPath("$.message").isEqualTo("Unknown customerId: " + nonExistentCustomerId);
    }

    @Test
    public void whenRemoveNonExistentClient_thenThrowNotFoundException() {
        // Arrange
        String nonExistentClientId = "nonExistentId";

        // Act & Assert
        webTestClient.delete().uri(BASE_URI_CUSTOMERS + "/" + nonExistentClientId)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.httpStatus").isEqualTo("NOT_FOUND")
                .jsonPath("$.message").isEqualTo("Unknown customerId: " + nonExistentClientId);
    }


    @Test
    public void whenDeleteClient_thenDeleteClientSuccessfully() {
        // Act
        webTestClient.delete().uri(BASE_URI_CUSTOMERS + "/" + FOUND_CUSTOMER_ID)
                .exchange()
                .expectStatus()
                .isNoContent();

        //Assert
        assertFalse(customerRepository.existsCustomerByCustomerIdentifier_CustomerId(FOUND_CUSTOMER_ID));
    }

    @Test
    public void whenGetClientById_thenReturnClient() {
        // Act & Assert
        webTestClient.get().uri(BASE_URI_CUSTOMERS + "/" + FOUND_CUSTOMER_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CustomerResponseModel.class)
                .value((customer) -> {
                    assertNotNull(customer);
                    assertEquals(FOUND_CUSTOMER_ID, customer.getCustomerId());
                    assertEquals(FOUND_CUSTOMER_LAST_NAME, customer.getLastName());
                });
    }


}