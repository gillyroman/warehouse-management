package com.example.warehousemanagement.persistence.mapper;

import com.example.warehousemanagement.persistence.model.Inventory;
import org.apache.ibatis.annotations.*;

@Mapper
public interface InventoryMapper {
    @Insert({
            "INSERT INTO inventory (article_id, stock) ",
            "VALUES (#{articleId}, #{stock}) "
    })
    void save(@Param("articleId") Long articleId, @Param("stock") Integer stock);

    @Select({
            "SELECT * FROM inventory ",
            "WHERE article_id = #{articleId}"
    })
    Inventory findByArticleId(@Param("articleId") Long articleId);

    @Update({
            "UPDATE inventory",
            "SET stock = #{stock} ",
            "WHERE article_id = #{articleId}"
    })
    void updateStock(@Param("articleId") Long articleId, @Param("stock") Integer stock);
}
