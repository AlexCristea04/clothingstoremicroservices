package com.example.apigateway.presentationlayer.orders;

import com.example.apigateway.businesslayer.orders.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseModel>> getOrders() {
        return ResponseEntity.ok().body(orderService.getOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseModel> getOrderByOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok().body(orderService.getOrderByOrderId(orderId));
    }

    @PostMapping
    public ResponseEntity<OrderResponseModel> createOrder(@RequestBody OrderRequestModel orderRequestModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrder(orderRequestModel));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponseModel> updateOrder(@RequestBody OrderRequestModel orderRequestModel, @PathVariable String orderId) {
        return ResponseEntity.ok().body(orderService.updateOrder(orderRequestModel, orderId));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String orderId) {
        orderService.removeOrder(orderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
