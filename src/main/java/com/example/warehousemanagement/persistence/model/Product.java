package com.example.warehousemanagement.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    @JsonProperty("product_id")
    private Integer productId;
    @JsonProperty("product_name")
    private String name;
    @JsonProperty("article_data")
    private List<ProductArticleRelation> productArticleRelationsList;
}
