package com.example.json_processing.service.impl;

import com.example.json_processing.model.dto.ProductNamePriceAndSellerDTO;
import com.example.json_processing.model.dto.ProductSeedDTO;
import com.example.json_processing.model.entity.Product;
import com.example.json_processing.repository.ProductRepository;
import com.example.json_processing.service.CategoryService;
import com.example.json_processing.service.ProductService;
import com.example.json_processing.service.UserService;
import com.example.json_processing.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.json_processing.constants.GlobalConstants.RESOURCES_FILE_PATH;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCT_FILE = "products.json";

    private final ProductRepository productRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, Gson gson,
                              ModelMapper modelMapper, ValidationUtil validationUtil,
                              UserService userService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedProduct() throws IOException {
        if (productRepository.count() > 0) {
            return;
        }

        ProductSeedDTO[] productSeedDTOS = gson.fromJson(
                Files.readString(Path.of(RESOURCES_FILE_PATH + PRODUCT_FILE)), ProductSeedDTO[].class);

        Arrays.stream(productSeedDTOS)
                .filter(validationUtil::isValid)
                .map(productSeedDTO -> {
                    Product product = modelMapper.map(productSeedDTO, Product.class);
                    product.setSeller(userService.findRandomUser());
                    if (product.getPrice().compareTo(BigDecimal.valueOf(500)) < 0) {
                        product.setBuyer(userService.findRandomUser());
                    }
                    product.setCategories(categoryService.findRandomCategory());
                    return product;
                })
                .forEach(productRepository::save);
    }

    @Override
    public List<ProductNamePriceAndSellerDTO> findAllProductsInRangeOrderByPrice(BigDecimal lower, BigDecimal upper) {
        return productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(lower, upper)
                .stream()
                .map(product -> {
                    ProductNamePriceAndSellerDTO productNamePriceAndSellerDTO =
                            modelMapper.map(product, ProductNamePriceAndSellerDTO.class);
                    productNamePriceAndSellerDTO.setSeller(String.format("%s %s",
                            product.getSeller().getFirstName(),
                            product.getSeller().getLastName()));
                    return productNamePriceAndSellerDTO;
                }).toList();
    }
}
