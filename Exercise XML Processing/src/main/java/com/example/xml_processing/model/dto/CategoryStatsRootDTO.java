package com.example.xml_processing.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryStatsRootDTO {

    @XmlElement(name = "category")
    List<CategoryStatsDTO> categories;

    public CategoryStatsRootDTO() {
    }

    public List<CategoryStatsDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryStatsDTO> categories) {
        this.categories = categories;
    }
}
