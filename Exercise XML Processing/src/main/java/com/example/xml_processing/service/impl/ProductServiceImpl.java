package com.example.xml_processing.service.impl;

import com.example.xml_processing.model.dto.ProductsRangeDTO;
import com.example.xml_processing.model.dto.ProductsRangeRootDTO;
import com.example.xml_processing.repository.ProductRepository;

import com.example.xml_processing.model.dto.seed.ProductSeedDTO;
import com.example.xml_processing.model.entity.Product;
import com.example.xml_processing.service.CategoryService;
import com.example.xml_processing.service.ProductService;

import com.example.xml_processing.service.UserService;
import com.example.xml_processing.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository,
                              ModelMapper modelMapper, ValidationUtil validationUtil,
                              UserService userService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public long getEntityCount() {
        return productRepository.count();
    }

    @Override
    public void seedProduct(List<ProductSeedDTO> products) {
        products.stream()
                .filter(validationUtil::isValid)
                .map(ProductSeedDTO -> {
                    Product product = modelMapper.map(ProductSeedDTO, Product.class);
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
    public ProductsRangeRootDTO findAllProductsInRangeOrderByPrice(BigDecimal lower, BigDecimal upper) {
        ProductsRangeRootDTO productsRangeRootDTO = new ProductsRangeRootDTO();
        productsRangeRootDTO.setProducts(productRepository
                .findAllByPriceBetweenAndBuyerIsNullOrderByPrice(lower, upper)
                .stream()
                .map(product -> {
                    ProductsRangeDTO productsRangeDTO =
                            modelMapper.map(product, ProductsRangeDTO.class);
                    productsRangeDTO.setSeller(String.format("%s %s",
                            product.getSeller().getFirstName() == null ? "" : product.getSeller().getFirstName(),
                            product.getSeller().getLastName()));
                    return productsRangeDTO;
                }).collect(Collectors.toList()));
        return productsRangeRootDTO;
    }

//    @Override
//    public List<CategoryStatsDTO> getCategoryStatistics() {
//        return productRepository.getCategoryStats();
//    }
}
