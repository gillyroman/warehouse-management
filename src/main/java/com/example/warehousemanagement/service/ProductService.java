package com.example.warehousemanagement.service;

import com.example.warehousemanagement.config.ApplicationPropertiesConfig;
import com.example.warehousemanagement.exception.ApplicationServiceException;
import com.example.warehousemanagement.model.transform.Product;
import com.example.warehousemanagement.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ObjectMapper mapper;
    private final ApplicationPropertiesConfig applicationPropertiesConfig;

    public void loadProducts(String path) {
        try {
            log.debug("Path from input request : {}", path);
            Product product = mapper.readValue(new File(null == path || path.isBlank() ? applicationPropertiesConfig.getProductFileLocation() : path), Product.class);
            product.getItems().forEach(item -> {
                Integer productId = productRepository.getSequenceNextValue();
                log.debug("Product Id from sequence : {}", productId);
                productRepository.saveProduct(productId, item.getName());
                item.getSubItems().forEach(subItem -> productRepository.saveProductArticleRelation(productId, subItem.getArticleId(), subItem.getQuantity()));
            });
        } catch (Exception e) {
            log.error("Error while loading product information to database");
            throw new ApplicationServiceException(e.getMessage());
        }
    }

    public List<com.example.warehousemanagement.persistence.model.Product> findAllProducts() {
        return productRepository.findAllProducts();
    }
}
