package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.persistence.mapper.InventoryMapper;
import com.example.warehousemanagement.persistence.model.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InventoryRepository {
    private final InventoryMapper inventoryMapper;

    public void saveInventory(Long articleId, Integer stock) {
        inventoryMapper.save(articleId, stock);
    }

    public Inventory findInventoryById(Long articleId) {
        return inventoryMapper.findByArticleId(articleId);
    }

    public void updateInventoryStock(Long articleId, Integer stock) {
        inventoryMapper.updateStock(articleId, stock);
    }
}
