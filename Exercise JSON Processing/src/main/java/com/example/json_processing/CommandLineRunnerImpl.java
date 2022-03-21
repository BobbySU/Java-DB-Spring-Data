package com.example.json_processing;

import com.example.json_processing.model.dto.ProductNamePriceAndSellerDTO;
import com.example.json_processing.model.dto.UserSoldDTO;
import com.example.json_processing.service.CategoryService;
import com.example.json_processing.service.ProductService;
import com.example.json_processing.service.UserService;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    public static final String OUTPUT_PATH = "src/main/resources/files/out/";
    public static final String PRODUCTS_IN_RANGE_FILE = "products-in-range.json";
    public static final String USERS_SOLD_PRODUCTS_FILE = "users-sold-products.json";

    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final BufferedReader bufferedReader;
    private final Gson gson;

    public CommandLineRunnerImpl(CategoryService categoryService, UserService userService,
                                 ProductService productService, Gson gson) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.gson = gson;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    }

    @Override
    public void run(String... args) throws Exception {
        seedDate();

        System.out.println(" ------ Please Select Exercise/int/: ------");
        int exNum = Integer.parseInt(bufferedReader.readLine());

        switch (exNum) {
            case 1 -> Query1_ProductsInRange();
            case 2 -> Query2_SuccessfullySoldProducts();
//            case 3 -> 3();
//            case 4 -> 4();
            default -> System.out.println("Please enter valid Exercise/int/");
        }
    }

    private void Query2_SuccessfullySoldProducts() throws IOException {
        List<UserSoldDTO> userSoldDTOS = userService.findAllUserWithMoreThenOneSoldProduct();

        String content = gson.toJson(userSoldDTOS);

        writeToFile(OUTPUT_PATH + USERS_SOLD_PRODUCTS_FILE, content);

        System.out.println("------ Write output information in File: users-sold-products.json ------");
    }

    private void Query1_ProductsInRange() throws IOException {
        List<ProductNamePriceAndSellerDTO> productNamePriceAndSellerDTOS =
                productService.findAllProductsInRangeOrderByPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        String content = gson.toJson(productNamePriceAndSellerDTOS);

        writeToFile(OUTPUT_PATH + PRODUCTS_IN_RANGE_FILE, content);

        System.out.println("------ Write output information in File: products-in-range.json ------");
    }

    private void writeToFile(String filePath, String content) throws IOException {
        Files.write(Path.of(filePath), Collections.singleton(content));
    }

    private void seedDate() throws IOException {
        categoryService.seedCategory();
        userService.seedUser();
        productService.seedProduct();
    }
}
