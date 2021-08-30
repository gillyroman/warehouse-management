package com.example.warehousemanagement.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceOrderRequest {
    private Integer productId;
    private Integer quantity;
}
