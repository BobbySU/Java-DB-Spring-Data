package com.example.xml_processing.service;

import com.example.xml_processing.repository.model.dto.CategoryStatsDTO;
import com.example.xml_processing.repository.model.dto.ProductNamePriceAndSellerDTO;
import com.example.xml_processing.repository.model.dto.seed.ProductSeedDTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    long getEntityCount();

    void seedProduct(List<ProductSeedDTO> products);
//    void seedProduct() throws IOException;
//
//    List<ProductNamePriceAndSellerDTO> findAllProductsInRangeOrderByPrice(BigDecimal lower, BigDecimal upper);
//
//    List<CategoryStatsDTO> getCategoryStatistics();
}
