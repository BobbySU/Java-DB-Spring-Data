package com.example.xml_processing.repository;

import com.example.xml_processing.model.dto.CategoryStatsDTO;
import com.example.xml_processing.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal lower, BigDecimal upper);

    @Query("SELECT new com.example.xml_processing.model.dto.CategoryStatsDTO" +
            " (c.name, COUNT(p), AVG(p.price), SUM(p.price)) FROM Product p JOIN p.categories c " +
            "GROUP BY c ORDER BY COUNT(p) DESC")
    List<CategoryStatsDTO> getCategoryStats();
}
