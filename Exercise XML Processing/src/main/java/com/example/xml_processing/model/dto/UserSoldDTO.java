package com.example.xml_processing.model.dto;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSoldDTO {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name = "product")
    private Set<UserSoldBuyerDTO> soldProducts;

    public UserSoldDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<UserSoldBuyerDTO> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<UserSoldBuyerDTO> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
