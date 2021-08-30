package com.example.warehousemanagement.unittest;

import com.example.warehousemanagement.repository.ProductRepository;
import com.example.warehousemanagement.service.ArticleService;
import com.example.warehousemanagement.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private ArticleService articleService;

    @BeforeAll
    public void init(){
        articleService.loadArticlesAndInventory(null);
        productService.loadProducts(null);
    }

    @Test
    void testFindAll() {
        var productList = productService.findAllProducts();
        assertNotNull(productList);
        assertNotEquals(0, productList.get(0).getProductArticleRelationsList().get(0).getStock());
    }
}
