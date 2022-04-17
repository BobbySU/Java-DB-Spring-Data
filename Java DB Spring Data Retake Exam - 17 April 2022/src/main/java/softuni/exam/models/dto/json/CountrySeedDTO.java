package softuni.exam.models.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountrySeedDTO {

    @Expose
    private String countryName;
    @Expose
    private String currency;

    @Size(min = 2, max = 60)
    @NotNull
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Size(min = 2, max = 20)
    @NotNull
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
