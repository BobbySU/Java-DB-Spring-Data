package com.example.exercisespringdataintro.services;

import com.example.exercisespringdataintro.models.Category;
import com.example.exercisespringdataintro.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String CATEGORIES_FILE_PATH = "src/main/resources/files/categories.txt";

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() throws IOException {
        if (categoryRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(CATEGORIES_FILE_PATH))
                .stream()
                .filter(row -> !row.isEmpty())
                .forEach(CategoryName -> {
                    Category category = new Category(CategoryName);
                    categoryRepository.save(category);
                });
    }

    @Override
    public Set<Category> getRandomCategory() {
        Set<Category> categories = new HashSet<>();
        int rand = ThreadLocalRandom.current().nextInt(1, 3);
        long catSize = categoryRepository.count();
        for (int i = 0; i < rand; i++) {
            long randId = ThreadLocalRandom.current().nextLong(1, (catSize + 1));
            categories.add(categoryRepository.findById(randId).orElse(null));
        }
        return categories;
    }
}
