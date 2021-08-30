package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.persistence.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {
    private final ArticleMapper articleMapper;

    public void saveArticle(Long articleId, String name) {
        articleMapper.saveArticle(articleId, name);
    }
}
