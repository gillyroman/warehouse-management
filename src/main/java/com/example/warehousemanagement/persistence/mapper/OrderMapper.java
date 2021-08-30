package com.example.warehousemanagement.persistence.mapper;

import com.example.warehousemanagement.persistence.model.Order;
import com.example.warehousemanagement.persistence.model.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert({
            "INSERT INTO orders (order_id, product_id, quantity, created_timestamp)",
            "VALUES (#{order.orderId}, #{order.productId}, #{order.quantity}, #{order.createdTimestamp})"
    })
    void saveOrder(@Param("order") Order order);

}
