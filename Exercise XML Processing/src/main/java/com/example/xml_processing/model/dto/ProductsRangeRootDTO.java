package com.example.xml_processing.model.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsRangeRootDTO {

    @XmlElement(name = "product")
    List<ProductsRangeDTO> products;

    public List<ProductsRangeDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsRangeDTO> products) {
        this.products = products;
    }
}
