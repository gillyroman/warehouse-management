package com.example.warehousemanagement.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductArticleRelation implements Serializable {
    @JsonIgnore
    private Integer productId;
    @JsonProperty("article_id")
    private Long articleId;
    @JsonProperty("quantity")
    private Integer minimumQuantity;
    @JsonProperty("available_stock")
    private Integer stock;
}
