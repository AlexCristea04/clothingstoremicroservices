package com.example.orders.presentationlayer;

import com.example.orders.businesslayer.OrderService;
import com.example.orders.datalayer.Order;
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
    public ResponseEntity<List<OrderResponseModel>> getOrders(){
        return ResponseEntity.ok().body(orderService.getOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseModel> getOrderById(@PathVariable String orderId){
        return ResponseEntity.ok().body(orderService.getOrderByOrderId(orderId));
    }

    @PostMapping
    public ResponseEntity<OrderResponseModel> createVenue(@RequestBody OrderRequestModel orderRequestModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrder(orderRequestModel));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponseModel> updateOrder(@RequestBody OrderRequestModel venueRequestModel, @PathVariable String orderId){
        return ResponseEntity.ok().body(orderService.updateOrder(venueRequestModel, orderId));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String orderId){
        orderService.removeOrder(orderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
