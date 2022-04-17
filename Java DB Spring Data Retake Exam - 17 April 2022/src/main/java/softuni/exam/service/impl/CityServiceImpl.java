package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.CitySeedDTO;
import softuni.exam.models.entity.City;
import softuni.exam.repository.CityRepository;
import softuni.exam.service.CityService;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class CityServiceImpl implements CityService {

    private static final String CITIES_FILE_PATH = "src/main/resources/files/json/cities.json";

    private final CityRepository cityRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    private final CountryService countryService;

    public CityServiceImpl(CityRepository cityRepository, Gson gson,
                           ValidationUtil validationUtil, ModelMapper modelMapper,
                           CountryService countryService) {
        this.cityRepository = cityRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.countryService = countryService;
    }

    @Override
    public boolean areImported() {
        return cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files.readString(Path.of(CITIES_FILE_PATH));
    }

    @Override
    public String importCities() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(readCitiesFileContent(), CitySeedDTO[].class))
                .filter(citySeedDTO -> {
                    boolean isValid = validationUtil.isValid(citySeedDTO);
                    if (cityRepository.findCityByCityName(citySeedDTO.getCityName()) != null){
                        isValid = false;
                    }
                    sb.append(isValid ? String.format("Successfully imported city %s - %d",
                                    citySeedDTO.getCityName(), citySeedDTO.getPopulation())
                                    : "Invalid city")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(citySeedDTO -> {
                    City city = modelMapper.map(citySeedDTO, City.class);
                    city.setCountry(countryService.findCountryById(citySeedDTO.getCountry()));
                    return city;
                })
                .forEach(cityRepository::save);
        return sb.toString();
    }

    @Override
    public City FindCityById(Long city) {
        return cityRepository.findCityById(city);
    }
}
