package com.example.warehousemanagement.persistence.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductArticleRelMapper {

    @Insert({
            "INSERT INTO product_article_rel ( product_id, article_id, quantity)",
            "VALUES ( #{productId}, #{articleId}, #{quantity})"
    })
    void saveProductArticleRel(@Param("productId") Integer productId, @Param("articleId") Long articleId, @Param("quantity") Integer quantity);

}
