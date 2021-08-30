package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.persistence.mapper.ProductArticleRelMapper;
import com.example.warehousemanagement.persistence.mapper.ProductMapper;
import com.example.warehousemanagement.persistence.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
    private final ProductMapper productMapper;
    private final ProductArticleRelMapper productArticleRelMapper;

    public void saveProduct (Integer productId, String name) {
        productMapper.saveProduct(productId, name);
    }

    public void saveProductArticleRelation(Integer productId, Long articleId, Integer quantity) {
        productArticleRelMapper.saveProductArticleRel(productId, articleId, quantity);
    }

    public List<Product> findAllProducts() {
        return productMapper.findAllProducts();
    }

    public Product findProductDetailsById(Integer productId) {
        return productMapper.findProductDetailsById(productId);
    }

    public Integer getSequenceNextValue() {
        return productMapper.getSequenceNextValue();
    }
}
