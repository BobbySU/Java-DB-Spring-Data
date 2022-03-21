package com.example.json_processing.service.impl;

import com.example.json_processing.model.dto.CategorySeedDTO;
import com.example.json_processing.model.entity.Category;
import com.example.json_processing.repository.CategoryRepository;
import com.example.json_processing.service.CategoryService;
import com.example.json_processing.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.json_processing.constants.GlobalConstants.RESOURCES_FILE_PATH;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORY_FILE = "categories.json";

    private final CategoryRepository categoryRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, Gson gson,
                               ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.categoryRepository = categoryRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCategory() throws IOException {
        if (categoryRepository.count() > 0) {
            return;
        }

        String readString = Files.readString(Path.of(RESOURCES_FILE_PATH + CATEGORY_FILE));

        CategorySeedDTO[] categorySeedDTOS = gson.fromJson(readString, CategorySeedDTO[].class);

        Arrays.stream(categorySeedDTOS)
                .filter(validationUtil::isValid)
                .map(categorySeedDTO -> modelMapper.map(categorySeedDTO, Category.class))
                .forEach(categoryRepository::save);
    }

    @Override
    public Set<Category> findRandomCategory() {
        Set<Category> categorySet = new HashSet<>();
        int catCount = ThreadLocalRandom.current().nextInt(1, 3);
        long totalRepositoryCount = categoryRepository.count();

        for (int i = 0; i < catCount; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1, totalRepositoryCount + 1);
            categorySet.add(categoryRepository.findById(randomId).orElse(null));
        }
        return categorySet;
    }
}
