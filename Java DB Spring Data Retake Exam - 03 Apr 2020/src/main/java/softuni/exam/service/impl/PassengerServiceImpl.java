package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Passenger;
import softuni.exam.models.dto.json.PassengerSeedDTO;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class PassengerServiceImpl implements PassengerService {

    private static final String PASSENGERS_FILE_PATH = "src/main/resources/files/json/passengers.json";

    private final PassengerRepository passengerRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    private final TownService townService;

    public PassengerServiceImpl(PassengerRepository passengerRepository, Gson gson,
                                ValidationUtil validationUtil, ModelMapper modelMapper,
                                TownService townService) {
        this.passengerRepository = passengerRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return Files.readString(Path.of(PASSENGERS_FILE_PATH));
    }

    @Override
    public String importPassengers() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(readPassengersFileContent(), PassengerSeedDTO[].class))
                .filter(passengerSeedDTO -> {
                    boolean isValid = validationUtil.isValid(passengerSeedDTO);
                    if (passengerRepository.findPassengerByEmail(passengerSeedDTO.getEmail()) != null) {
                        isValid = false;
                    }
                    sb.append(isValid ? String.format("Successfully imported Passenger %s - %s",
                                    passengerSeedDTO.getLastName(), passengerSeedDTO.getEmail())
                                    : "Invalid Passenger")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(passengerSeedDTO -> {
                    Passenger passenger = modelMapper.map(passengerSeedDTO, Passenger.class);
                    passenger.setTown(townService.FindTownByName(passengerSeedDTO.getTown()));
                    return passenger;
                })
                .forEach(passengerRepository::save);
        return sb.toString();
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        StringBuilder sb = new StringBuilder();
        passengerRepository.FindPassengersOrderByTicketsCountDescendingThenByEmail()
                .forEach(passenger -> {
                    if (passenger.getTickets().size() > 0) {
                        sb.append(String.format("Passenger %s  %s\n" +
                                                "\tEmail - %s\n" +
                                                "\tPhone - %s\n" +
                                                "\tNumber of tickets - %d\n",
                                        passenger.getFirstName(), passenger.getLastName(),
                                        passenger.getEmail(), passenger.getPhoneNumber(),
                                        passenger.getTickets().size()))
                                .append(System.lineSeparator());
                    }
                });
        return sb.toString();
    }

    @Override
    public Passenger FindPassengerByEmail(String email) {
        return passengerRepository.findPassengerByEmail(email);
    }
}
