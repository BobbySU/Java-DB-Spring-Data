package com.example.springdataadvancedquering.services;

import com.example.springdataadvancedquering.entities.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> selectNameStartsWith(String start);

    List<Ingredient> selectInNames(List<String> names);

    int deleteByName(String name);

    void increasePriceByPercentage(double percent);

    void increasePriceByPercentageAndName(double percent, List<String> names);
}
