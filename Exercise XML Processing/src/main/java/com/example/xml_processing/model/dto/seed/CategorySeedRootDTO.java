package com.example.xml_processing.model.dto.seed;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategorySeedRootDTO {

    @XmlElement(name = "category")
    List<CategorySeedDTO> categories;

    public List<CategorySeedDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategorySeedDTO> categories) {
        this.categories = categories;
    }
}
