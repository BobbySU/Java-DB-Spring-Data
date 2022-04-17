package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.ForecastSeedRootDTO;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.entity.enums.DaysOfWeek;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.CityService;
import softuni.exam.service.ForecastService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static softuni.exam.models.entity.enums.DaysOfWeek.SUNDAY;

@Service
public class ForecastServiceImpl implements ForecastService {

    private static final String FORECASTS_FILE_PATH = "src/main/resources/files/xml/forecasts.xml";

    private final ForecastRepository forecastRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    private final CityService cityService;

    public ForecastServiceImpl(ForecastRepository forecastRepository, XmlParser xmlParser,
                               ValidationUtil validationUtil, ModelMapper modelMapper,
                               CityService cityService) {
        this.forecastRepository = forecastRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.cityService = cityService;
    }

    @Override
    public boolean areImported() {
        return forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(Path.of(FORECASTS_FILE_PATH));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        xmlParser.fromFile(FORECASTS_FILE_PATH, ForecastSeedRootDTO.class)
                .getForecasts()
                .stream()
                .filter(forecastSeedDTO -> {
                    boolean isValid = validationUtil.isValid(forecastSeedDTO);
                    if (forecastRepository
                            .findForecastByCityWhereDaysOfWeek(cityService.FindCityById(forecastSeedDTO.getCity()),
                                    forecastSeedDTO.getDaysOfWeek()) != null) {
                        isValid = false;
                    }
                    sb.append(isValid ? String.format("Successfully import forecast %s - %.2f",
                                    forecastSeedDTO.getDaysOfWeek(), forecastSeedDTO.getMaxTemperature())
                                    : "Invalid forecast")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(forecastSeedDTO -> {
                    Forecast forecast = modelMapper.map(forecastSeedDTO, Forecast.class);
                    forecast.setCity(cityService.FindCityById(forecastSeedDTO.getCity()));
                    return forecast;
                })
                .forEach(forecastRepository::save);
        return sb.toString();
    }

    @Override
    public String exportForecasts() {
        StringBuilder sb = new StringBuilder();
        DaysOfWeek daysOfWeek = SUNDAY;
        int lessPopulation = 150000;
        forecastRepository.FindAllByDaysOfWeekAndLessPopulation(daysOfWeek, lessPopulation)
                .forEach(forecast -> {
                    sb.append(String.format("City: %s:\n" +
                                            "   \t\t-min temperature: %.2f\n" +
                                            "   \t\t--max temperature: %.2f\n" +
                                            "   \t\t---sunrise: %s\n" +
                                            "   \t\t----sunset: %s\n",
                                    forecast.getCity().getCityName(), forecast.getMinTemperature(),
                                    forecast.getMaxTemperature(), forecast.getSunrise(),
                                    forecast.getSunset()))
                            .append(System.lineSeparator());
                });
        return sb.toString();
    }
}
