package com.example.warehousemanagement.service;

import com.example.warehousemanagement.config.ApplicationPropertiesConfig;
import com.example.warehousemanagement.exception.ApplicationServiceException;
import com.example.warehousemanagement.model.transform.Inventory;
import com.example.warehousemanagement.repository.ArticleRepository;
import com.example.warehousemanagement.repository.InventoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final InventoryRepository inventoryRepository;
    private final ObjectMapper objectMapper;
    private final ApplicationPropertiesConfig applicationPropertiesConfig;

    public void loadArticlesAndInventory(String path) {
        try {
            Inventory inventory = objectMapper.readValue(new File(null == path || path.isBlank() ? applicationPropertiesConfig.getInventoryFileLocation() : path), Inventory.class);
            inventory.getInventory().forEach(inventoryEntry -> {
                final Long articleId = inventoryEntry.getArticleId();
                if (inventoryRepository.findInventoryById(articleId) != null) {
                    log.info("Inventory already present, overwriting stock information for articleId {}", articleId);
                    inventoryRepository.updateInventoryStock(articleId, inventoryEntry.getStock());
                } else {
                    log.info("Inserting inventory and article data for articleId {}", articleId);
                    articleRepository.saveArticle(articleId, inventoryEntry.getName());
                    inventoryRepository.saveInventory(articleId, inventoryEntry.getStock());
                }
            });
        } catch (Exception e) {
            log.error("Error while loading articles and inventory information to database");
            throw new ApplicationServiceException(e.getMessage());
        }
    }

}
