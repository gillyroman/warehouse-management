package com.example.warehousemanagement.model.transform;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubItem {

    @JsonProperty("art_id")
    private Long articleId;

    @JsonProperty("amount_of")
    private Integer quantity;
}
