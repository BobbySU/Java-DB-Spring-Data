package exam.service.impl;

import com.google.gson.Gson;
import exam.model.Customer;
import exam.model.dto.CustomerSeedDTO;
import exam.repository.CustomerRepository;
import exam.service.CustomerService;
import exam.service.TownService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final String CUSTOMERS_FILE_PATH = "src/main/resources/files/json/customers.json";

    private final CustomerRepository customerRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final TownService townService;

    public CustomerServiceImpl(CustomerRepository customerRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, TownService townService) {
        this.customerRepository = customerRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(Path.of(CUSTOMERS_FILE_PATH));
    }

    @Override
    public String importCustomers() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(readCustomersFileContent(), CustomerSeedDTO[].class))
                .filter(customerSeedDTO -> {
                    boolean isValid = validationUtil.isValid(customerSeedDTO);
                    sb.append(isValid ? String.format("Successfully imported Customer %s %s - %s",
                                    customerSeedDTO.getFirstName(), customerSeedDTO.getLastName(),
                                    customerSeedDTO.getEmail())
                                    : "Invalid Customer")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(customerSeedDTO -> {
                    Customer customer = modelMapper.map(customerSeedDTO, Customer.class);
                        customer.setTown(townService.FindTownByName(customerSeedDTO.getTown().getName()));
                    return customer;
                })
                .forEach(customerRepository::save);
        return sb.toString();
    }
}
