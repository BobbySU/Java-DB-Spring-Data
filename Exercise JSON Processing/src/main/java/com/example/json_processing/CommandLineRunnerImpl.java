package com.example.json_processing;

import com.example.json_processing.service.CategoryService;
import com.example.json_processing.service.ProductService;
import com.example.json_processing.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final BufferedReader bufferedReader;

    public CommandLineRunnerImpl(CategoryService categoryService, UserService userService,
                                 ProductService productService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    }

    @Override
    public void run(String... args) throws Exception {
        seedDate();

        System.out.println(" ------ Please Select Exercise/int/:");
        int exNum = Integer.parseInt(bufferedReader.readLine());

        switch (exNum) {
//            case 1 -> Query1_ProductsInRange();
//            case 2 -> 2();
//            case 3 -> 3();
        }

    }

    private void Query1_ProductsInRange() {


    }

    private void seedDate() throws IOException {
        categoryService.seedCategory();
        userService.seedUser();
        productService.seedProduct();
    }
}
