package com.example.xml_processing.service;

import com.example.xml_processing.model.dto.CategoryStatsRootDTO;
import com.example.xml_processing.model.dto.ProductsRangeRootDTO;
import com.example.xml_processing.model.dto.seed.ProductSeedDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    long getEntityCount();

    void seedProduct(List<ProductSeedDTO> products);

    ProductsRangeRootDTO findAllProductsInRangeOrderByPrice(BigDecimal lower, BigDecimal upper);

    CategoryStatsRootDTO getCategoryStats();
}
