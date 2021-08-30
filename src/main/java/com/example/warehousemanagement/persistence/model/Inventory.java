package com.example.warehousemanagement.persistence.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Inventory implements Serializable {
    private Long articleId;
    private Integer stock;
}
