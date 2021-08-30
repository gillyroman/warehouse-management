package com.example.warehousemanagement.model.transform;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Inventory {
    private List<Stock> inventory;
}
