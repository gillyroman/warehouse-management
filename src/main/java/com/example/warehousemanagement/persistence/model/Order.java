package com.example.warehousemanagement.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    private String orderId;
    private Integer productId;
    private Timestamp createdTimestamp;
    private Integer quantity;
}
