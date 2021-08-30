package com.example.warehousemanagement.model.transform;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Item {
    private String name;

    @JsonProperty("contain_articles")
    private List<SubItem> subItems;
}
