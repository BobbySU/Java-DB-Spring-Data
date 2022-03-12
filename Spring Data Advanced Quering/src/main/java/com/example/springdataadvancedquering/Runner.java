package com.example.springdataadvancedquering;

import com.example.springdataadvancedquering.entities.Size;
import com.example.springdataadvancedquering.repositories.ShampooRepository;
import com.example.springdataadvancedquering.services.IngredientService;
import com.example.springdataadvancedquering.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

import static com.example.springdataadvancedquering.entities.Size.MEDIUM;

@Component
public class Runner implements CommandLineRunner {


    private final ShampooRepository shampooRepository;

    private final ShampooService shampooService;

    private final IngredientService ingredientService;

    @Autowired
    public Runner(
            ShampooRepository shampooRepository,
            ShampooService shampooService,
            IngredientService ingredientService) {
        this.shampooRepository = shampooRepository;
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {

        // TODO: 12/03/2022 ------ Select the Method you want to TEST -------
//        this.shampooService.selectBySize(MEDIUM)
//                .forEach(System.out::println);

//        this.shampooService.selectBySizeOrLabelId(MEDIUM,10)
//                .forEach(System.out::println);

//        this.shampooService.selectMoreExpensiveThan(new BigDecimal(5))
//                .forEach(System.out::println);

//        this.ingredientService.selectNameStartsWith("M")
//                .forEach(e->System.out.printf("%s%n",e.getName()));

//        this.ingredientService.selectInNames(List.of("Lavender", "Herbs", "Apple"))
//                .forEach(e -> System.out.printf("%s%n", e.getName()));

//        int result = this.shampooService.
//                countPriceLowerThan(new BigDecimal(8.50));
//        System.out.println(result);

//        not Work
//        this.shampooService.selectByIngredients(Set.of("Berry", "Mineral-Collagen"))
//                .forEach(System.out::println);

//        this.shampooService.selectByIngredientsCount(2)
//                .forEach(e -> System.out.printf("%s%n", e.getBrand()));

//        this.ingredientService.deleteByName("Apple");

//        this.ingredientService.increasePriceByPercentage(1.1);

//        this.ingredientService.increasePriceByPercentageAndName(1.1, List.of("Apple","Nettle","Macadamia Oil"));
    }
}
