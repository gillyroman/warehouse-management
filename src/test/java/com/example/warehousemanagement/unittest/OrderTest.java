package com.example.warehousemanagement.unittest;

import com.example.warehousemanagement.exception.ApplicationServiceException;
import com.example.warehousemanagement.exception.ValidationException;
import com.example.warehousemanagement.model.PlaceOrderRequest;
import com.example.warehousemanagement.persistence.model.Order;
import com.example.warehousemanagement.service.ArticleService;
import com.example.warehousemanagement.service.OrderService;
import com.example.warehousemanagement.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderTest {

    @Autowired
    OrderService orderService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ProductService productService;

    PlaceOrderRequest req;

    @BeforeAll
    public void init() {
        articleService.loadArticlesAndInventory(null);
        productService.loadProducts(null);
    }

    @BeforeEach
    public void loadBeforeEachTest() {
        req = new PlaceOrderRequest();
        req.setProductId(1);
        req.setQuantity(1);
    }

    @Test
    void testValidPlaceOrder() throws Exception {
        Order order = orderService.placeOrder(req);
        assertNotNull(order);
    }

    @Test
    void testInvalidQuantityForOrder() {
        req.setQuantity(-1);
        Throwable exception = assertThrows(ValidationException.class, () -> {
            orderService.placeOrder(req);
        });

        assertEquals(exception.getMessage(), "Invalid quantity provided in the request");
    }

    @Test
    void testInvalidProductForOrder() {
        req.setProductId(100);
        Throwable exception = assertThrows(ValidationException.class, () -> {
            orderService.placeOrder(req);
        });
        assertEquals(exception.getMessage(), "Invalid productId provided in the request");
    }

    @Test
    void testInsufficientStockForOrder() {
        req.setProductId(1);
        req.setQuantity(3);
        Throwable exception = assertThrows(ApplicationServiceException.class, () -> {
            orderService.placeOrder(req);
        });
        assertEquals(exception.getMessage(), "[Not enough stock available for articleId 2, Not enough stock available for articleId 3]");
    }

}
