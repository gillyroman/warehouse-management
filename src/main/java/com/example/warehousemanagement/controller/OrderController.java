package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.model.PlaceOrderRequest;
import com.example.warehousemanagement.persistence.model.Order;
import com.example.warehousemanagement.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Place an order for a product in the store")
    public ResponseEntity<Order> placeOrder (@RequestBody PlaceOrderRequest placeOrderRequest) {
        Order order = orderService.placeOrder(placeOrderRequest);
        return ResponseEntity.created(URI.create(String.format("/orders/%s", order.getOrderId()))).body(order);
    }
}
