package com.example.xml_processing;

import com.example.xml_processing.model.dto.CategoryStatsRootDTO;
import com.example.xml_processing.model.dto.ProductsRangeRootDTO;
import com.example.xml_processing.model.dto.UserSoldRootDTO;
import com.example.xml_processing.model.dto.seed.CategorySeedRootDTO;
import com.example.xml_processing.model.dto.seed.ProductSeedRootDTO;
import com.example.xml_processing.model.dto.seed.UserSeedRootDTO;
import com.example.xml_processing.service.CategoryService;
import com.example.xml_processing.service.ProductService;
import com.example.xml_processing.service.UserService;
import com.example.xml_processing.util.XmlParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private static final String RESOURCES_FILE_PATH = "src/main/resources/files/";
    private static final String CATEGORY_FILE = "categories.xml";
    private static final String PRODUCT_FILE = "products.xml";
    private static final String USER_FILE = "users.xml";
    private static final String OUTPUT_PATH = "src/main/resources/files/out/";
    private static final String PRODUCTS_IN_RANGE_FILE = "products-in-range.xml";
    private static final String USERS_SOLD_PRODUCTS_FILE = "users-sold-products.xml";
    private static final String CATEGORIES_BY_PRODUCTS_FILE = "categories-by-products.xml";

    private final XmlParser xmlParser;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final BufferedReader bufferedReader;

    public CommandLineRunnerImpl(XmlParser xmlParser, CategoryService categoryService, UserService userService,
                                 ProductService productService) {
        this.xmlParser = xmlParser;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println(" ------ Please Select Exercise/int/: ------");
        int exNum = Integer.parseInt(bufferedReader.readLine());

        switch (exNum) {
            // TODO: 24/03/2022 First Seed DataBase - ex. 1
            case 1 -> seedProductShopDate();
            case 2 -> {
                System.out.println(" ------ Exercise 2: Please Select Query/int/: ------");
                int queryNum = Integer.parseInt(bufferedReader.readLine());
                switch (queryNum) {
                    case 1 -> Query1_ProductsInRange();
                    case 2 -> Query2_SuccessfullySoldProducts();
                    case 3 -> Query3_CategoriesByProductsCount();
                    case 4 -> Query4_UsersAndProducts();
                    default -> System.out.println("Please enter valid Query/int/");
                }
            }
            case 3 -> {
//                seedCarDealerDate();
//                System.out.println("------ Seed information in DateBase! ------");
            }

            case 4 -> {
                System.out.println(" ------ Exercise 4: Please Select Query/int/: ------");
                int queryNum = Integer.parseInt(bufferedReader.readLine());
                switch (queryNum) {
//                    case 1 -> Query1_();
//                    case 2 -> Query2_();
//                    case 3 -> Query3_();
//                    case 4 -> Query4_();
                    default -> System.out.println("Please enter valid Query/int/");
                }
            }
            default -> System.out.println("Please enter valid Exercise/int/");
        }
    }

    private void Query4_UsersAndProducts() {
    }

    private void Query3_CategoriesByProductsCount() throws JAXBException {
        CategoryStatsRootDTO categoryStatsRootDTO = this.productService.getCategoryStats();

        xmlParser.writeToFile(OUTPUT_PATH + CATEGORIES_BY_PRODUCTS_FILE, categoryStatsRootDTO);

        System.out.println("------ Write output information in File: categories-by-products.xml ------");
    }

    private void Query2_SuccessfullySoldProducts() throws JAXBException {
        UserSoldRootDTO userSoldRootDTO = userService.findAllUserWithMoreThenOneSoldProduct();

        xmlParser.writeToFile(OUTPUT_PATH + USERS_SOLD_PRODUCTS_FILE, userSoldRootDTO);

        System.out.println("------ Write output information in File: users-sold-products.xml ------");
    }

    private void Query1_ProductsInRange() throws JAXBException {
        ProductsRangeRootDTO productsRangeRootDTO =
                productService.findAllProductsInRangeOrderByPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        xmlParser.writeToFile(OUTPUT_PATH + PRODUCTS_IN_RANGE_FILE, productsRangeRootDTO);

        System.out.println("------ Write output information in File: products-in-range.xml ------");
    }

    private void seedProductShopDate() throws IOException, JAXBException {
        if (categoryService.getEntityCount() == 0) {
            CategorySeedRootDTO categorySeedRootDTO = xmlParser.fromFile(
                    RESOURCES_FILE_PATH + CATEGORY_FILE, CategorySeedRootDTO.class);
            categoryService.seedCategory(categorySeedRootDTO.getCategories());
        }
        if (userService.getEntityCount() == 0) {
            UserSeedRootDTO userSeedRootDTO = xmlParser.fromFile(
                    RESOURCES_FILE_PATH + USER_FILE, UserSeedRootDTO.class);
            userService.seedUser(userSeedRootDTO.getUsers());
        }
        if (productService.getEntityCount() == 0) {
            ProductSeedRootDTO productSeedRootDTO = xmlParser.fromFile(
                    RESOURCES_FILE_PATH + PRODUCT_FILE, ProductSeedRootDTO.class);
            productService.seedProduct(productSeedRootDTO.getProducts());
            System.out.println("------ Seeded information in DateBase! ------");
        }
    }
}
