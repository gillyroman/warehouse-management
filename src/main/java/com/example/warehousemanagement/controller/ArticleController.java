package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/v1/articles")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping(path = "/load")
    @ApiOperation("Load articles and its inventory to the system")
    public ResponseEntity<Object> loadArticlesAndInventory(@RequestBody(required = false) String path) {
        articleService.loadArticlesAndInventory(path);
        return ResponseEntity.accepted().build();
    }

}
