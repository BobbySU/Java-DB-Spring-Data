package com.example.exercisespringdataautomappingobjects.model.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class GameEditDTO {
    private Long id;
    @DecimalMin(value = "0", message = "Enter valid price")
    private BigDecimal price;
    @Min(value = 0, message = "Enter valid size")
    private Double size;

    public GameEditDTO() {
    }

    public GameEditDTO(Long id, BigDecimal price, Double size) {
        this.id = id;
        this.price = price;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }
}
