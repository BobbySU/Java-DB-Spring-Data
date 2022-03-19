package com.example.exercisespringdataautomappingobjects.model.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class GameAddDTO {
    @Pattern(regexp = "[A-Z][a-z]{3,100}", message = "Enter valid title")
    private String title;

    @Size(min = 11, max = 11, message = "Enter valid trailerId")
    private String trailer;

    @Pattern(regexp = "(https?).+", message = "Enter valid url")
    private String imageThumbnailURL;

    @Min(value = 0, message = "Enter valid size")
    private Double size;

    @DecimalMin(value = "0", message = "Enter valid price")
    private BigDecimal price;

    @Size(min = 20, message = "Enter valid description")
    private String description;

    private LocalDate releaseDate;

    public GameAddDTO() {
    }

// |<title>|<price>|<size>|<trailer>|<thubnailURL>|<description>|<releaseDate>
    public GameAddDTO(String title, BigDecimal price, Double size,String trailer, String imageThumbnailURL,
                       String description, LocalDate releaseDate) {
        this.title = title;
        this.trailer = trailer;
        this.imageThumbnailURL = imageThumbnailURL;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImageThumbnailURL() {
        return imageThumbnailURL;
    }

    public void setImageThumbnailURL(String imageThumbnailURL) {
        this.imageThumbnailURL = imageThumbnailURL;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
