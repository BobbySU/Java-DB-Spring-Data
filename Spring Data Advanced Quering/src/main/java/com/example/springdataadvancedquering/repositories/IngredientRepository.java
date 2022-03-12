package com.example.springdataadvancedquering.repositories;

import com.example.springdataadvancedquering.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
    List<Ingredient> findByNameStartingWith(String start);

    List<Ingredient> findByNameInOrderByPriceAsc(List<String> names);

    int deleteByName(String name);

    @Modifying
    @Query("UPDATE Ingredient i SET i.price = i.price * :multiplier")
    void increasePriceByPercent(@Param("multiplier") BigDecimal percent);

    @Modifying
    @Query("UPDATE Ingredient i SET i.price = i.price * :multiplier WHERE i.name IN :names")
    void increasePriceByPercentAndName(@Param("multiplier") BigDecimal percent, @Param("names") List<String> names);
}
