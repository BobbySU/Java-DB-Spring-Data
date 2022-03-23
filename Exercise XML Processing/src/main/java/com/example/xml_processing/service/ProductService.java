package com.example.xml_processing.service;

import com.example.xml_processing.model.dto.seed.ProductSeedDTO;

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
