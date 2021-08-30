package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.persistence.mapper.OrderMapper;
import com.example.warehousemanagement.persistence.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class OrderRepository {
    private final OrderMapper orderMapper;

    public void saveOrder(Order order) {
        orderMapper.saveOrder(order);
    }

}
