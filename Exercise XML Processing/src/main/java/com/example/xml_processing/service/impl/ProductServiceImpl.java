package com.example.xml_processing.service.impl;

import com.example.xml_processing.repository.ProductRepository;

import com.example.xml_processing.repository.model.dto.seed.ProductSeedDTO;
import com.example.xml_processing.repository.model.entity.Product;
import com.example.xml_processing.service.CategoryService;
import com.example.xml_processing.service.ProductService;

import com.example.xml_processing.service.UserService;
import com.example.xml_processing.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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

//    @Override
//    public List<ProductNamePriceAndSellerDTO> findAllProductsInRangeOrderByPrice(BigDecimal lower, BigDecimal upper) {
//        return productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(lower, upper)
//                .stream()
//                .map(product -> {
//                    ProductNamePriceAndSellerDTO productNamePriceAndSellerDTO =
//                            modelMapper.map(product, ProductNamePriceAndSellerDTO.class);
//                    productNamePriceAndSellerDTO.setSeller(String.format("%s %s",
//                            product.getSeller().getFirstName(),
//                            product.getSeller().getLastName()));
//                    return productNamePriceAndSellerDTO;
//                }).toList();
//    }
//
//    @Override
//    public List<CategoryStatsDTO> getCategoryStatistics() {
//        return productRepository.getCategoryStats();
//    }
}
