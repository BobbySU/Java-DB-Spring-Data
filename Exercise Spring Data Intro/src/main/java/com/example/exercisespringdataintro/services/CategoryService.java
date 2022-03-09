package com.example.exercisespringdataintro.services;

import com.example.exercisespringdataintro.models.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> getRandomCategory();
}
