package com.example.warehousemanagement.integrationtest;

import com.example.warehousemanagement.model.PlaceOrderRequest;
import com.example.warehousemanagement.service.ArticleService;
import com.example.warehousemanagement.service.OrderService;
import com.example.warehousemanagement.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testLoadArticles() throws Exception{
        mockMvc.perform(post("/v1/articles/load")).andExpect(status().isAccepted());
    }

    @Test
    public void testLoadProducts() throws Exception{
        mockMvc.perform(post("/v1/products/load")).andExpect(status().isAccepted());
    }

    @Test
    public void placeOrder() throws Exception{
        populateData();
        PlaceOrderRequest request = new PlaceOrderRequest();
        request.setProductId(1);
        request.setQuantity(1);
        mockMvc.perform(post("/v1/orders/place").contentType("application/json").content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated());
    }

    @Test
    public void getAllProducts() throws Exception{
        populateData();
        mockMvc.perform(get("/v1/products")).andExpect(status().isOk()).andExpect(content().contentType("application/json"));
    }

    public void populateData() {
        articleService.loadArticlesAndInventory(null);
        productService.loadProducts(null);
    }

}
