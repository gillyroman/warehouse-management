package com.example.warehousemanagement.unittest;

import com.example.warehousemanagement.exception.ApplicationServiceException;
import com.example.warehousemanagement.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArticleTest {

    @Autowired
    ArticleService articleService;

    @Test
    void testInvalidLoad(){
        assertThrows(ApplicationServiceException.class, ()-> {
            articleService.loadArticlesAndInventory("/xyz/test.json");
        });
    }

}
