package com.example.json_processing.service;

import com.example.json_processing.model.dto.ProductNamePriceAndSellerDTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedProduct() throws IOException;

    List<ProductNamePriceAndSellerDTO> findAllProductsInRangeOrderByPrice(BigDecimal lower, BigDecimal upper);
}
