package com.example.warehousemanagement.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ApplicationPropertiesConfig {
    @Value("${warehousemanagement.dataload.inventory.location}")
    private String inventoryFileLocation;
    @Value("${warehousemanagement.dataload.product.location}")
    private String productFileLocation;
}
