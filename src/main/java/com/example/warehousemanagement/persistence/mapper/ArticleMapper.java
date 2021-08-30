package com.example.warehousemanagement.persistence.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper {
    @Insert({
            "INSERT INTO article ( article_id, name)",
            "VALUES ( #{articleId}, #{name})"
    })
    void saveArticle(@Param("articleId") Long articleId, @Param("name") String name);


}
