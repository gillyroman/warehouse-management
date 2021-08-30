package com.example.warehousemanagement.model.transform;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Stock {

    @JsonProperty("art_id")
    private Long articleId;
    private String name;
    private Integer stock;
}
