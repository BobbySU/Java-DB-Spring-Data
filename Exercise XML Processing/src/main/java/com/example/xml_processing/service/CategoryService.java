package com.example.xml_processing.service;

import com.example.xml_processing.model.dto.seed.CategorySeedDTO;
import com.example.xml_processing.model.entity.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategory(List<CategorySeedDTO> categories);

    long getEntityCount();

    Set<Category> findRandomCategory();
}
