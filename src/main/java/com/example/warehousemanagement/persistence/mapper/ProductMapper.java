package com.example.warehousemanagement.persistence.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Insert({
            "INSERT INTO product (product_id, name)",
            "VALUES (#{productId}, #{name})"
    })
    void saveProduct(@Param("productId") Integer productId, @Param("name") String name);

    @Select({
            "SELECT p.product_id as p_product_id, p.name as p_name, par.article_id as par_article_id, par.quantity as par_quantity, i.stock as i_stock ",
            "FROM product p ",
            "LEFT OUTER JOIN product_article_rel par on par.product_id = p.product_id ",
            "LEFT OUTER JOIN inventory i ON i.article_id = par.article_id ",
            "WHERE p.product_id = #{productId}"
    })
    @ResultMap("ProductResultMap")
    com.example.warehousemanagement.persistence.model.Product findProductDetailsById(@Param("productId") Integer productId);

    @Select({
            "SELECT p.product_id as p_product_id, p.name as p_name, par.article_id as par_article_id, par.quantity as par_quantity, i.stock as i_stock ",
            "FROM product p ",
            "LEFT OUTER JOIN product_article_rel par on par.product_id = p.product_id ",
            "LEFT OUTER JOIN inventory i ON i.article_id = par.article_id "
    })
    @ResultMap("ProductResultMap")
    List<com.example.warehousemanagement.persistence.model.Product> findAllProducts();

    @Select({"SELECT product_id_schema.nextval FROM DUAL"})
    Integer getSequenceNextValue();

}
