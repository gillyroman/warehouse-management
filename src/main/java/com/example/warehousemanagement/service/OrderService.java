package com.example.warehousemanagement.service;

import com.example.warehousemanagement.exception.ApplicationServiceException;
import com.example.warehousemanagement.exception.ValidationException;
import com.example.warehousemanagement.model.PlaceOrderRequest;
import com.example.warehousemanagement.persistence.model.Order;
import com.example.warehousemanagement.persistence.model.Product;
import com.example.warehousemanagement.persistence.model.ProductArticleRelation;
import com.example.warehousemanagement.repository.InventoryRepository;
import com.example.warehousemanagement.repository.OrderRepository;
import com.example.warehousemanagement.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final OrderRepository orderRepository;

    public Order placeOrder(PlaceOrderRequest request) {
        if (request.getQuantity() < 0) {
            throw new ValidationException("Invalid quantity provided in the request");
        }
        Product productDetails = productRepository.findProductDetailsById(request.getProductId());
        if (productDetails == null) {
            throw new ValidationException("Invalid productId provided in the request");
        }

        // For each article that constitutes the product, it should be checked if requested quantity is in stock
        var articleList = productDetails.getProductArticleRelationsList();
        checkStockAgainstRequestedQuantity(articleList, request);

        articleList.forEach(article -> {
            int totalRequestedQuantity = article.getMinimumQuantity()*request.getQuantity();
            int stockAfterOrder = article.getStock() - totalRequestedQuantity;
            log.debug("Stock after order for articleId {} : {}", article.getArticleId(), stockAfterOrder);
            inventoryRepository.updateInventoryStock(article.getArticleId(), stockAfterOrder);
        });

        String orderId = UUID.randomUUID().toString();
        log.debug("OrderId : {}", orderId);
        Order order = Order.builder().orderId(orderId)
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .createdTimestamp(Timestamp.from(Instant.now()))
                .build();

        orderRepository.saveOrder(order);
        return order;
    }

    private void checkStockAgainstRequestedQuantity(List<ProductArticleRelation> articleList, PlaceOrderRequest request) {
        var stockCheckErrors = new ArrayList<String>();
        articleList.forEach(article -> {
            int totalRequestedQuantity = article.getMinimumQuantity()*request.getQuantity();
            if (article.getStock() < totalRequestedQuantity) {
                log.error("Total requested quantity {} is greater than stock level {} for articleId {}", totalRequestedQuantity, article.getStock(), article.getArticleId());
                stockCheckErrors.add("Not enough stock available for articleId " + article.getArticleId());
            }
        });
        if (!stockCheckErrors.isEmpty()) {
            throw new ApplicationServiceException(stockCheckErrors);
        }
    }

}
