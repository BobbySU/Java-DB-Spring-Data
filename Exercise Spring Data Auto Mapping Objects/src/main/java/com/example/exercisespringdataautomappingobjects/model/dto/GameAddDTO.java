package com.example.exercisespringdataautomappingobjects.model.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class GameAddDTO {
    @Pattern(regexp = "[A-Z][a-z]{3,100}", message = "Enter valid title")
    private String title;

    @Size(min = 11, max = 11, message = "Enter valid trailerId")
    private String trailerId;

    @Pattern(regexp = "(https?).+", message = "Enter valid url")
    private String imageThumbnail;

    @Min(value = 0, message = "Enter valid size")
    private double size;

    @DecimalMin(value = "0", message = "Enter valid price")
    private BigDecimal price;

    @Size(min = 20, message = "Enter valid description")
    private String description;

    private String releaseDate;

    public GameAddDTO() {
    }
// |<title>|<price>|<size>|<trailer>|<thubnailURL>|<description>|<releaseDate>

    public GameAddDTO(String title,BigDecimal price, Double size, String trailerId,
                      String imageThumbnail, String description, String releaseDate) {
        this.title = title;
        this.trailerId = trailerId;
        this.imageThumbnail = imageThumbnail;
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

    public String getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(String trailerId) {
        this.trailerId = trailerId;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
