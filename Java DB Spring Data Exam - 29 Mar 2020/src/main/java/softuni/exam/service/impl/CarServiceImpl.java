package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CarServiceImpl implements CarService {
    private static final String CAR_FILE_PATH = "src/main/resources/files/json/cars.json";

    private CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public boolean areImported() {
        return carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return Files.readString(Path.of(CAR_FILE_PATH));
    }

    @Override
    public String importCars() throws IOException {
        return null;
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        return null;
    }
}
