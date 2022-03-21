package com.example.json_processing.service;

import com.example.json_processing.model.entity.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategory() throws IOException;
    Set<Category> findRandomCategory();
}
