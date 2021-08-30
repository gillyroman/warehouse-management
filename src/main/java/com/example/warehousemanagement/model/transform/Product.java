package com.example.warehousemanagement.model.transform;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Product {
    @JsonProperty("products")
    private List<Item> items;
}
