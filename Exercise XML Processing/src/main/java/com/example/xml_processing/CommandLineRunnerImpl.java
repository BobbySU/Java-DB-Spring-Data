package com.example.xml_processing;

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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private static final String RESOURCES_FILE_PATH = "src/main/resources/files/";
    private static final String CATEGORY_FILE = "categories.xml";
    private static final String PRODUCT_FILE = "products.xml";
    private static final String USER_FILE = "users.xml";
    public static final String OUTPUT_PATH = "src/main/resources/files/out/";
    public static final String PRODUCTS_IN_RANGE_FILE = "products-in-range.xml";
    public static final String USERS_SOLD_PRODUCTS_FILE = "users-sold-products.xml";
    public static final String CATEGORIES_BY_PRODUCTS_FILE = "categories-by-products.xml";

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
        seedDate();

        System.out.println(" ------ Please Select Exercise/int/: ------");
        int exNum = Integer.parseInt(bufferedReader.readLine());

        switch (exNum) {
            case 1 -> Query1_ProductsInRange();
            case 2 -> Query2_SuccessfullySoldProducts();
            case 3 -> Query3_CategoriesByProductsCount();
//            case 4 -> Query4_UsersAndProducts();
            default -> System.out.println("Please enter valid Exercise/int/");
        }
    }

    private void Query4_UsersAndProducts() {
    }

    private void Query3_CategoriesByProductsCount() throws IOException {
//        List<CategoryStatsDTO> categoryStatsDTOS= this.productService.getCategoryStatistics();
//
//        String content = this.gson.toJson(categoryStatsDTOS);
//
//        writeToFile(OUTPUT_PATH + CATEGORIES_BY_PRODUCTS_FILE, content);
//
//        System.out.println("------ Write output information in File: categories-by-products.json ------");
    }

    private void Query2_SuccessfullySoldProducts() throws IOException {
//        List<UserSoldDTO> userSoldDTOS = userService.findAllUserWithMoreThenOneSoldProduct();
//
//        String content = gson.toJson(userSoldDTOS);
//
//        writeToFile(OUTPUT_PATH + USERS_SOLD_PRODUCTS_FILE, content);
//
//        System.out.println("------ Write output information in File: users-sold-products.json ------");
    }

    private void Query1_ProductsInRange() throws IOException {
//        List<ProductNamePriceAndSellerDTO> productNamePriceAndSellerDTOS =
//                productService.findAllProductsInRangeOrderByPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
//
//        String content = gson.toJson(productNamePriceAndSellerDTOS);
//
//        writeToFile(OUTPUT_PATH + PRODUCTS_IN_RANGE_FILE, content);
//
//        System.out.println("------ Write output information in File: products-in-range.json ------");
    }

    private void writeToFile(String filePath, String content) throws IOException {
        Files.write(Path.of(filePath), Collections.singleton(content));
    }

    private void seedDate() throws IOException, JAXBException {
        if (categoryService.getEntityCount() == 0) {
            CategorySeedRootDTO categorySeedRootDTO = xmlParser.fromFile(
                    RESOURCES_FILE_PATH + CATEGORY_FILE, CategorySeedRootDTO.class);
            categoryService.seedCategory(categorySeedRootDTO.getCategories());
        }
        if (userService.getEntityCount() == 0){
            UserSeedRootDTO userSeedRootDTO = xmlParser.fromFile(
                    RESOURCES_FILE_PATH + USER_FILE, UserSeedRootDTO.class);
            userService.seedUser(userSeedRootDTO.getUsers());
        }
        if (productService.getEntityCount() == 0) {
            ProductSeedRootDTO productSeedRootDTO = xmlParser.fromFile(
                    RESOURCES_FILE_PATH + PRODUCT_FILE, ProductSeedRootDTO.class);
            productService.seedProduct(productSeedRootDTO.getProducts());
        }
    }
}
