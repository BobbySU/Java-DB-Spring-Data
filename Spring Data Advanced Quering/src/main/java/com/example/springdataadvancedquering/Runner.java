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

//        BigDecimal price = new BigDecimal(5);
//        this.shampooService.selectMoreExpensiveThan(price)
//                .forEach(System.out::println);

//        this.ingredientService.selectNameStartsWith("M")
//                .forEach(e->System.out.printf("%s%n",e.getName()));

//        List<String> names= new ArrayList<>();
//        names.add("Lavender");
//        names.add("Herbs");
//        names.add("Apple");
//        this.ingredientService.selectInNames(names)
//                .forEach(e->System.out.printf("%s%n",e.getName()));

//        BigDecimal price1 = new BigDecimal(8.50);
//        int result = this.shampooService.
//                countPriceLowerThan(price1);
//        System.out.println(result);



//        this.ingredientService.increasePriceByPercentage(0.1);
//            .forEach(System.out::println);
    }

    private void demo() {
        Scanner scanner = new Scanner(System.in);

        String first = scanner.nextLine();
        String second = scanner.nextLine();

        Set<String> names = Set.of(first, second);

        this.shampooRepository.findByIngredientsNames(names)
                .forEach(System.out::println);


//        String sizeName = scanner.nextLine();
//        Size size = Size.valueOf(sizeName);
//
//        this.shampooRepository.findBySizeOrderById(size)
//            .forEach(System.out::println);
    }
}
