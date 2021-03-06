package com.example.springdataadvancedquering.services;

import com.example.springdataadvancedquering.entities.Shampoo;
import com.example.springdataadvancedquering.entities.Size;
import com.example.springdataadvancedquering.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class ShampooServiceImpl implements ShampooService {
    @Autowired
    private ShampooRepository shampooRepository;

    @Override
    public List<Shampoo> selectBySize(Size size) {
        return this.shampooRepository.findBySize(size);
    }

    @Override
    public List<Shampoo> selectBySizeOrLabelId(Size size, int labelId) {
//        Label label = this.labelRepository.findById(labelId);

        return this.shampooRepository.findBySizeOrLabelIdOrderByPriceAsc(size, labelId);
    }

    @Override
    public List<Shampoo> selectMoreExpensiveThan(BigDecimal price) {
        return this.shampooRepository.findByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public int countPriceLowerThan(BigDecimal price) {
        return this.shampooRepository.countByPriceLessThan(price);
    }

    @Override
    public List<Shampoo> selectByIngredientsCount(int count) {
        return this.shampooRepository.findByIngredientCountLessThan(count);
    }

    @Override
    public List<Shampoo> selectByIngredients(Set<String> names) {
        return this.shampooRepository.findByIngredients(names);
    }
}
