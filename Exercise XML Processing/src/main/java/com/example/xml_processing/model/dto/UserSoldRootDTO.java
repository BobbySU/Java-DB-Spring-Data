package com.example.xml_processing.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSoldRootDTO {

    @XmlElement(name = "user")
    List<UserSoldDTO> products;

    public UserSoldRootDTO() {
    }

    public List<UserSoldDTO> getProducts() {
        return products;
    }

    public void setProducts(List<UserSoldDTO> products) {
        this.products = products;
    }
}
