package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.persistence.model.Product;
import com.example.warehousemanagement.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping(path = "/load")
    @ApiOperation("Load products to the system")
    public ResponseEntity<Object> loadProducts(@RequestBody(required = false) String path) {
        productService.loadProducts(path);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    @ApiOperation("List all products and the associated article information from the system")
    public ResponseEntity<List<Product>> findAllProducts() {
        var productList = productService.findAllProducts();
        return ResponseEntity.ok(productList);
    }
}
